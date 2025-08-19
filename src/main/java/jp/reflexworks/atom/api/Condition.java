package jp.reflexworks.atom.api;

import jp.sourceforge.reflex.util.StringUtils;

/**
 * 検索条件クラス
 */
public class Condition {
	
	/** 演算子 : equal (=) */
	public static final String EQUAL = "eq";
	/** 演算子 : greater than (>) */
	public static final String GREATER_THAN = "gt";
	/** 演算子 : greater than or equal (>=) */
	public static final String GREATER_THAN_OR_EQUAL = "ge";
	/** 演算子 : less than (<) */
	public static final String LESS_THAN = "lt";
	/** 演算子 : less than or equal (<=) */
	public static final String LESS_THAN_OR_EQUAL = "le";
	/** 演算子 : not equal (!=) */
	public static final String NOT_EQUAL = "ne";
	/** 演算子 : 正規表現 */
	public static final String REGEX = "rg";
	/** 演算子 : 前方一致 */
	public static final String FORWARD_MATCH = "fm";
	/** 演算子 : 後方一致 */
	public static final String BACKWARD_MATCH = "bm";
	/** 演算子 : 全文検索 */
	public static final String FULL_TEXT_SEARCH = "ft";
	/** 演算子 : OR */
	public static final String OR = "|";
	/** 演算子 : OR開始 */
	public static final String OR_START = OR + "(";
	/** 演算子 : OR終了 */
	public static final String OR_END = ")";
	/** ソート演算子 : 昇順 */
	public static final String ASC = "asc";
	/** ソート演算子 : 降順 */
	public static final String DESC = "desc";
	/** 全文検索の値AND指定 */
	public static final String FT_AND = ",";

	// 互換性のためfinalにしない。(継承クラスで編集)
	/** 演算子の接続文字 */
	public static String DELIMITER = "-";
	
	/** 項目名 */
	protected String prop;
	
	/** 等・不等式 */
	protected String equations;
	
	/** 値 (デコード済) */
	protected String value;
	
	/**
	 * コンストラクタ
	 * @param cond 条件
	 */
	public Condition(String cond) {
		setCondition(cond);
	}

	/**
	 * コンストラクタ
	 * @param prop 項目
	 * @param value 値
	 */
	public Condition(String prop, String value) {
		setCondition(prop, value);
	}

	/**
	 * コンストラクタ
	 * @param prop 項目
	 * @param equations 等・不等式
	 * @param value 値
	 */
	public Condition(String prop, String equations, String value) {
		this.prop = prop;
		this.equations = equations;
		this.value = value;
	}

	/**
	 * 条件の解析
	 * @param cond 条件
	 */
	protected void setCondition(String cond) {
		if (StringUtils.isBlank(cond)) {
			return;
		}
		
		int condLen = cond.length();
		int idxProp = cond.indexOf(DELIMITER);
		int idxFilter = -1;
		if (idxProp == 0) {
			return;	// 項目名が指定されていないので条件としない
		}
		if (idxProp == -1) {
			this.prop = cond;
			idxProp = condLen;
		} else {
			this.prop = cond.substring(0, idxProp);
			idxProp++;
		}
		
		if (idxProp >= condLen) {
			this.equations = EQUAL;
			idxFilter = condLen;
		} else {
			idxFilter = cond.indexOf(DELIMITER, idxProp);
			if (idxFilter == -1) {
				idxFilter = condLen;
			}
			this.equations = cond.substring(idxProp, idxFilter);
			idxFilter++;
		}
		
		if (idxFilter >= condLen) {
			this.value = "";
		} else {
			this.value = cond.substring(idxFilter);
		}
	}
	
	/**
	 * 条件をフィールドに設定
	 * @param prop 項目
	 * @param value 値
	 */
	protected void setCondition(String prop, String value) {
		if (value == null || "".equals(value)) {
			setCondition(prop);
		} else {
			this.prop = prop;
			this.equations = EQUAL;
			this.value = value;
		}
	}

	/**
	 * 項目名を取得
	 * @return 項目名
	 */
	public String getProp() {
		return prop;
	}

	/**
	 * 演算子を取得
	 * @return 演算子
	 */
	public String getEquations() {
		return equations;
	}

	/**
	 * 値を取得
	 * @return 値
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 文字列表現
	 * @return 文字列表現
	 */
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append(prop);
		buf.append("-");
		buf.append(equations);
		if (!ASC.equals(equations) && !DESC.equals(equations) && value != null) {
			buf.append("-");
			buf.append(value);
		}
		return buf.toString();
	}

}
