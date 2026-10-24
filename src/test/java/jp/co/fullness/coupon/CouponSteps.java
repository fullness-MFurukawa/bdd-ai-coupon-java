package jp.co.fullness.coupon;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.cucumber.java.ja.ならば;
import io.cucumber.java.ja.もし;
import io.cucumber.java.ja.前提;
import jp.co.fullness.coupon.domain.model.Cart;
import jp.co.fullness.coupon.domain.model.Coupon;
import jp.co.fullness.coupon.domain.model.CouponApplicationResult;
import jp.co.fullness.coupon.domain.model.CouponCode;
import jp.co.fullness.coupon.domain.model.ExpirationDate;
import jp.co.fullness.coupon.domain.model.MembershipRank;
import jp.co.fullness.coupon.domain.model.Money;
import jp.co.fullness.coupon.domain.service.DiscountCalculationService;

/**
 * クーポンによる割引の適用のステップ定義。
 */
public class CouponSteps {

	/** .feature に記述された日付の書式 */
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/** .feature に記述された日時の書式 */
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/** 割引計算 */
	private final DiscountCalculationService discountCalculationService = new DiscountCalculationService();

	/** 券種コード */
	private CouponCode couponCode;

	/** 最低購入金額 */
	private Money minimumPurchaseAmount;

	/** 割引上限額 */
	private Money maximumDiscountAmount;

	/** 有効期限 */
	private ExpirationDate expirationDate;

	/** ポイントの換算における、ポイント数 */
	private int pointRatePoints;

	/** ポイントの換算における、ポイント数に相当する金額（円） */
	private int pointRateYen;

	/** 現在日時 */
	private LocalDateTime now;

	/** 会員ランク */
	private MembershipRank membershipRank;

	/** 購入金額 */
	private Money totalAmount;

	/** 適用結果 */
	private CouponApplicationResult result;

	/**
	 * クーポン {string} が存在する。
	 *
	 * @param couponCode 券種コード
	 */
	@前提("クーポン {string} が存在する")
	public void couponExists(String couponCode) {
		this.couponCode = new CouponCode(couponCode);
	}

	/**
	 * クーポン {string} の最低購入金額は {int} 円である。
	 *
	 * @param couponCode 券種コード
	 * @param minimumPurchaseAmount 最低購入金額（円）
	 */
	@前提("クーポン {string} の最低購入金額は {int} 円である")
	public void couponHasMinimumPurchaseAmount(String couponCode, int minimumPurchaseAmount) {
		this.minimumPurchaseAmount = new Money(minimumPurchaseAmount);
	}

	/**
	 * クーポン {string} の割引上限額は {int} 円である。
	 *
	 * @param couponCode 券種コード
	 * @param maximumDiscountAmount 割引上限額（円）
	 */
	@前提("クーポン {string} の割引上限額は {int} 円である")
	public void couponHasMaximumDiscountAmount(String couponCode, int maximumDiscountAmount) {
		this.maximumDiscountAmount = new Money(maximumDiscountAmount);
	}

	/**
	 * クーポン {string} の有効期限は {string} である。
	 *
	 * @param couponCode 券種コード
	 * @param expirationDate 有効期限
	 */
	@前提("クーポン {string} の有効期限は {string} である")
	public void couponExpiresOn(String couponCode, String expirationDate) {
		this.expirationDate = new ExpirationDate(LocalDate.parse(expirationDate, DATE_FORMATTER));
	}

	/**
	 * 割引額の端数は切り捨てる。
	 */
	@前提("割引額の端数は切り捨てる")
	public void discountFractionIsTruncated() {
		// 現時点のシナリオの判定には関わらず、受け取る値も無いため、何もしない
	}

	/**
	 * ポイントは {int} ポイントを {int} 円として計算する。
	 *
	 * @param points ポイント数
	 * @param yen ポイントに相当する金額（円）
	 */
	@前提("ポイントは {int} ポイントを {int} 円として計算する")
	public void pointRateIs(int points, int yen) {
		this.pointRatePoints = points;
		this.pointRateYen = yen;
	}

	/**
	 * 現在日時は {string} である。
	 *
	 * @param now 現在日時
	 */
	@前提("現在日時は {string} である")
	public void currentDateTimeIs(String now) {
		this.now = LocalDateTime.parse(now, DATE_TIME_FORMATTER);
	}

	/**
	 * ポイントを利用しない。
	 */
	@前提("ポイントを利用しない")
	public void pointsAreNotUsed() {
		// 現時点のシナリオの判定には関わらず、受け取る値も無いため、何もしない
	}

	/**
	 * 会員ランクは {string} である。
	 *
	 * @param membershipRank 会員ランクの名称
	 */
	@前提("会員ランクは {string} である")
	public void membershipRankIs(String membershipRank) {
		this.membershipRank = MembershipRank.of(membershipRank);
	}

	/**
	 * カートの購入金額は {int} 円である。
	 *
	 * @param totalAmount 購入金額（円）
	 */
	@前提("カートの購入金額は {int} 円である")
	public void cartTotalAmountIs(int totalAmount) {
		this.totalAmount = new Money(totalAmount);
	}

	/**
	 * クーポン {string} を適用する。
	 *
	 * @param couponCode 券種コード
	 */
	@もし("クーポン {string} を適用する")
	public void applyCoupon(String couponCode) {
		Coupon coupon = new Coupon(this.couponCode, minimumPurchaseAmount, maximumDiscountAmount, expirationDate);
		Cart cart = new Cart(totalAmount, membershipRank);
		result = discountCalculationService.apply(coupon, cart, now);
	}

	/**
	 * クーポンの適用は失敗する。
	 */
	@ならば("クーポンの適用は失敗する")
	public void couponApplicationFails() {
		assertThat(result.successful()).isFalse();
	}

	/**
	 * エラーメッセージ {string} が表示される。
	 *
	 * @param errorMessage エラーメッセージ
	 */
	@ならば("エラーメッセージ {string} が表示される")
	public void errorMessageIsShown(String errorMessage) {
		assertThat(result.errorMessage()).isEqualTo(errorMessage);
	}

	/**
	 * 割引額は {int} 円である。
	 *
	 * @param discountAmount 割引額（円）
	 */
	@ならば("割引額は {int} 円である")
	public void discountAmountIs(int discountAmount) {
		assertThat(result.discountAmount()).isEqualTo(new Money(discountAmount));
	}

	/**
	 * 支払金額は {int} 円である。
	 *
	 * @param paymentAmount 支払金額（円）
	 */
	@ならば("支払金額は {int} 円である")
	public void paymentAmountIs(int paymentAmount) {
		assertThat(result.paymentAmount()).isEqualTo(new Money(paymentAmount));
	}

}
