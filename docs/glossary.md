# 対訳表

ユビキタス言語（日本語）と、Java の実装名の対応を定めます。クラス名・属性名・メソッド名は、
この表に従って記述します。表に無い用語が必要になった場合は、名前を定める前に確認してください。

対象は、`src/test/resources/features/coupon.feature` のうち、背景と
「購入金額が最低購入金額に 1 円足りない場合はクーポンを使えない」シナリオに必要な範囲です。
シナリオを追加するたびに、この表も更新します。

| ユビキタス言語 | 分類 | クラス | 属性・メソッド | 備考 |
|---|---|---|---|---|
| 金額 | 値オブジェクト | `Money` | | 負の値を許さない |
| 会員ランク | 値オブジェクト | `MembershipRank` | `of(String)` | `enum`（一般 `GENERAL`・ゴールド `GOLD`・プラチナ `PLATINUM`）、`of` は日本語の名称から列挙子を得る |
| 有効期限 | 値オブジェクト | `ExpirationDate` | | 当日まで有効 |
| 券種コード | 値オブジェクト | `CouponCode` | | クーポンを識別する |
| 適用結果 | 値オブジェクト | `CouponApplicationResult` | | 成否・エラーメッセージ・割引額・支払金額 |
| クーポン | エンティティ | `Coupon` | | 識別子は券種コード |
| 買い物かご | エンティティ | `Cart` | | |
| 割引計算 | ドメインサービス | `DiscountCalculationService` | | |
| クーポンを適用する | メソッド | `DiscountCalculationService` | `apply(Coupon, Cart, LocalDateTime)` | 戻り値は `CouponApplicationResult`、引数の名前は `coupon`、`cart`、`now`、ステップ定義はこれを呼び出す。判定は有効期限、最低購入金額の順に行い、両方を満たさない場合は有効期限切れのメッセージを返す。適用できない場合は `CouponApplicationResult.failure` を返す。適用できる場合の割引額の計算は現時点では実装せず、`UnsupportedOperationException` のままとする |
| クーポンが使えるか | メソッド | `DiscountCalculationService` | `canApply(Coupon, Cart, LocalDateTime)` | 戻り値は `boolean`、引数の名前は `coupon`、`cart`、`now`。`Coupon.isValidOn` と `Coupon.isSatisfiedBy` の両方が `true` の場合に `true` |
| 金額が他の金額より少ないか | メソッド | `Money` | `isLessThan(Money)` | 戻り値は `boolean`、引数の名前は `other` |
| 指定した日が有効期限内か | メソッド | `ExpirationDate` | `isValidOn(LocalDate)` | 当日まで有効。引数の名前は `targetDate`。引数の日付が有効期限の日付以前であれば `true` |
| 最低購入金額を満たすか | メソッド | `Coupon` | `isSatisfiedBy(Money)` | 引数は購入金額で、名前は `totalAmount`。購入金額が最低購入金額以上であれば `true` |
| クーポンが有効期限内か | メソッド | `Coupon` | `isValidOn(LocalDateTime)` | 引数の名前は `dateTime`。引数の日時を日付に変換し、`ExpirationDate.isValidOn` に委譲する |
| 同一のクーポンか | メソッド | `Coupon` | `equals`、`hashCode` | 券種コードが等しければ同一とみなす。`@EqualsAndHashCode(onlyExplicitlyIncluded = true)` と `couponCode` への `@EqualsAndHashCode.Include` で実装する |
| 適用の失敗 | メソッド | `CouponApplicationResult` | `failure(String, Money)` | 静的ファクトリ。引数の名前は `errorMessage`、`totalAmount`。成否は `false`、割引額は 0 円、支払金額は購入金額とする |
| 最低購入金額未満のエラーメッセージ | 文言 | `DiscountCalculationService` | | 「{最低購入金額} 円以上のお買い上げでご利用いただけます」。金額はクーポンの最低購入金額から生成し、3桁ごとにカンマで区切る（例：5,000）。書式は `Locale.JAPAN` を指定して生成する |
| 有効期限切れのエラーメッセージ | 文言 | `DiscountCalculationService` | | 「このクーポンは有効期限が切れています」 |
| 業務ルール違反 | 例外 | `DomainException` | `DomainException(String)`、`DomainException(String, Throwable)` | `RuntimeException` を継承、値オブジェクトやエンティティの制約に反する値を渡された場合にスローする。原因となる例外を包む場合は第2のコンストラクタを使用する |
| 券種コード | 属性 | `Coupon` | `couponCode` | 型は `CouponCode` |
| 最低購入金額 | 属性 | `Coupon` | `minimumPurchaseAmount` | 型は `Money` |
| 割引上限額 | 属性 | `Coupon` | `maximumDiscountAmount` | 型は `Money` |
| 有効期限 | 属性 | `Coupon` | `expirationDate` | 型は `ExpirationDate` |
| 購入金額 | 属性 | `Cart` | `totalAmount` | 型は `Money` |
| 会員ランク | 属性 | `Cart` | `membershipRank` | 型は `MembershipRank` |
| 会員ランクの名称 | 属性 | `MembershipRank` | `displayName` | 型は `String`（一般・ゴールド・プラチナ）、`of` はこの値で列挙子を探索する、外部から参照するメソッドは現時点では用意しない |
| 成否 | 属性 | `CouponApplicationResult` | `successful` | 型は `boolean` |
| エラーメッセージ | 属性 | `CouponApplicationResult` | `errorMessage` | 型は `String` |
| 割引額 | 属性 | `CouponApplicationResult` | `discountAmount` | 型は `Money` |
| 支払金額 | 属性 | `CouponApplicationResult` | `paymentAmount` | 型は `Money` |
| 金額の値 | 属性 | `Money` | `amount` | 型は `long`（円単位） |
| 券種コードの値 | 属性 | `CouponCode` | `value` | 型は `String` |
| 有効期限の日付 | 属性 | `ExpirationDate` | `date` | 型は `LocalDate` |

