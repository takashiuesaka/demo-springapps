# Azure Spring Apps の準備

２つの Azure Spring App Enterprise を作成する。
１つは 独自 ACR を使わない。もう１つは事前に ACR を作成しておき、それを使用するようにして作成する。

# Managed の ACR にデプロイする

## 準備

次のアプリを事前に3つ作っておく。

1. springbootapp
2. dotnetappapp
3. pythonapp

※エンドポイントを有効化しておく。

## 1. Spring Boot のソースファイルを Upload してデプロイ

コマンド：

```zsh:zsh
az config set defaults.group="リソースグループ"
az config set defaults.spring="Azure Spring App Name"

az spring app deploy \
--name springbootapp \
--build-env BP_JVM_VERSION=17.* \
--source-path . 
```

次は .NET か Python かどちらかをデモ

## 2. .NET アプリのソースファイルを Upload してデプロイ

コマンド：

```zsh:zsh
az config set defaults.group="リソースグループ"
az config set defaults.spring="Azure Spring App Name"

az spring app deploy \
--name dotnetapp \
--source-path ./web
```

## 3. Python Function アプリのソースファイルを Upload してデプロイ

AOAIシナリオにも対応しやすいということを口頭で。

コマンド：

```zsh:zsh
az config set defaults.group="リソースグループ"
az config set defaults.spring="Azure Spring App Name"

az spring app deploy \
--name pythonapp \
--source-path .
```

# 独自 ACR の場合

## 準備

アプリを事前に１つ作っておく

1. springbootapp

※エンドポイントを有効化しておく。

1. Spring Boot のソースファイルを Upload してデプロイ
    - ※ ソースを少しだけ変更する

## ビルド

ビルド作成コマンド：

```zsh:zsh
az config set defaults.spring="Azure Spring App Name"

az spring build-service build create \
--name springbootapp \
--build-env BP_JVM_VERSION=17.* \
--source-path .
```

Azure Portal で VMWare Tanzu のビルドサービスを開き、ビルドが作成されていることを見せる

## コンテナデプロイ

コンテナデプロイコマンド：

```zsh:zsh
az spring app deploy \
--name springbootapp \
--container-image springbootapp:result \
--container-registry "ACR ログインサーバー" \
--registry-username "ACR 管理者名" \
--registry-password "ACR 管理者パスワード"
```
