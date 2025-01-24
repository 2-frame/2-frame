// 여기서 나오는 url을 application.properties에 저장한다
package com.frame2.crawler.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlService {

    // application.properties에서 FQDN 및 그 다음 경로, 둘이 합친 링크 가져오기
    @Value("${FQDN}")
    private String FQDN;

    @Value("${domainPath}")
    private String domainPath;

    @Value("${productUrl}")
    private String productUrl;

    // URL에서 "productUrl"로 시작하는 링크들을 추출하는 메서드
    public List<String> crawlProductLinks(String baseUrl) throws IOException {
        List<String> productLinks = new ArrayList<>();

        // URL에 요청을 보내 HTML 문서 가져오기
        Document document = Jsoup.connect(baseUrl)
                .userAgent(
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                .get();

        // 모든 <a> 태그를 선택하여 "href" 속성에 포함된 링크를 가져옵니다.
        Elements linkElements = document.select("a[href]");

        // 각 링크를 확인하고 "FQDN/DOMAIN/*"로 시작하는 링크만 추출합니다.
        for (Element linkElement : linkElements) {
            String href = linkElement.attr("href");

            // 이미 절대 경로인 경우 변환하지 않도록 처리
            if (href.startsWith(domainPath) && !href.startsWith("https://")) {
                href = FQDN + href; // 상대 경로를 절대 경로로 변환
            }

            // 원하는 도메인과 경로를 가진 링크만 추가
//            if (href.startsWith(productUrl) && href.endsWith("/")) {
            if (href.startsWith(productUrl)) {
                // 이미 추가된 링크인지 확인하고, 중복된 링크는 추가하지 않도록 처리
                if (!productLinks.contains(href)) {
                    productLinks.add(href);
                }
            }
        }

        // URL 목록을 application.properties 파일에 저장  -> 나중에 함
//        fileService.toProperties(productLinks);

        return productLinks;
    }
}
