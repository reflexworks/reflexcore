package jp.reflexworks.atom.mapper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.reflexworks.atom.api.Condition;
import jp.reflexworks.atom.entry.Element;
import jp.sourceforge.reflex.util.DateUtil;
import jp.sourceforge.reflex.util.StringUtils;

/**
 * エンティティオブジェクトが検索条件に合致するか調べるために使われるコンテキスト
 *
 */
public class ConditionContext {

	public Condition[] conditions;
	public String fldname;
	public String type;
	public Object obj;
	public String parent;
	public Map<String,Boolean> isMatchs;
	private Boolean[] isFetchs;		// 項目名が一致したか

	public ConditionContext(Condition[] conditions) {
		this.conditions = conditions;
		this.isMatchs = new HashMap<String, Boolean>();
		this.isFetchs = new Boolean[conditions.length];
	}

	/**
	 * 検索条件に合致していたかについてのチェック結果の判定
	 *
	 * @return 合致していればtrue
	 */
	public boolean isMatch() {
		for (Boolean value : isFetchs) {
			if (value==null||!value) return false;
		}

		for (Boolean value : isMatchs.values()) {
			if (!value) return false;
		}
		return true;
	}

	/**
	 * すべての項目の検索条件について合致しているかどうかチェックする
	 *
	 * @param context
	 */
	public static void checkCondition(ConditionContext context) {
		for (int i=0;i<context.conditions.length;i++) {
			Condition cond = context.conditions[i];
			if (cond.getProp().equals(context.fldname)) { // 項目名が一致するかどうか
				Boolean value = context.isMatchs.get(context.fldname+"-"+cond.getEquations()+"-"+i);
				if (value == null || !value) {
					context.isMatchs.put(context.fldname+"-"+cond.getEquations()+"-"+i,
							checkCondition(context.obj, cond, context.type));
				}
				context.isFetchs[i] = true;
			}
		}
	}

	/**
	 * Stringの項目について検索条件に合致しているかどうかチェックする
	 *
	 * @param obj
	 * @param cond
	 * @param type
	 * @return 合致していればtrue
	 */
	private static boolean checkConditionString(String src,Condition cond,String type) {
		String value = cond.getValue();
		String equal = cond.getEquations();

		if (Condition.REGEX.equals(equal)) {
			Pattern pattern = Pattern.compile(value);
			Matcher matcher = pattern.matcher(src);
			if (!matcher.matches()) {
				return false;
			}
		}
		else
		//if (!cond.isPrefixMatching() && !cond.isLikeForward()) {
		if (!Condition.FORWARD_MATCH.equals(equal) && !Condition.BACKWARD_MATCH.equals(equal)) {
			int compare = src.compareTo(value);

			if (Condition.EQUAL.equals(equal) && compare != 0) {
				return false;
			} else if (Condition.NOT_EQUAL.equals(equal) && compare == 0) {
				return false;
			} else if (Condition.GREATER_THAN.equals(equal) && compare <= 0) {
				return false;
			} else if (Condition.GREATER_THAN_OR_EQUAL.equals(equal) && compare < 0) {
				return false;
			} else if (Condition.LESS_THAN.equals(equal) && compare >= 0) {
				return false;
			} else if (Condition.LESS_THAN_OR_EQUAL.equals(equal) && compare > 0) {
				return false;
			}

		//} else if (cond.isPrefixMatching() && cond.isLikeForward()) {
		//	// あいまい検索
		//	if (src.indexOf(value) < 0) {
		//		return false;
		//	}

		//} else if (cond.isPrefixMatching() && !cond.isLikeForward()) {
		} else if (Condition.FORWARD_MATCH.equals(equal)) {
			// 前方一致検索
			if (!src.startsWith(value)) {
				return false;
			}

		} else {
			// 後方一致検索
			if (!src.endsWith(value)) {
				return false;
			}
		}
		return true;
	}


