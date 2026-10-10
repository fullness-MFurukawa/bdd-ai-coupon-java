package jp.co.fullness.coupon.domain.model;

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
	 */
	public static MembershipRank of(String displayName) {
		throw new UnsupportedOperationException("未実装");
	}

}
