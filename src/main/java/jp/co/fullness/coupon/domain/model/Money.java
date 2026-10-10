package jp.co.fullness.coupon.domain.model;

/**
 * 金額。
 * 負の値を許さない。
 *
 * @param amount 金額の値（円単位）
 */
public record Money(long amount) {

}
