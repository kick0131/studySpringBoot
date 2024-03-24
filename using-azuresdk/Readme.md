# Using Azure SDK
Azure SDKの使い方サンプル

## 準備
プロジェクトをcloneしたらまずやっておくこと
```bash
# Mavenラッパーのインストール
mvn wrapper:wrapper
```

## usage
### ローカルのアプリ起動
```bash
# build
mvn build
# run
mvn spring-boot:run

# Mavenラッパーを使ったビルド
./mvmn package
# javaアプリ起動
java -jar target/xxxx.jar
```

## Dockerコンテナ
```bash
# Dockerfileからコンテナイメージの生成
docker build --build-arg JAR_FILE=target/*.jar -t usingazuresdk:1.0 .

# ビルドしたコンテナイメージからコンテナ起動
docker container run -t -p 8080:8080 usingazuresdk:1.0 --name use-azuresdk 
```

# 色々
### DockerImageにOpenJDK17が無い
Maven + OpenJDKの組み合わせにOpenJDK17が無いので18にしている

### ポート転送
接続先がSSH接続されている場合
- Teratermのポート転送
- VsCode(RemoteSSH)のポート転送機能

### /resources/templatesを見ない
Thymeleafをインストールしていない(テンプレートエンジン未設定)場合はtemplatesディレクトリを参照しない

対策  
pom.xmlにThymeleaf追加

### Docker buildの途中で選択肢(Please select an image:)が出てきて異常終了してしまう
あいまいなイメージ名にしない  
OK docker.io/library/openjdk:18-alpine  
NG quay.io/openjdk:18-alpine  

### Docker上でmvnwが使えない
★調査中。mvn packageで対応



