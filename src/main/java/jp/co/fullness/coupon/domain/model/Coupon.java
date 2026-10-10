package jp.co.fullness.coupon.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * クーポン。
 * 識別子は券種コード。
 */
@Getter
@RequiredArgsConstructor
public class Coupon {

	/** 券種コード */
	private final CouponCode couponCode;

	/** 最低購入金額 */
	private final Money minimumPurchaseAmount;

	/** 割引上限額 */
	private final Money maximumDiscountAmount;

	/** 有効期限 */
	private final ExpirationDate expirationDate;

}