## ステップ定義

ステップ定義は、テスト側の `jp.co.fullness.coupon` パッケージの `CouponSteps` クラスに記述します。
アノテーションには `.feature` のステップ文をそのまま使用し、メソッド名は次の表に従います。
`{string}` と `{int}` は Cucumber のパラメータで、それぞれ `String` と `int` の引数になります。

| 種別 | ステップ文 | メソッド名 |
|---|---|---|
| 前提 | クーポン {string} が存在する | `couponExists` |
| 前提 | クーポン {string} の最低購入金額は {int} 円である | `couponHasMinimumPurchaseAmount` |
| 前提 | クーポン {string} の割引上限額は {int} 円である | `couponHasMaximumDiscountAmount` |
| 前提 | クーポン {string} の有効期限は {string} である | `couponExpiresOn` |
| 前提 | 割引額の端数は切り捨てる | `discountFractionIsTruncated` |
| 前提 | ポイントは {int} ポイントを {int} 円として計算する | `pointRateIs` |
| 前提 | 現在日時は {string} である | `currentDateTimeIs` |
| 前提 | ポイントを利用しない | `pointsAreNotUsed` |
| 前提 | 会員ランクは {string} である | `membershipRankIs` |
| 前提 | カートの購入金額は {int} 円である | `cartTotalAmountIs` |
| もし | クーポン {string} を適用する | `applyCoupon` |
| ならば | クーポンの適用は失敗する | `couponApplicationFails` |
| ならば | エラーメッセージ {string} が表示される | `errorMessageIsShown` |
| ならば | 割引額は {int} 円である | `discountAmountIs` |
| ならば | 支払金額は {int} 円である | `paymentAmountIs` |

メソッド名は、前提では状況を表す名前（`couponExists` など）、もしでは動詞から始まる名前（`applyCoupon`）、
ならばでは検証する内容を表す名前（`couponApplicationFails` など）とします。

`.feature` で「かつ」と記述されたステップは、直前のステップと同じ種別（`@前提` または `@ならば`）で
定義します。金額は `{int}` で受け取り、`int` の引数とします。`Money` への変換はステップ定義の中で行います。
日付と日時は `{string}` で受け取り、`LocalDate` と `LocalDateTime` への変換もステップ定義の中で行います。
`.feature` の書式は、日付が `yyyy-MM-dd`、日時が `yyyy-MM-dd HH:mm:ss` です。書式は `DateTimeFormatter` の
定数として `CouponSteps` に定義します。

### ステップ定義が保持する状態

背景の各ステップで受け取った値は、次のフィールドに保持します。`Coupon` と `Cart` は、属性がそろう
「クーポン {string} を適用する」のステップで生成し、`DiscountCalculationService.apply` を呼び出します。
検証には AssertJ の `assertThat` を使用し、`result` の各属性を確認します。

| 用語 | フィールド | 型 | 設定するステップ |
|---|---|---|---|
| 券種コード | `couponCode` | `CouponCode` | クーポン {string} が存在する |
| 最低購入金額 | `minimumPurchaseAmount` | `Money` | クーポン {string} の最低購入金額は {int} 円である |
| 割引上限額 | `maximumDiscountAmount` | `Money` | クーポン {string} の割引上限額は {int} 円である |
| 有効期限 | `expirationDate` | `ExpirationDate` | クーポン {string} の有効期限は {string} である |
| ポイントの換算における、ポイント数 | `pointRatePoints` | `int` | ポイントは {int} ポイントを {int} 円として計算する |
| ポイントの換算における、ポイント数に相当する金額（円） | `pointRateYen` | `int` | ポイントは {int} ポイントを {int} 円として計算する |
| 現在日時 | `now` | `LocalDateTime` | 現在日時は {string} である |
| 会員ランク | `membershipRank` | `MembershipRank` | 会員ランクは {string} である |
| 購入金額 | `totalAmount` | `Money` | カートの購入金額は {int} 円である |
| 適用結果 | `result` | `CouponApplicationResult` | クーポン {string} を適用する |
| 割引計算 | `discountCalculationService` | `DiscountCalculationService` | （フィールドの宣言時に生成する） |

