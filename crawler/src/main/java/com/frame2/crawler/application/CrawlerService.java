package com.frame2.crawler.application;

import com.frame2.crawler.domain.Category;
import com.frame2.crawler.domain.Option;
import com.frame2.crawler.domain.Product;
import com.frame2.crawler.domain.SaleProduct;
import com.frame2.crawler.infrastructure.CategoryRepository;
import com.frame2.crawler.infrastructure.OptionRepository;
import com.frame2.crawler.infrastructure.ProductRepository;
import com.frame2.crawler.infrastructure.SaleProductRepository;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CrawlerService {

    @Value("${FQDN}")
    private String FQDN;

    private final FileService fileService;
    private final CategoryRepository categoryRepository;
    private final SaleProductRepository saleProductRepository;
    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;
    private EntityManager entityManager;

    // application.properties에서 URL 목록 가져오기
    @Value("${baseUrlList}")
    private String baseUrlListProperty;

    // SaleProducts 크롤링 메서드
    @Transactional
    public List<SaleProduct> crawlSaleProducts() throws IOException {
        List<SaleProduct> allSaleProducts = new ArrayList<>();

        // baseUrlListProperty 값을 List<String>으로 변환
        List<String> baseUrlList = List.of(baseUrlListProperty.split(","));

        // 여러 개의 baseUrl을 반복하여 크롤링 처리
        for (String baseUrl : baseUrlList) {
            // 각 baseUrl에 대해 HTML 문서 가져오기
            Document document = Jsoup.connect(baseUrl).get();

            // 메타 데이터는 한 번만 가져옵니다.
            String productName = document.select("meta[property=og:title]").attr("content").trim();
            String imageUrl = document.select("meta[property=og:image]").attr("content").trim();
            if (imageUrl.isEmpty()) {
                imageUrl = "default_image.jpg";  // 기본 이미지 경로
            }

//            // 설명 이미지 URL 추출 (ec-data-src 속성) - 단 하나의 이미지만 사용
//            List<String> descriptionImages = new ArrayList<>();
//            for (Element imgElement : document.select("div.cont img[ec-data-src]")) {
//                String descriptionImage = imgElement.attr("ec-data-src");
//                if (!descriptionImage.isEmpty()) {
//                    descriptionImages.add(
//                            descriptionImage.startsWith("//") ? "https:" + descriptionImage : descriptionImage);
//                }
//            }

            // 설명 이미지 URL 추출 (ec-data-src 속성) - 여러 이미지 사용, 여러 이미지 콤마로 구분
            List<String> descriptionImages = new ArrayList<>();
            StringBuilder descriptionImagesBuilder = new StringBuilder();

            Elements imgElements = document.select("div.cont img[ec-data-src]");
            for (Element imgElement : imgElements) {
                String descriptionImage = imgElement.attr("ec-data-src");

                // 이미지 URL이 비어있지 않으면 처리
                if (!descriptionImage.isEmpty()) {
                    // 상대 URL을 절대 URL로 변환
                    String fullImageUrl = descriptionImage.startsWith("//") ? "https:" + descriptionImage
                            : FQDN + descriptionImage;

                    // 여러 이미지 URL을 콤마로 구분하여 추가
                    if (!descriptionImagesBuilder.isEmpty()) {
                        descriptionImagesBuilder.append(",");
                    }
                    descriptionImagesBuilder.append(fullImageUrl);
                }
            }

            // List로 저장 (콤마로 구분된 이미지 URL을 리스트로 변환)
            String finalDescriptionImages = descriptionImagesBuilder.toString();
            if (!finalDescriptionImages.isEmpty()) {
                descriptionImages = List.of(finalDescriptionImages.split(","));
            }

            String priceString = document.select("meta[property=product:sale_price:amount]").attr("content").trim();
            int salePrice = 0;
            if (!priceString.isEmpty()) {
                try {
                    salePrice = Integer.parseInt(priceString);
                } catch (NumberFormatException e) {
                    salePrice = 0;
                }
            }

            // 상품 크롤링
            Element productElement = document.select("li.xans-record-").first();
            if (productElement != null) {
                int random = new Random().nextInt(1000) + 1;

                // SaleProduct 객체 생성
                SaleProduct product = SaleProduct.builder()
                        .name(productName)  // og:title에서 추출한 상품 이름
                        .mainImg(imageUrl)  // og:image에서 추출한 이미지 URL
                        .subImage(descriptionImages.isEmpty() ? imageUrl : descriptionImages.get(0))
                        .descriptionImage(descriptionImages.isEmpty() ? "" : descriptionImages.get(1))
                        .salePrice(salePrice)  // 가격
                        .saleCount(random)  // 랜덤 판매 수량
                        .build();

                // 각 baseUrl에 대해 크롤링한 상품을 목록에 추가
                allSaleProducts.add(product);
            }
        }

//        // 크롤링된 데이터를 파일에 저장 -> sql 파일로 주기로 함
//        fileService.saveToDocs(allProducts);

        // 크롤링된 데이터를 DB에 저장 (엔티티로 저장)
        saleProductRepository.saveAll(allSaleProducts);

        // 크롤링된 상품 목록 반환
        return allSaleProducts;
    }

    // Options 크롤링 메서드
    @Transactional
    public List<Option> crawlOptions() throws IOException {
        List<Option> options = new ArrayList<>();  // 여러 Option 객체를 저장할 리스트

        // baseUrlListProperty 값을 List<String>으로 변환
        List<String> baseUrlList = List.of(baseUrlListProperty.split(","));

        // 여러 개의 baseUrl을 반복하여 크롤링 처리
        for (String baseUrl : baseUrlList) {
            // 각 baseUrl에 대해 HTML 문서 가져오기
            Document document = Jsoup.connect(baseUrl).get();

            // <optgroup> 내의 <option> 태그들 추출
            Elements optgroupElements = document.select("optgroup[label]");

            for (Element optgroupElement : optgroupElements) {
                String optionTitle = optgroupElement.attr("label");  // optgroup의 label 속성값을 옵션 이름으로 사용

                // <optgroup> 내의 <option> 태그에서 value와 텍스트 추출
                Elements optionTags = optgroupElement.select("option[value]:not([disabled])");
                for (Element optionTag : optionTags) {
                    String optionText = optionTag.text();

                    // 각 옵션에 대해 Option 객체를 생성하여 리스트에 추가
                    Option option = Option.builder()
                            .name(optionTitle)
                            .value(optionText)
                            .build();

                    options.add(option);
                }
            }
        }

        // 크롤링된 데이터를 DB에 저장 (엔티티로 저장)
        optionRepository.saveAll(options);

        // 크롤링된 Option 리스트 반환
        return options;
    }

    @Transactional
    public List<Product> crawlProducts() throws IOException {
        List<Product> allProducts = new ArrayList<>();

        // baseUrlListProperty 값을 List<String>으로 변환
        List<String> baseUrlList = List.of(baseUrlListProperty.split(","));

        // 여러 개의 baseUrl을 반복하여 크롤링 처리
        for (String baseUrl : baseUrlList) {
            // 각 baseUrl에 대해 HTML 문서 가져오기
            Document document = Jsoup.connect(baseUrl).get();

            // 메타 데이터를 가져오기
            String productName = document.select("meta[property=og:title]").attr("content").trim();
            String priceString = document.select("meta[property=product:price:amount]").attr("content").trim();
            String description = document.select("meta[property=og:description]").attr("content").trim();
            String imageUrl = document.select("meta[property=og:image]").attr("content").trim();

            if (imageUrl.isEmpty()) {
                imageUrl = "default_image.jpg";  // 기본 이미지 경로
            }

            int price = 0;
            if (!priceString.isEmpty()) {
                try {
                    price = Integer.parseInt(priceString);
                } catch (NumberFormatException e) {
                    price = 0;  // 가격 파싱 오류가 발생하면 0으로 처리
                }
            }

            // Product 객체 생성
            Product product = Product.builder()
                    .name(productName)  // og:title에서 추출한 상품 이름
                    .price(price)  // 가격
                    .description(description)  // og:description에서 추출한 상품 설명
                    .image(imageUrl)  // og:image에서 추출한 이미지 URL
                    .build();

            // 크롤링한 상품을 목록에 추가
            allProducts.add(product);
        }

        // 크롤링된 데이터를 DB에 저장
        productRepository.saveAll(allProducts);

        // 크롤링된 상품 목록 반환
        return allProducts;
    }

    // 최상위 카테고리 저장: 트랜잭션 분리
    @Transactional
    public List<Category> generateCategories() {
        List<Category> categories = new ArrayList<>();

        // Root 카테고리 배열
        String[] rootCategories = {
                "주방잡화",
                "조리기구",
                "보관/밀폐용기",
                "주방가전/측정기구"
        };

        // Root 카테고리 배열을 반복문으로 처리
        for (String name : rootCategories) {
            Category rootCategory = Category.builder()
                    .rootCategory(null) // 루트 카테고리는 null로 설정
                    .parentCategory(null)
                    .categoryName(name)
                    .build();

            // 리스트에 Root 카테고리 추가
            categories.add(rootCategory);

            // switch 문을 사용하여 하위 카테고리 배열 처리
            switch (name) {
                case "주방잡화":
                    // 주방잡화 하위 목록 배열
                    String[] sub1 = {
                            "고무장갑/기타장갑",
                            "수세미/행주",
                            "쟁반/트레이",
                            "주방정리소품",
                            "장식용품",
                            "핸드카트/운반기",
                            "기타용품"
                    };
                    // 하위 카테고리 객체 생성
                    for (String categoryName : sub1) {
                        Category subCategory = Category.builder()
                                .rootCategory(rootCategory) // Root 카테고리를 상속
                                .parentCategory(rootCategory)
                                .categoryName(categoryName)
                                .build();
                        categories.add(subCategory);
                    }
                    break;

                case "조리기구":
                    // 조리기구 하위 목록 배열
                    String[] sub2 = {
                            "거품기",
                            "국자/스쿠프",
                            "깔때기",
                            "다시망/건지기",
                            "뒤집개/볶음스푼",
                            "믹싱볼",
                            "바구니/채반",
                            "솔/브러쉬",
                            "스퀴저",
                            "오프너",
                            "주걱/헤라/스패츄라",
                            "집게",
                            "기타조리도구"
                    };
                    // 하위 카테고리 객체 생성
                    for (String categoryName : sub2) {
                        Category subCategory = Category.builder()
                                .rootCategory(rootCategory) // Root 카테고리를 상속
                                .parentCategory(rootCategory)
                                .categoryName(categoryName)
                                .build();
                        categories.add(subCategory);
                    }
                    break;

                case "보관/밀폐용기":
                    // 보관/밀폐용기 하위 목록 배열
                    String[] sub3 = {
                            "김치통",
                            "도시락/찬합",
                            "도자기/유리용기",
                            "물병/물통",
                            "플라스틱용기",
                            "스텐용기",
                            "보온/보냉/도시락용기",
                            "밧드/가니쉬케이스",
                            "양념통/소스용기",
                            "기타보관용기"
                    };
                    // 하위 카테고리 객체 생성
                    for (String categoryName : sub3) {
                        Category subCategory = Category.builder()
                                .rootCategory(rootCategory) // Root 카테고리를 상속
                                .parentCategory(rootCategory)
                                .categoryName(categoryName)
                                .build();
                        categories.add(subCategory);
                    }
                    break;

                case "주방가전/측정기구":
                    // 주방가전/측정기구 하위 목록 배열
                    String[] sub4 = {
                            "그릴/하이라이트/인덕션",
                            "믹서기/핸드블렌더",
                            "에어프라이어/오븐",
                            "전기밥솥",
                            "전기포트/멀티포트",
                            "측정기구/타이머",
                            "토스터기",
                            "기타 가전"
                    };
                    // 하위 카테고리 객체 생성
                    for (String categoryName : sub4) {
                        Category subCategory = Category.builder()
                                .rootCategory(rootCategory) // Root 카테고리를 상속
                                .parentCategory(rootCategory)
                                .categoryName(categoryName)
                                .build();
                        categories.add(subCategory);
                    }
                    break;

                default:
                    break;
            }
        }

        // 크롤링된 데이터를 DB에 저장
        categoryRepository.saveAll(categories);

        return categories;
    }
}