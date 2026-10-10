package jp.co.fullness.coupon;

import io.cucumber.java.ja.ならば;
import io.cucumber.java.ja.もし;
import io.cucumber.java.ja.前提;

/**
 * クーポンによる割引の適用のステップ定義。
 */
public class CouponSteps {

	/**
	 * クーポン {string} が存在する。
	 *
	 * @param couponCode 券種コード
	 */
	@前提("クーポン {string} が存在する")
	public void couponExists(String couponCode) {
		throw new io.cucumber.java.PendingException();
	}

	/**
	 * クーポン {string} の最低購入金額は {int} 円である。
	 *
	 * @param couponCode 券種コード
	 * @param minimumPurchaseAmount 最低購入金額（円）
	 */
	@前提("クーポン {string} の最低購入金額は {int} 円である")
	public void couponHasMinimumPurchaseAmount(String couponCode, int minimumPurchaseAmount) {
		throw new io.cucumber.java.PendingException();
	}

	/**
	 * クーポン {string} の割引上限額は {int} 円である。
	 *
	 * @param couponCode 券種コード
	 * @param maximumDiscountAmount 割引上限額（円）
	 */
	@前提("クーポン {string} の割引上限額は {int} 円である")
	public void couponHasMaximumDiscountAmount(String couponCode, int maximumDiscountAmount) {
		throw new io.cucumber.java.PendingException();
	}

	/**
	 * クーポン {string} の有効期限は {string} である。
	 *
	 * @param couponCode 券種コード
	 * @param expirationDate 有効期限
	 */
	@前提("クーポン {string} の有効期限は {string} である")
	public void couponExpiresOn(String couponCode, String expirationDate) {
		throw new io.cucumber.java.PendingException();
	}

	/**
	 * 割引額の端数は切り捨てる。
	 */
	@前提("割引額の端数は切り捨てる")
	public void discountFractionIsTruncated() {
		throw new io.cucumber.java.PendingException();
	}

	/**
	 * ポイントは {int} ポイントを {int} 円として計算する。
	 *
	 * @param points ポイント数
	 * @param yen ポイントに相当する金額（円）
	 */
	@前提("ポイントは {int} ポイントを {int} 円として計算する")
	public void pointRateIs(int points, int yen) {
		throw new io.cucumber.java.PendingException();
	}

	/**
	 * 現在日時は {string} である。
	 *
	 * @param now 現在日時
	 */
	@前提("現在日時は {string} である")
	public void currentDateTimeIs(String now) {
		throw new io.cucumber.java.PendingException();
	}

	/**
	 * ポイントを利用しない。
	 */
	@前提("ポイントを利用しない")
	public void pointsAreNotUsed() {
		throw new io.cucumber.java.PendingException();
	}

	/**
	 * 会員ランクは {string} である。
	 *
	 * @param membershipRank 会員ランクの名称
	 */
	@前提("会員ランクは {string} である")
	public void membershipRankIs(String membershipRank) {
		throw new io.cucumber.java.PendingException();
	}

	/**
	 * カートの購入金額は {int} 円である。
	 *
	 * @param totalAmount 購入金額（円）
	 */
	@前提("カートの購入金額は {int} 円である")
	public void cartTotalAmountIs(int totalAmount) {
		throw new io.cucumber.java.PendingException();
	}

	/**
	 * クーポン {string} を適用する。
	 *
	 * @param couponCode 券種コード
	 */
	@もし("クーポン {string} を適用する")
	public void applyCoupon(String couponCode) {
		throw new io.cucumber.java.PendingException();
	}

	/**
	 * クーポンの適用は失敗する。
	 */
	@ならば("クーポンの適用は失敗する")
	public void couponApplicationFails() {
		throw new io.cucumber.java.PendingException();
	}

	/**
	 * エラーメッセージ {string} が表示される。
	 *
	 * @param errorMessage エラーメッセージ
	 */
	@ならば("エラーメッセージ {string} が表示される")
	public void errorMessageIsShown(String errorMessage) {
		throw new io.cucumber.java.PendingException();
	}

	/**
	 * 割引額は {int} 円である。
	 *
	 * @param discountAmount 割引額（円）
	 */
	@ならば("割引額は {int} 円である")
	public void discountAmountIs(int discountAmount) {
		throw new io.cucumber.java.PendingException();
	}

	/**
	 * 支払金額は {int} 円である。
	 *
	 * @param paymentAmount 支払金額（円）
	 */
	@ならば("支払金額は {int} 円である")
	public void paymentAmountIs(int paymentAmount) {
		throw new io.cucumber.java.PendingException();
	}

}
