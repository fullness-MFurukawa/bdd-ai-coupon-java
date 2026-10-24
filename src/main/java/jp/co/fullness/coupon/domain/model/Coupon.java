package jp.co.fullness.coupon.domain.model;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * クーポン。
 * 識別子は券種コード。券種コードが等しければ同一のクーポンとみなす。
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Coupon {

	/** 券種コード */
	@EqualsAndHashCode.Include
	private final CouponCode couponCode;

	/** 最低購入金額 */
	private final Money minimumPurchaseAmount;

	/** 割引上限額 */
	private final Money maximumDiscountAmount;

	/** 有効期限 */
	private final ExpirationDate expirationDate;

	/**
	 * 最低購入金額を満たすか。
	 *
	 * @param totalAmount 購入金額
	 * @return 購入金額が最低購入金額以上である場合は {@code true}
	 */
	public boolean isSatisfiedBy(Money totalAmount) {
		return !totalAmount.isLessThan(minimumPurchaseAmount);
	}

	/**
	 * クーポンが有効期限内か。
	 *
	 * @param dateTime 判定する日時
	 * @return 判定する日時の日付が有効期限内である場合は {@code true}
	 */
	public boolean isValidOn(LocalDateTime dateTime) {
		return expirationDate.isValidOn(dateTime.toLocalDate());
	}

}
