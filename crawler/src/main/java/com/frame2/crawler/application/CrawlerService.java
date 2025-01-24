package com.frame2.crawler.application;

import com.frame2.crawler.domain.Category;
import com.frame2.crawler.domain.Option;
import com.frame2.crawler.domain.Product;
import com.frame2.crawler.domain.SaleProduct;
import com.frame2.crawler.domain.Stock;
import com.frame2.crawler.infrastructure.CategoryRepository;
import com.frame2.crawler.infrastructure.OptionRepository;
import com.frame2.crawler.infrastructure.ProductRepository;
import com.frame2.crawler.infrastructure.SaleProductRepository;

import com.frame2.crawler.infrastructure.StockRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    private final FileService fileService;
    private final CategoryRepository categoryRepository;
    private final SaleProductRepository saleProductRepository;
    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;
    private final StockRepository stockRepository;

    @Value("${FQDN}")
    private String FQDN;

    // application.properties에서 URL 목록 가져오기
    @Value("${baseUrlList}")
    private String baseUrlListProperty;

    // application.properties에서 카테고리 가져오기
    @Value("${crawledCategory}")
    private Long crawledCategory;

    // SaleProducts 크롤링 메서드
    @Transactional
    public List<SaleProduct> crawlSaleProducts() throws IOException {

        // baseUrlListProperty 값을 List<String>으로 변환
        List<String> baseUrlList = List.of(baseUrlListProperty.split(","));

        List<SaleProduct> allSaleProducts = new ArrayList<>();

        // 여러 개의 baseUrl을 반복하여 크롤링 처리
        for (String baseUrl : baseUrlList) {
            // 각 baseUrl에 대해 HTML 문서 가져오기
            Document document = Jsoup.connect(baseUrl).get();

            Product product = createProduct(document);
            List<Option> options = createOptions(document);

            for (Option option : options) {
                SaleProduct saleProduct = createSaleProduct(product, option, document);
                allSaleProducts.add(saleProduct);
            }
        }

        saleProductRepository.saveAll(allSaleProducts);
        generateStocks(allSaleProducts);
        return allSaleProducts;
    }

    private Product createProduct(Document document) {
        String productName = document.select("meta[property=og:title]").attr("content").trim();
        String description = document.select("meta[property=og:description]").attr("content").trim();

        // 상품 가격
        int price = parsePrice(document);
        // 메인이미지
        String imageUrl = extractImageUrl(document);

        Product product = Product.builder()
                .category(categoryRepository.findOne(crawledCategory))
                .name(productName)
                .description(description)
                .price(price)
                .image(imageUrl)
                .build();

        return productRepository.save(product);
    }

    private List<Option> createOptions(Document document) {
        List<Option> options = new ArrayList<>();
        // <optgroup> 내의 <option> 태그들 추출
        Elements optgroupElements = document.select("optgroup[label]");

        for (Element optgroupElement : optgroupElements) {
            String optionTitle = optgroupElement.attr("label");
            // <optgroup> 내의 <option> 태그에서 value와 텍스트 추출
            Elements optionTags = optgroupElement.select("option[value]:not([disabled])");

            for (Element optionTag : optionTags) {
                Option option = Option.builder()
                        .name(optionTitle)
                        .value(optionTag.text())
                        .build();

                options.add(optionRepository.save(option));
            }
        }

        return options;
    }

    private SaleProduct createSaleProduct(Product product, Option option, Document document) {
        String productName = document.select("meta[property=og:title]").attr("content").trim();
        String imageUrl = extractImageUrl(document);
        List<String> descriptionImages = extractDescriptionImages(document);

        int salePrice = parsePrice(document);

        return SaleProduct.builder()
                .product(product)
                .option(option)
                .name(productName)
                .mainImg(imageUrl)
                .subImage(descriptionImages.isEmpty() ? imageUrl : descriptionImages.get(0))
                .descriptionImage(descriptionImages.size() > 1 ? descriptionImages.get(1) : "")
                .salePrice(salePrice)
                .build();
    }

    private int parsePrice(Document document) {
        String priceString = document.select("meta[property=product:price:amount]").attr("content").trim();
        return priceString.isEmpty() ? 0 : Integer.parseInt(priceString);
    }

    private String extractImageUrl(Document document) {
        String imageUrl = document.select("meta[property=og:image]").attr("content").trim();
        return imageUrl.isEmpty() ? "default_image.jpg" : imageUrl;
    }

    private List<String> extractDescriptionImages(Document document) {
        List<String> descriptionImages = new ArrayList<>();
        Elements imgElements = document.select("div.cont img[ec-data-src]");

        for (Element imgElement : imgElements) {
            String descriptionImage = imgElement.attr("ec-data-src");
            if (!descriptionImage.isEmpty()) {
                String fullImageUrl = descriptionImage.startsWith("//")
                        ? "https:" + descriptionImage
                        : FQDN + descriptionImage;
                descriptionImages.add(fullImageUrl);
            }
        }

        return descriptionImages;
    }

    // 카테고리 저장
    @Transactional
    public void generateCategories() {
        List<Category> categories = new ArrayList<>();

        // Root 카테고리 정의
        Map<String, String[]> categoryMap = new LinkedHashMap<>() {{
            put("주방잡화", new String[]{
                    "고무장갑/기타장갑", "수세미/행주", "쟁반/트레이", "주방정리소품",
                    "장식용품", "핸드카트/운반기", "기타용품"
            });
            put("조리기구", new String[]{
                    "거품기", "국자/스쿠프", "깔때기", "다시망/건지기", "뒤집개/볶음스푼",
                    "믹싱볼", "바구니/채반", "솔/브러쉬", "스퀴저", "오프너",
                    "주걱/헤라/스패츄라", "집게", "기타조리도구"
            });
            put("보관/밀폐용기", new String[]{
                    "김치통", "도시락/찬합", "도자기/유리용기", "물병/물통", "플라스틱용기",
                    "스텐용기", "보온/보냉/도시락용기", "밧드/가니쉬케이스",
                    "양념통/소스용기", "기타보관용기"
            });
            put("주방가전/측정기구", new String[]{
                    "그릴/하이라이트/인덕션", "믹서기/핸드블렌더", "에어프라이어/오븐",
                    "전기밥솥", "전기포트/멀티포트", "측정기구/타이머",
                    "토스터기", "기타 가전"
            });
        }};

        // 카테고리 생성 및 추가 로직
        categoryMap.forEach((rootCategoryName, subCategories) -> {
            // Root 카테고리 생성
            Category rootCategory = createCategory(rootCategoryName, null, null);
            categories.add(rootCategory);

            // 하위 카테고리 생성
            for (String subCategoryName : subCategories) {
                Category subCategory = createCategory(subCategoryName, rootCategory, rootCategory);
                categories.add(subCategory);
            }
        });

        // 모든 카테고리 저장
        categoryRepository.saveAll(categories);
    }

    // 카테고리 생성 헬퍼 메서드
    private Category createCategory(String categoryName, Category rootCategory, Category parentCategory) {
        return Category.builder()
                .rootCategory(rootCategory)
                .parentCategory(parentCategory)
                .categoryName(categoryName)
                .build();
    }

    private void generateStocks(List<SaleProduct> allSaleProducts) {
        List<Stock> stocks = new ArrayList<>();

        allSaleProducts.forEach(saleProduct -> {
            Stock stock = Stock.builder()
                    .saleProduct(saleProduct)
                    .quantity(new Random().nextInt(1000) + 1)
                    .build();
            stocks.add(stock);
        });
        stockRepository.saveAll(stocks);
    }
}
