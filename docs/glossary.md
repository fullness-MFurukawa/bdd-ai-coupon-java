# 対訳表

ユビキタス言語（日本語）と、Java の実装名の対応を定めます。クラス名・属性名・メソッド名は、
この表に従って記述します。表に無い名称が必要になった場合は、名前を決める前に確認してください。

対象は、`src/test/resources/features/coupon.feature` のうち、背景と
「購入金額が最低購入金額に 1 円足りない場合はクーポンを使えない」シナリオに必要な範囲です。
シナリオを追加するたびに、この表も更新します。

| ユビキタス言語 | 分類 | クラス | 属性・メソッド | 備考 |
|---|---|---|---|---|
| 金額 | 値オブジェクト | `Money` | | 負の値を許さない |
| 会員ランク | 値オブジェクト | `MembershipRank` | | 一般・ゴールド・プラチナ |
| 有効期限 | 値オブジェクト | `ExpirationDate` | | 当日まで有効 |
| 券種コード | 値オブジェクト | `CouponCode` | | クーポンを識別する |
| 適用結果 | 値オブジェクト | `CouponApplicationResult` | | 成否・エラーメッセージ・割引額・支払金額 |
| クーポン | エンティティ | `Coupon` | | 識別子は券種コード |
| 買い物かご | エンティティ | `Cart` | | |
| クーポンの適用 | ドメインサービス | `CouponApplicationService` | `apply(Coupon, Cart, MembershipRank, LocalDateTime)` | 戻り値は `CouponApplicationResult` |
| 券種コード | 属性 | `Coupon` | `couponCode` | 型は `CouponCode` |
| 最低購入金額 | 属性 | `Coupon` | `minimumPurchaseAmount` | 型は `Money` |
| 割引上限額 | 属性 | `Coupon` | `maximumDiscountAmount` | 型は `Money` |
| 有効期限 | 属性 | `Coupon` | `expirationDate` | 型は `ExpirationDate` |
| 購入金額 | 属性 | `Cart` | `totalAmount` | 型は `Money` |
| 成否 | 属性 | `CouponApplicationResult` | `successful` | 型は `boolean` |
| エラーメッセージ | 属性 | `CouponApplicationResult` | `errorMessage` | 型は `String` |
| 割引額 | 属性 | `CouponApplicationResult` | `discountAmount` | 型は `Money` |
| 支払金額 | 属性 | `CouponApplicationResult` | `paymentAmount` | 型は `Money` |

## 補足

**一つの名称が一つのクラスになるとは限りません。**
最低購入金額、購入金額、支払金額、割引額は、概念としてはいずれも金額です。違いは、
どのような役割で使われているかだけです。クラスは `Money` の一つとし、役割は属性名で表します。

**.feature に現れない構成要素もあります。**
「ならば クーポンの適用は失敗する」「かつ エラーメッセージ … が表示される」を検証するには、
適用した結果を受け取る型が必要です。`CouponApplicationResult` がこれにあたります。

**会員ランクと現在日時は、`apply` の引数で受け取ります。**
会員ランクは `Cart` にも `Coupon` にも持たせません。現在日時も `CouponApplicationService` の
内部で取得せず、引数で受け取ります。背景で現在日時を設定するため、内部で取得する作りにすると、
テストから制御できなくなります。

**他の言語でも、この表の左の2列は変わりません。**
言葉とその分類は言語に依存しません。他の言語で実装する際に変わるのは、
クラスと属性・メソッドの列だけです。
