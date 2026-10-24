package jp.co.fullness.coupon.domain.model;

/**
 * 適用結果。
 *
 * @param successful 成否
 * @param errorMessage エラーメッセージ
 * @param discountAmount 割引額
 * @param paymentAmount 支払金額
 */
public record CouponApplicationResult(
		boolean successful,
		String errorMessage,
		Money discountAmount,
		Money paymentAmount) {

	/**
	 * 適用の失敗。
	 * 割引されないため、割引額は 0 円、支払金額は購入金額となる。
	 *
	 * @param errorMessage エラーメッセージ
	 * @param totalAmount 購入金額
	 * @return 適用に失敗した適用結果
	 */
	public static CouponApplicationResult failure(String errorMessage, Money totalAmount) {
		return new CouponApplicationResult(false, errorMessage, new Money(0), totalAmount);
	}

}
