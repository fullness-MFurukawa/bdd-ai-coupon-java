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

}
