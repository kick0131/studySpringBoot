# Brousing service 
Seleniumを使い、特定サイトの情報をJSON形式で取り出す

## 準備
### Selenium
1. ChromeDriverのダウンロード
1. 環境変数PATHにChromeDriver配置ディレクトリのパスを指定

### Securefile
src/main/resources/static/secure.jsonに以下ファイルを配置
```bash
{
  "id": "xxx",
  "password": "xxx",
  "url": "ログイン画面のURL"
}
```


## ToDo
- 特定サイトのログイン
- XPathを使ったデータ抽出

## Done
- logbackの出力フォーマット変更

### command
```bash
# アプリケーション実行
mvn spring-boot:run
```
