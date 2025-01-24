## 크롤러를 사용하는 방법

1. `.\server\src\main\resources\application.properties` 파일에 `baseUrl`의 값을 수정한다. + 변경 후 재시작

> // 크롤링할 URL: 상품 전체 목록 페이지
> baseUrl=https://toffee.co.kr/category/new-arrival/157/;

2. baseUrl 수집 API 호출 : GET http://localhost:8080/crawl-url

> 출력된 결과값을 복사한다.
> "url1",
> "url2",
> ... 생략

3. `.\server\src\main\resources\application.properties` 파일에 출력된 결과값을 붙여넣기 한다. + 변경 후 재시작
    1. `Ctrl + F` 로 "(쌍따옴표)를 "(공백)"으로 replaceAll 한다. (스페이스바 ㄴㄴ)
    2. 여러줄을 한줄로 합치는 반복 행동을 한다.
    3. 저장

> baseUrlList=url1,url2,...

4. Crawling 시작 API 호출 : GET http://localhost:8080/start-crawl

> sale_products DB와 `.\crawler\products_data.docs` 파일을 확인하면 값이 잘 들어간 것을 확인할 수 있다 !!
> 참고로 docs 파일에는 내용이 이어쓰기로 삽입된다. (덮어쓰기 ㄴㄴ)

# 주의사항

## 1. FK가 null 값이다 ㅠㅠ

> 생성 순서: option, category > product > sale_products > stock

## 2. 아직은 어느 정도의 노동력이 투입되어야 한다.

## 참고사항

1. FileService.java > `toProperties()` 메서드는 No usage 상태이다. 이유는 인코딩 문제를 해결하지 못했기 때문이다.
    - 참고로, `toProperties()` 메서드는 수집한 baseUrl 목록을 application.properties 파일에 저장하는 메서드이다.
