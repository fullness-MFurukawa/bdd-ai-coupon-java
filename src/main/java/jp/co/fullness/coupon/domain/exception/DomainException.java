package jp.co.fullness.coupon.domain.exception;

/**
 * 業務ルール違反。
 * 値オブジェクトやエンティティの制約に反する値を渡された場合に投げる。
 */
public class DomainException extends RuntimeException {

	/**
	 * 業務ルール違反を生成する。
	 *
	 * @param message 開発者に向けたメッセージ
	 */
	public DomainException(String message) {
		super(message);
	}

	/**
	 * 原因となる例外を包んで、業務ルール違反を生成する。
	 *
	 * @param message 開発者に向けたメッセージ
	 * @param cause 原因となる例外
	 */
	public DomainException(String message, Throwable cause) {
		super(message, cause);
	}

}
