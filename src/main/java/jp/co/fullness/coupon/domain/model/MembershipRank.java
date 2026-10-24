package jp.co.fullness.coupon.domain.model;

import java.util.Arrays;

import jp.co.fullness.coupon.domain.exception.DomainException;

/**
 * 会員ランク。
 */
public enum MembershipRank {

	/** 一般 */
	GENERAL("一般"),

	/** ゴールド */
	GOLD("ゴールド"),

	/** プラチナ */
	PLATINUM("プラチナ");

	/** 会員ランクの名称 */
	private final String displayName;

	MembershipRank(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 会員ランクの名称から、会員ランクを得る。
	 *
	 * @param displayName 会員ランクの名称（一般・ゴールド・プラチナ）
	 * @return 会員ランク
	 * @throws DomainException 該当する会員ランクの名称が無い場合
	 */
	public static MembershipRank of(String displayName) {
		return Arrays.stream(values())
				.filter(rank -> rank.displayName.equals(displayName))
				.findFirst()
				.orElseThrow(() -> new DomainException("該当する会員ランクの名称がありません: " + displayName));
	}

}
