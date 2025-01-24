package com.frame2.crawler.api;

import com.frame2.crawler.application.CrawlerService;
import com.frame2.crawler.application.UrlService;
import com.frame2.crawler.domain.SaleProduct;
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

    // 1. 카테고리 생성 (크롤링 X)
    @GetMapping("/generate-categories")
    public void generateCategories() {

        crawlerService.generateCategories(); // 카테고리 서비스는 한 번만 실행되면 된다
    }

    // 2. baseUrl 수집 크롤링
    @GetMapping("/crawl-url")
    public List<String> crawlUrls() throws IOException {

        // product 링크 크롤링
        return urlService.crawlProductLinks(baseUrl);
    }

    // 3. 옵션, 상품, 판매상품 크롤링
    @GetMapping("/start-crawl")
    public void startCrawl() throws IOException {

        crawlerService.crawlSaleProducts();
    }
}