	/**
	 * 個々の項目について検索条件に合致しているかどうかチェックする
	 *
	 * @param obj
	 * @param cond
	 * @param type
	 * @return 合致していればtrue
	 */
	private static boolean checkCondition(Object obj, Condition cond, String type) {
		String equal = cond.getEquations();

		if (obj==null) return false;
		if (type.equals("String")) {
			if (obj instanceof ArrayList) {
				for(Element element:(ArrayList<Element>) obj){
					// 配列要素のどれか一つに合致していればtrue
					if (checkConditionString(element._$$text, cond, type)) return true;
				}
				return false;

			}else {
					return checkConditionString((String)obj, cond, type);
			}
		} else if (type.equals("Integer")) {
			int src = (Integer)obj;
			int value = StringUtils.intValue(cond.getValue());

			if (Condition.EQUAL.equals(equal) && src != value) {
				return false;
			} else if (Condition.NOT_EQUAL.equals(equal) && src == value) {
				return false;
			} else if (Condition.GREATER_THAN.equals(equal) && src <= value) {
				return false;
			} else if (Condition.GREATER_THAN_OR_EQUAL.equals(equal) && src < value) {
				return false;
			} else if (Condition.LESS_THAN.equals(equal) && src >= value) {
				return false;
			} else if (Condition.LESS_THAN_OR_EQUAL.equals(equal) && src > value) {
				return false;
			}

		} else if (type.equals("Long")) {
			long src = (Long)obj;
			long value = StringUtils.longValue(cond.getValue());

			if (Condition.EQUAL.equals(equal) && src != value) {
				return false;
			} else if (Condition.NOT_EQUAL.equals(equal) && src == value) {
				return false;
			} else if (Condition.GREATER_THAN.equals(equal) && src <= value) {
				return false;
			} else if (Condition.GREATER_THAN_OR_EQUAL.equals(equal) && src < value) {
				return false;
			} else if (Condition.LESS_THAN.equals(equal) && src >= value) {
				return false;
			} else if (Condition.LESS_THAN_OR_EQUAL.equals(equal) && src > value) {
				return false;
			}

		} else if (type.equals("Float")) {
			float src = (Float)obj;
			float value = StringUtils.floatValue(cond.getValue());

			if (Condition.EQUAL.equals(equal) && src != value) {
				return false;
			} else if (Condition.NOT_EQUAL.equals(equal) && src == value) {
				return false;
			} else if (Condition.GREATER_THAN.equals(equal) && src <= value) {
				return false;
			} else if (Condition.GREATER_THAN_OR_EQUAL.equals(equal) && src < value) {
				return false;
			} else if (Condition.LESS_THAN.equals(equal) && src >= value) {
				return false;
			} else if (Condition.LESS_THAN_OR_EQUAL.equals(equal) && src > value) {
				return false;
			}

		} else if (type.equals("Double")) {
			double src = (Double)obj;
			double value = StringUtils.doubleValue(cond.getValue());

			if (Condition.EQUAL.equals(equal) && src != value) {
				return false;
			} else if (Condition.NOT_EQUAL.equals(equal) && src == value) {
				return false;
			} else if (Condition.GREATER_THAN.equals(equal) && src <= value) {
				return false;
			} else if (Condition.GREATER_THAN_OR_EQUAL.equals(equal) && src < value) {
				return false;
			} else if (Condition.LESS_THAN.equals(equal) && src >= value) {
				return false;
			} else if (Condition.LESS_THAN_OR_EQUAL.equals(equal) && src > value) {
				return false;
			}

		} else if (type.equals("Date")) {
			long src = ((Date)obj).getTime();
			long value;
			try {
				value = DateUtil.getDate(cond.getValue()).getTime();
			} catch (ParseException e) {
				value = 0;
			}

			if (Condition.EQUAL.equals(equal) && src != value) {
				return false;
			} else if (Condition.NOT_EQUAL.equals(equal) && src == value) {
				return false;
			} else if (Condition.GREATER_THAN.equals(equal) && src <= value) {
				return false;
			} else if (Condition.GREATER_THAN_OR_EQUAL.equals(equal) && src < value) {
				return false;
			} else if (Condition.LESS_THAN.equals(equal) && src >= value) {
				return false;
			} else if (Condition.LESS_THAN_OR_EQUAL.equals(equal) && src > value) {
				return false;
			}

		} else if (type.equals("Boolean")) {
			boolean src = (Boolean) obj;
			boolean value = cond.getValue().equals("true");

			if (Condition.EQUAL.equals(equal) && src != value) {
				return false;
			} else if (Condition.NOT_EQUAL.equals(equal) && src == value) {
				return false;
			}
		}

		return true;
	}

}
