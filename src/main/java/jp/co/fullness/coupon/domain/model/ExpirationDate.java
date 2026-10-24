package jp.co.fullness.coupon.domain.model;

import java.time.LocalDate;

/**
 * 有効期限。
 * 当日まで有効。
 *
 * @param date 有効期限の日付
 */
public record ExpirationDate(LocalDate date) {

	/**
	 * 指定した日が有効期限内か。
	 *
	 * @param targetDate 判定する日付
	 * @return 判定する日付が有効期限の日付以前である場合は {@code true}
	 */
	public boolean isValidOn(LocalDate targetDate) {
		return !targetDate.isAfter(date);
	}

}
