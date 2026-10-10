package jp.co.fullness.coupon.domain.service;

import java.time.LocalDateTime;

import jp.co.fullness.coupon.domain.model.Cart;
import jp.co.fullness.coupon.domain.model.Coupon;
import jp.co.fullness.coupon.domain.model.CouponApplicationResult;
import lombok.RequiredArgsConstructor;

/**
 * 割引計算。
 */
@RequiredArgsConstructor
public class DiscountCalculationService {

	/**
	 * クーポンを適用する。
	 *
	 * @param coupon クーポン
	 * @param cart 買い物かご
	 * @param now 現在日時
	 * @return 適用結果
	 */
	public CouponApplicationResult apply(Coupon coupon, Cart cart, LocalDateTime now) {
		throw new UnsupportedOperationException("未実装");
	}

	/**
	 * クーポンが使えるか。
	 *
	 * @param coupon クーポン
	 * @param cart 買い物かご
	 * @param now 現在日時
	 * @return クーポンが使える場合は {@code true}
	 */
	public boolean canApply(Coupon coupon, Cart cart, LocalDateTime now) {
		throw new UnsupportedOperationException("未実装");
	}

}
