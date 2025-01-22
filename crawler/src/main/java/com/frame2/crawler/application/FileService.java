package com.frame2.crawler.application;

import com.frame2.crawler.domain.SaleProduct;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Properties;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    // 저장할 파일 경로 설정 (예시로 docs 파일로 저장)
    private static final String FILE_PATH = "./crawler/products_data.docs";
    private static final String PROPERTIES_FILE_PATH = "./server/src/main/resources/application.properties"; // properties 파일 경로

    // 크롤링된 제품 데이터를 docs 파일에 저장하는 메서드
    public void saveToDocs(List<SaleProduct> products) {
        StringBuilder sb = new StringBuilder();

        // 제품 정보 문자열로 만들어서 추가
        for (SaleProduct product : products) {
            String sql = String.format(
                    "('N', %d, 'Y', 1, now(), now(), now(), 'ss', '%s', '%s', '%s', '%s', '12', '%s', 'origin', 'preview');",
                    (int) (Math.random() * 500) * 100,  // 랜덤 값
                    product.getName(),  // 제품 이름
                    product.getMainImage(),  // 메인 이미지 URL
                    product.getSubImage(),  // 서브 이미지 URL
                    product.getDescriptionImage(),  // 설명 이미지 URL
                    product.getSalePrice());  // 제품 가격

            // 파일에 추가할 SQL 문을 문자열로 구성
            sb.append(sql).append("\n");
        }

        // 생성된 내용을 파일에 이어붙여서 저장
        try {
            Files.write(Paths.get(FILE_PATH), sb.toString().getBytes(), StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
            System.out.println("파일 저장 완료.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 크롤링된 URL 목록을 application.properties에 저장하는 메서드
    public void toProperties(List<String> urls) {
        Properties properties = new Properties();

        // URL을 "product.urls"에 추가
        StringBuilder urlList = new StringBuilder();
        for (String url : urls) {
            try {
                // URL 인코딩 처리: URL에 한글이나 특수문자가 포함되어 있을 경우 깨짐을 방지
                String encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString());
                urlList.append(encodedUrl).append(",");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 마지막에 추가된 콤마 제거
        if (urlList.length() > 0) {
            urlList.deleteCharAt(urlList.length() - 1);
        }

        // 프로퍼티에 URL 목록 저장
        properties.setProperty("product.urls", urlList.toString());

        // 새로운 내용을 properties 파일에 저장
        try {
            // 파일을 UTF-8 인코딩 방식으로 저장
            properties.store(Files.newBufferedWriter(Paths.get(PROPERTIES_FILE_PATH), StandardCharsets.UTF_8),
                    "Updated product URLs");
            System.out.println("application.properties에 URL 목록 저장 완료.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
