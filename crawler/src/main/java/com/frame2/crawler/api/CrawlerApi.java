package com.frame2.crawler.api;

import com.frame2.crawler.application.CrawlerService;
import com.frame2.crawler.application.UrlService;
import com.frame2.crawler.domain.Product;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CrawlerApi {

    private final CrawlerService crawlerService;
    private final UrlService urlService;

    // application.properties에서 크롤링할 URL 가져오기
    @Value("${baseUrl}")
    String baseUrl;

    // 크롤링을 트리거할 GET 엔드포인트
    @GetMapping("/start-crawl")
//    public List<SaleProduct> startCrawl() {
    public List<Product> startCrawl() {
        try {
            // 크롤링 메서드 호출
//            return crawlerService.crawlSaleProducts();
            return crawlerService.crawlProducts();
        } catch (IOException e) {
            e.printStackTrace();
            return null;  // 예외 발생 시 null 반환
        }
    }

    // baseUrl 수집 api
    @GetMapping("/crawl-url")
    public List<String> crawlUrls() throws IOException {
        // product 링크 크롤링
        return urlService.crawlProductLinks(baseUrl);
    }
}