「割引額の端数は切り捨てる」「ポイントは {int} ポイントを {int} 円として計算する」「ポイントを利用しない」の
3つのステップは、現時点のシナリオの判定には関わりません。受け取った値がある場合はフィールドに保持するだけとし、
判定には使用しません。これらを検証するシナリオを追加した時点で、対訳表に用語を加え、判定に使用します。

## 補足

**1つの用語が1つのクラスになるとは限りません。**
最低購入金額、購入金額、支払金額、割引額は、概念としてはいずれも金額です。違いは、
どのような役割で用いられているかだけです。したがって、クラスは `Money` のみとし、役割は属性名で表します。
別の型に分けるのは、その概念だけが持つ制約がある場合や、取り違えを型で防ぎたい場合に限られます。

**.feature に現れない構成要素もあります。**
「ならば クーポンの適用は失敗する」「かつ エラーメッセージ … が表示される」を検証するには、
適用した結果を受け取る型が必要です。`CouponApplicationResult` がこれにあたります。

**値オブジェクトの内部も、この表で定義します。**
`Money` や `CouponCode` は、クラス名だけでは実装できません。保持する値まで定義して、
はじめて `record` として記述できます。金額は円単位の整数で扱えるため `long` とし、
整数のみの計算にしています。

**会員ランクは `enum` にしています。**
理由は2つあります。① 取りうる値が一般・ゴールド・プラチナの3つに限られる。
② `record` にすると「シルバー」のような想定外の値を生成できてしまうのに対し、
`enum` であれば列挙子以外の値が存在しない。`.feature` には「一般」と日本語で
記述されていますので、日本語の名称から列挙子を得る `of(String)` を `MembershipRank` に用意します。
この変換をステップ定義に記述すると、ステップ定義が業務の知識を持つことになるためです。

**現在日時は `apply` の引数で受け取るようにしています。**
背景には「現在日時は "2026-09-20 10:00:00" である」が記述されています。この日時で有効期限を判定するには、
ドメインサービスが背景で指定された日時を使用する必要があります。ドメインサービスの内部で
`LocalDateTime.now()` のように実際の現在時刻を取得する実装にすると、背景の指定とは無関係に
実行した日の日時で判定されてしまい、シナリオの結果が実行日によって変わります。

**業務上の失敗と、プログラムの誤りを分けます。**
処理が正常に完了しない場合には、業務上の失敗とプログラムの誤りの2種類があり、この2つを区別して扱います。
クーポンを使えないことは業務上の失敗で、`.feature` にも「ならば クーポンの適用は失敗する」と
記述されているとおり、業務としては正常な結果です。したがって例外にはせず、`CouponApplicationResult` の
成否とエラーメッセージで呼び出し側に返します。一方、金額に負の値が渡されることはプログラムの誤りで、
仕様のどこにも記述されていません。これは正常な結果として返すものではないため、`DomainException` を
スローして処理を中断します。対訳表に「業務ルール違反」として `DomainException` の行があるのは、
この例外を表すためです。

**判定の順序は、業務上の判断として定めています。**
有効期限と最低購入金額の両方を満たさない場合にどちらのメッセージを返すかは、`.feature` に記述されていません。
これは「条件が競合した場合の優先順位」にあたり、開発側で定めてよい事項ではありません。本プロジェクトでは、
有効期限が切れたクーポンは金額の条件を確認するまでもなく使えないことを利用者に伝えるべきである、という判断で、
有効期限を先に判定します。この順序は `apply` の備考に記載しています。

**エラーメッセージの数値は、最低購入金額から生成します。**
「5,000 円以上のお買い上げでご利用いただけます」の文言は `.feature` で与えられています。数値の 5,000 は
クーポンの最低購入金額ですので、文言を定数として固定せず、最低購入金額から生成します。最低購入金額の異なる
クーポンを追加したとき、文言の数値もそれに合わせて変わるべきだという判断です。カンマ区切りも `.feature` の
記述に合わせます。

**`.feature` に記述されていない振る舞いは、`CLAUDE.md` の例外の方針に従います。**
負の金額や、該当しない会員ランクの名称は、業務の場面では起こりえないプログラムの誤りです。これらの扱いは
`.feature` には記述されていませんが、「推測で補わない」の対象となる業務上の判断ではなく、`CLAUDE.md` の
「例外の方針」で定めた `DomainException` のスローにあたります。開発者に確認せずに実装してください。

**他の言語でも、この表の左の2列は変わりません。**
対訳表の左の2列、すなわちユビキタス言語と分類は、実装する言語に依存しません。
C# や Python で実装する際も、変わるのは右側のクラスと属性・メソッドの列だけで、
この表をそのまま使用できます。