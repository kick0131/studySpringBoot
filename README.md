# studySpringBoot

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/kick0131/studySpringBoot/tree/main.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/kick0131/studySpringBoot/tree/main)

## description
SpringBoot関連サンプルプロジェクト集  
個々のプロジェクトの詳細についてはサブプロジェクト内のReadme.mdを参照

# setup
## install JDK
```bash
# [Ubuntu]インストール可能なJDKの確認
sudo apt search "^openjdk.*jdk$"
# [Ubuntu]JDKのインストール
sudo apt install -y openjdk-XX-jdk
```

## install Maven
```bash
# [Ubuntu]Mavenのインストール
sudo apt install -y maven
```

## install CircleCI CLI
```bash
# [Win]事前にChocoletyのインストールが必要
choco install circleci-cli -y
# [Ubuntu]
sudo snap install circleci
```

### CircleCI validation check
.circleci/config.ymlの文法チェック
```bash
circleci config validate
```

## Usage VsCode
### install Extention
| Toolname | description |
| --- | --- |
| Spring Boot Extention Pack | 公式のVsCodeサポート。必須
| Extension Pack for Java | プロジェクトファイルを立ち上げようすると推奨される。必須 |
| Red Hat Dependency Analytics | ライブラリの脆弱性チェック |


上記入れればSpring Initializr Java SupportもSpring Boot Toolsも入る

### usage Extention
```bash
# コマンドパレットからSpringBoot initializr呼び出し
Ctrl + Shift + P
spring initializr

# 作りたい設定を選ぶ(省略)
# 初期設定で導入したいライブラリを選択
この辺りは大体必要
Spring Web
Spring Boot Tools
lombok
# プロジェクトの出力先を選ぶ
```

