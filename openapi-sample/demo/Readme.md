# OpenAPI sample

## Description
簡単なREST APIのサンプルを作成、ついでに詳細設計用のWebドキュメントも作成する

### version
openjdk 17.0.3 2022-04-19  
Apache Maven 3.8.6

## springdoc-openapi
Swagger風のUIでWEBページを出力してくれる  
[公式ページ](https://springdoc.org/)

### install
swagger-uiのパス定義を追加するだけ
```bash
# swagger-ui custom path
springdoc.swagger-ui.path=/swagger-ui.html
```
### usage
`http://localhost:8080/swagger-ui.html`にアクセスするだけ


## To Container App
コンテナ化対応に必要なもの
- Dockerfile

```bash
# 事前にmvnコマンドでjarファイルが作成できることを確認
mvn package
# イメージファイル作成
docker image build -t kick0131/springboot-demo:0.0.1 ./
```


## Point

### DefaultErrorController
エラー処理は/errorにマッピングされることを利用し、共通のエラーメッセージ出力を行っている

[エラーハンドリング](https://docs.spring.io/spring-boot/docs/1.5.3.RELEASE/reference/html/boot-features-developing-web-applications.html#boot-features-error-handling)
