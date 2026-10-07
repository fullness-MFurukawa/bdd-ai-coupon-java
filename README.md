# bdd-ai-coupon-java

note の連載「BDD × AI」のサンプルコードです。Gherkin で記述した仕様（`.feature`）を、
Java と Cucumber-JVM で実行します。

同じ `.feature` を他の言語でも実行します。言語ごとにリポジトリを分けていますが、
`.feature` の記述はすべて同一です。

| 言語 | リポジトリ |
|------|-----------|
| Java | bdd-ai-coupon-java（本リポジトリ） |
| C# | bdd-ai-coupon-csharp（予定） |
| Python | bdd-ai-coupon-python（予定） |

---

## 連載記事

| 回 | タイトル |
|----|---------|
| 第2回 | [仕様がそのまま品質基準になる——AIと作るGherkin（ガーキン）入門](https://note.com/fullness_dev/n/n8afa12c67ee3) |
| 第3回 | （公開後に追記） |

記事の公開時点のコードは、タグから取得できます。記事を読みながら動かす場合は、
対応するタグをチェックアウトしてください。

```bash
git clone https://github.com/fullness-MFurukawa/bdd-ai-coupon-java.git
cd bdd-ai-coupon-java
git checkout v3   # 第3回の公開時点
```

---

## 題材

ECサイトのクーポン機能を題材としています。購入金額や会員ランク、有効期限、
ポイントとの併用可否によって、クーポンを使えるかどうかと割引額が決まります。

要件とシナリオの詳細は第2回の記事を参照してください。

---

## 実行環境

| 項目 | バージョン |
|------|-----------|
| Java | 21 |
| Gradle | Gradle Wrapper（リポジトリに同梱） |
| Spring Boot | 4.1.1 |
| Cucumber-JVM | 8.0.4 |

Gradle のインストールは不要です。同梱の Wrapper を使用します。

### VS Code の拡張機能

| 拡張機能 | 発行元 |
|---------|-------|
| Extension Pack for Java | Microsoft |
| Gradle for Java | Microsoft |
| Cucumber | Cucumber（CucumberOpen） |

Lombok の拡張機能はインストールしないでください。Extension Pack for Java に含まれる
Red Hat の Java 言語サポートが Lombok を組み込みで扱うため、別途導入すると設定が競合します。

---

## 実行方法

```bash
./gradlew test
```

Windows の場合は次のとおりです。

```
gradlew.bat test
```

---

## ディレクトリ構成

```
bdd-ai-coupon-java/
├── docs/
│   └── glossary.md                       対訳表（ユビキタス言語と実装名の対応）
├── src/
│   ├── main/java/jp/co/fullness/coupon/   ドメインモデルの実装
│   └── test/
│       ├── java/jp/co/fullness/coupon/    ステップ定義と起動クラス
│       └── resources/features/            .feature
└── build.gradle.kts
```

### 設計方針

- 値オブジェクトは `record` で実装します
- エンティティとドメインサービスは Lombok を使用します。`@Data` と `@Setter` は使用しません
- ドメイン層は Spring に依存させません

`build.gradle.kts` には、現時点で使用していない依存が含まれています。連載の後半で
API として公開し、データベースに広げるため、あらかじめ加えてあるものです。

---

## 対訳表

ユビキタス言語は日本語、Java のクラス名は英語になります。訳した時点で言葉がずれるため、
対応を [docs/glossary.md](docs/glossary.md) に固定しています。

他の言語のリポジトリにも同じ表を置いています。言語ごとに変わるのは実装名の列だけです。

---

## ライセンス

MIT License

本リポジトリのコードは、自由に複製・改変してご利用いただけます。

