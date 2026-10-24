package jp.co.fullness.coupon.domain.model;

import jp.co.fullness.coupon.domain.exception.DomainException;

/**
 * 金額。
 * 負の値を許さない。
 *
 * @param amount 金額の値（円単位）
 */
public record Money(long amount) {

	public Money {
		if (amount < 0) {
			throw new DomainException("金額に負の値は指定できません: " + amount);
		}
	}

	/**
	 * 金額が他の金額より少ないか。
	 *
	 * @param other 比較する金額
	 * @return この金額が比較する金額より少ない場合は {@code true}
	 */
	public boolean isLessThan(Money other) {
		return amount < other.amount;
	}

}
