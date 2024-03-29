# Brousing service 

## Description
Seleniumを使い、特定サイトの情報をJSON形式で取り出す

## 準備
### Selenium
1. [ChromeDriverのダウンロード](https://googlechromelabs.github.io/chrome-for-testing/#stable)
1. 環境変数PATHにChromeDriver配置ディレクトリのパスを指定  
  - [win] : C:/_develop/

### Securefile
src/main/resources/static/secure_xxxx.jsonに以下ファイルを配置  
xxxに入るのはpathと同じ名称(ex:secure_aplus.json)
```bash
{
  "id": "xxx",
  "password": "xxx",
  "url": "ログイン画面のURL"
}
```

### command
```bash
# ローカルリポジトリにライブラリインストール
mvn install
# アプリケーション実行
mvn spring-boot:run
```

### usage
ブラウザからlocalhost:8080にアクセス
| path | api | release |
| -- | -- | -- |
| / | 動作確認用 | o |
| /debug/user | secure.jsonの読み込み確認 | o |
| /debug/selenium | Seleniumの動作確認 | o |
| /aplus | 会社名 | o |
| /view | 会社名 | o |

# status
## ToDo
- UnitTest
- Swagger

## Done
- 特定サイトのログイン
- logbackの出力フォーマット変更
- XPathを使ったデータ抽出

# TroubleShoot
## ConnectionFailedException
原因：Chromeのバージョンが上がったことで挙動が変わった。  
対策：以下を実施したら解決した  
  - chromedriverのバージョンを最新化
  - pom.xmlのspring-boot-starter-parentを最新化

