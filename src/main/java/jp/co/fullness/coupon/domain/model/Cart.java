package jp.co.fullness.coupon.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 買い物かご。
 */
@Getter
@RequiredArgsConstructor
public class Cart {

	/** 購入金額 */
	private final Money totalAmount;

	/** 会員ランク */
	private final MembershipRank membershipRank;

}
