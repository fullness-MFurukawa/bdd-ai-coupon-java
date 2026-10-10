package jp.co.fullness.coupon.domain.model;

import java.time.LocalDate;

/**
 * 有効期限。
 * 当日まで有効。
 *
 * @param date 有効期限の日付
 */
public record ExpirationDate(LocalDate date) {

}
