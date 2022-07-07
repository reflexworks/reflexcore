package jp.sourceforge.reflex;

/**
 * 例外
 */
public abstract class IReflexException extends Exception {
	
	/**
	 * コンストラクタ
	 */
	public IReflexException() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param cause 原因例外
	 */
	public IReflexException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * コンストラクタ
	 * @param message メッセージ
	 */
	public IReflexException(String message) {
		super(message);
	}
	
	/**
	 * コンストラクタ
	 * @param message メッセージ
	 * @param cause 原因例外
	 */
	public IReflexException(String message, Throwable cause) {
		super(message, cause);
	}

}
