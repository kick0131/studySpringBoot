# Azure MySQLアクセス
## 目次
1. [初期構築](#初期構築)
1. [ローカルのMySQLアクセス](#ローカルのmysqlアクセス)
1. [MyBatisを使う](#mybatisを使う)
1. [テーブル結合](#テーブル結合)
1. [Azure上のMySQLアクセス](#azure上のmysqlアクセス)

## 環境
- VsCode
- SpringBoot3系
- MyBatis
- MySQL
- Azure CosmosDB

### 事前準備
- 接続先の環境に応じてポート転送設定を行う
- DBはあらかじめ用意

## 方針
- GUIは最小限、バックエンド中心の実装とする
- application.propertiesではなく、application.yamlを使う

## 使い方
1. application.yamlで接続先DBを指定する
1. 起動後、ブラウザアクセスを行う
```bash
# 起動
mvn spring-boot:run

# ブラウザアクセス
# ■ demodbデータベース
# /jdbcselect/{id}
# //jdbcselect/
# /diary/{id}
# /diary/
# 
# ■ employeeデータベース
# /employeesalary/{year}/{month}/{limit}
```

### その他アプリケーション共通操作
[Spring Boot Mavenプラグイン](https://spring.pleiades.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/)
```bash
# ライブラリインストール
mvn install
# キャッシュクリア
mvn clean
# 起動
mvn spring-boot:run
# jar作成
mvn spring-boot:repackage
# OCIイメージ作成
mvn spring-boot:build-image
```


# 初期構築
プロジェクト作成からHelloworldまで。
1. ctrl + shift + pでコマンドパレットからspring initializrを呼び出し、プロジェクト作成
1. mvn install


## application.propertiesをyamlに変更
初期構築で作られるapplication.propertiesの内容をapplication.yamlを新規に作成して内容を移す  
application.propertiesは削除。

## MyBatisの設定を追加
プロパティが存在しないと初期構築時のTestで失敗するのでダミーの設定を用意する
```bash
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mysql
    username: devusr
    password: devpass
    driver-class-name: com.mysql.cj.jdbc.Driver
```

## HelloWorld
1. Controllerクラスを用意
2. Controllerクラスが返すindex.htmlを用意

これでlocalhost:8080/demoが動作。ここがスタート地点。


# ローカルのMySQLアクセス
1. MySQLのDBを用意
    ```bash
    # id              integer       PK
    # bodytext        varchar(255)
    # create_datetime timestamp
    ```
1. application.yamlを作成したMySQLに合わせて修正
    ```bash
    # 修正前
    spring:
      datasource:
        url: jdbc:mysql://localhost:3306/mysql # ★修正
        username: devusr  # ★修正
        password: devpass # ★修正
        driver-class-name: com.mysql.cj.jdbc.Driver

    # 修正後
    spring:
      datasource:
        url: jdbc:mysql://localhost:9999/demodb?enabledTLSProtocols=TLSv1.2
        username: demouser
        password: demopass
        driver-class-name: com.mysql.cj.jdbc.Driver
    ```

# MyBatis
## Selectアノテーション利用
1. マッパーインタフェースを作成する

    java.com.example.demo.mapper
    ```java
    @Mapper
    public interface DiaryMapper {
        @Select("SELECT * FROM diary WHERE id = #{id}")
        DiaryDTO findDiaryById(@Param("id") String id);

        @Select("SELECT * from diary")
        List<DiaryDTO> findAll();
    }
    ```
1. マッパーの実装部分(Service)を作成する

    java.com.example.demo.service
    ```java
    @Service
    public class DiaryService {
      private final DiaryMapper diaryMapper;

      // コンストラクタインジェクション
      public DiaryService(DiaryMapper diaryMapper) {
        this.diaryMapper = diaryMapper;
      }

      // 1件取得
      public DiaryDTO getDiaryById(String id) {
        return diaryMapper.findDiaryById(id);
      }

      // 全件取得
      public List<DiaryDTO> getAllDiaries() {
        return diaryMapper.findAll();
      }
      
    }
    ```

1. サービスを使う
    ```java
    @RestController
    public class demoRestController {
      @Autowired
      JdbcTemplate jdbcTemplate;
      
      @Autowired
      DiaryService diaryService; // Instantiate the DiaryService class

      // 全件取得
      @GetMapping("/diary/")
      private List<DiaryDTO> getAllDiary() {
        List<DiaryDTO> diarys = diaryService.getAllDiaries();
        return diarys;
      }
    ```

## XML対応
- マッパーXMLファイルを作成

  注意点として、JavaとXMLのパッケージを合わせること
  ```
  java.com.example.xxx
    hogeMapper.java
  resources.com.example.xxx
    hogeMapper.xml
  ```

- マッパーXMLファイルにSQL定義を記載
  ```
  <mapper namespace="com.example.mysqlonazure.mapper.<クラス名>">
      <select id="<メソッド名>" resultType="com.example.mysqlonazure.dto.<DTOクラス名>">
          SELECT * FROM diary WHERE id = #{id}
      </select>
  </mapper>  
  ```


# Azure上のMySQLアクセス




# トラブルシュート
## com.mysql.jdbc.Driverは非推奨
```bash
# application.propertiesに以下を書かない！！
# com.mysql.cj.jdbc.Driverが正解
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

## CommunicationsException: Communications link failure
### 問題
MySQLの接続に失敗する

### 原因と対策
1. MySQLの接続に失敗している
    - application.properties(yaml)の接続情報を見直す
    ```bash
    url: jdbc:mysql://localhost:9999/demodb?
                      1~~~~~~~~~~~~~ 2~~~~~
    ```
    - サーバ上でPodmanコンテナを起動している場合、ポート転送して外部から接続を許可する必要がある
    - SSH接続している場合、TeratermなどのSSH転送と組み合わせる必要がある
    - MySQLクライアントの代わりにHeidiSQLを使って接続できるか確認する
    - 2の部分はDB名を指定する。接続先DBが間違っていないか。

1. TLSバージョン1.0が使用できない  
    enabledTLSProtocols=TLSv1.2オプションを付与する
    ```yaml
    jdbc:mysql://localhost:3306/testdb?enabledTLSProtocols=TLSv1.2
    ```

## BuilderException: Error creating document instance. Cause: org.xml.sax.SAXParseException;
### 問題
XMLマッパーの内容が解析できない。
- 大なり小なり記号をそのまま使っている

### 原因と対策
エスケープする。
- 大なり記号 : \&gt;
- 小なり記号 : \&lt;


# DBについて
## Diary
適当に作ったテーブル
```
+-----------------+--------------+------+-----+---------+----------------+
| Field           | Type         | Null | Key | Default | Extra          |
+-----------------+--------------+------+-----+---------+----------------+
| id              | int          | NO   | PRI | NULL    | auto_increment |
| bodytext        | varchar(255) | YES  |     | NULL    |                |
| create_datetime | timestamp    | NO   |     | NULL    |                |
+-----------------+--------------+------+-----+---------+----------------+
```

## Employees, Salaries
MySQLが公式で提供しているテーブル
```
mysql> show columns from employees;
+------------+---------------+------+-----+---------+-------+
| Field      | Type          | Null | Key | Default | Extra |
+------------+---------------+------+-----+---------+-------+
| emp_no     | int           | NO   | PRI | NULL    |       |
| birth_date | date          | NO   |     | NULL    |       |
| first_name | varchar(14)   | NO   |     | NULL    |       |
| last_name  | varchar(16)   | NO   |     | NULL    |       |
| gender     | enum('M','F') | NO   |     | NULL    |       |
| hire_date  | date          | NO   |     | NULL    |       |
+------------+---------------+------+-----+---------+-------+
6 rows in set (0.01 sec)

mysql> show columns from salaries;
+-----------+------+------+-----+---------+-------+
| Field     | Type | Null | Key | Default | Extra |
+-----------+------+------+-----+---------+-------+
| emp_no    | int  | NO   | PRI | NULL    |       |
| salary    | int  | NO   |     | NULL    |       |
| from_date | date | NO   | PRI | NULL    |       |
| to_date   | date | NO   |     | NULL    |       |
+-----------+------+------+-----+---------+-------+
```
