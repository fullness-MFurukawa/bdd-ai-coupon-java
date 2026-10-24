package jp.co.fullness.coupon.domain.service;

import java.time.LocalDateTime;
import java.util.Locale;

import jp.co.fullness.coupon.domain.model.Cart;
import jp.co.fullness.coupon.domain.model.Coupon;
import jp.co.fullness.coupon.domain.model.CouponApplicationResult;
import lombok.RequiredArgsConstructor;

/**
 * 割引計算。
 */
@RequiredArgsConstructor
public class DiscountCalculationService {

	/** 有効期限切れのエラーメッセージ */
	private static final String EXPIRED_ERROR_MESSAGE = "このクーポンは有効期限が切れています";

	/** 最低購入金額未満のエラーメッセージ（最低購入金額は3桁ごとにカンマで区切る） */
	private static final String BELOW_MINIMUM_PURCHASE_ERROR_MESSAGE = "%,d 円以上のお買い上げでご利用いただけます";

	/**
	 * クーポンを適用する。
	 *
	 * @param coupon クーポン
	 * @param cart 買い物かご
	 * @param now 現在日時
	 * @return 適用結果
	 */
	public CouponApplicationResult apply(Coupon coupon, Cart cart, LocalDateTime now) {
		// 有効期限が切れたクーポンは、金額の条件を確認するまでもなく使えないことを優先して伝える
		if (!coupon.isValidOn(now)) {
			return CouponApplicationResult.failure(EXPIRED_ERROR_MESSAGE, cart.getTotalAmount());
		}
		if (!coupon.isSatisfiedBy(cart.getTotalAmount())) {
			String errorMessage = String.format(Locale.JAPAN, BELOW_MINIMUM_PURCHASE_ERROR_MESSAGE,
					coupon.getMinimumPurchaseAmount().amount());
			return CouponApplicationResult.failure(errorMessage, cart.getTotalAmount());
		}
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
		return coupon.isValidOn(now) && coupon.isSatisfiedBy(cart.getTotalAmount());
	}

}
