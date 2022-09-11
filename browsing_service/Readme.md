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
| /(account)/yyyy | 指定した年の請求金額を取得 | x |
| /(account)/yyyymm | 指定した年月の請求金額を取得 | x |
| /user | secure.jsonの読み込み確認 | o |

# status
## ToDo
- XPathを使ったデータ抽出

## Done
- 特定サイトのログイン
- logbackの出力フォーマット変更

