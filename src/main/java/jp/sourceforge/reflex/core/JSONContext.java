package jp.sourceforge.reflex.core;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.Locale;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.sourceforge.reflex.util.DateUtil;

/**
 * @author Takezaki
 *
 */
public class JSONContext {

	/**
	 * bit1 is on
	 */
	public final int STARTMARK = 0x02;

	/**
	 * bit2 is on
	 */
	public final int ENDMARK = 0x04;

	/**
	 * bit0 is on
	 */
	public final int HASKEY = 0x01;

	/**
	 * bit0 is off
	 */
	public final int ARRAY = 0; // [

	/**
	 * bit0 is on (it means 'has key')
	 */
	public final int HASH = 1; // {

	/**
	 * bit1 is on (it also has a key)
	 */
	public final int ARRAYS = 3;

	/**
	 * bit2 is on
	 */
	public final int ARRAYE = 4;

	/**
	 * bit0,1,2 is on(it also has a key)
	 */
	public final int ARRAYSE = 7;

	/**
	 * bit0 is off (Two-dimensional array)
	 */
	public final int ARRAY2 = 96;

	/**
	 * bit1 is on (it also has a key) (Two-dimensional array)
	 */
	public final int ARRAY2S = 107;

	/**
	 * bit0,1,2 is on(it also has a key) (Two-dimensional array)
	 */
	public final int ARRAY2SE = 103;

	/**
	 * bit0,1,2 is on(it also has a key) (One-dimensional array)
	 */
	public final int ARRAY2SE0 = 111;

	/**
	 * bit0,1,2 is on(it also has a key) (Two-dimensional array)
	 */
	public final int ARRAY2CS = 119;

	/**
	 * bit1 is on (it also has a key) (One-dimensional array)
	 */
	public final int ARRAY2S0 = 115;

	/**
	 * bit1,2 is on (Two-dimensional array)
	 */
	public final int ARRAY2CE = 102;

	/**
	 * bit1,2 is on bit1,2 is on (Two-dimensional array)
	 */
	public final int ARRAY2CSE = 110;

	/**
	 * bit1 is on (Two-dimensional array)
	 */
	public final int ARRAY2SC = 106;

	/**
	 * bit2 is on (Two-dimensional array)
	 */
	public final int ARRAY2E = 108;

	/**
	 * bit2 is on (Two-dimensional array)
	 */
	public final int ARRAY2EC = 116;

	/**
	 * bit2 is on (One-dimensional array)
	 */
	public final int ARRAY2E0 = 124;

	/**
	 * separator = { "[", "]", "{", "}" }; String
	 */
	private final String[] separator = { "[", "]", "{", "}" };

	/**
	 * stack = new Stack(); Stack
	 */
	private Stack stack = new Stack();

	/**
	 * out; Writer
	 */
	private Writer out;

	/**
	 * lastout; String
	 */
	private String lastout;

	// Date/Time ISO8601 TIME ZONE FORMAT 2006-02-10T10:00Z.
	// for use: String string = XX.isoformat.format(date);
	//private SimpleDateFormat isoformat = new SimpleDateFormat(
	//		"yyyy-MM-dd'T'HH:mm:ssZZ");
	private SimpleDateFormat isoformat = new SimpleDateFormat(
			DateUtil.FORMAT_PATTERN);

	/**
	 * @return int
	 */
	public int mode() {
		try {
			return ((Integer) this.stack.peek()).intValue();
		} catch (EmptyStackException e) {
			return -1;
		}
	}

	/**
	 * Quoate
	 *
	 */
	public String Q;

	/**
	 * Flag
	 *
	 */
	public boolean F;

	/**
	 * dispChildNumFlag
	 *
	 */
	public boolean dispChildNum;
	
	/**
	 * @param out
	 *            Write
	 */
	public JSONContext(Writer out, String quate,boolean flag,boolean dispChildNum) {
		this.out = out;
		this.Q = quate;
		this.F = flag;
		this.dispChildNum = dispChildNum;
	}

	/**
	 * @return String
	 */
	public String getLastout() {
		return lastout;
	}

	/**
	 * @return boolean
	 */
	public boolean hasKey() {
		return (this.mode() & HASKEY) > 0;
	}

	/**
	 * @param value
	 *            int
	 * @return String
	 */
	public String getSeparator(int value) {
		return separator[(value & 0x01) * 2 + 1];
	}

	/**
	 * @return boolean
	 */
	public boolean isHash() {
		return (this.mode() & 0x01) == 1;
	}

	/**
	 * @param mode
	 *            int
	 */
	public void push(int mode) {
		this.stack.push(new Integer(mode));
	}

	/**
	 * @throws IOException
	 *             pushout
	 */
	public void pushout() throws IOException {
		if ((this.mode() & STARTMARK) > 0)
			this.outprint(separator[ARRAY]);
		if (this.mode() == ARRAY2S || this.mode() == ARRAY2SE
				|| this.mode() == ARRAY2CS)
			this.outprint(separator[ARRAY]);
		if (this.mode() < ARRAY2)
			this.outprint(separator[HASH * 2]);
	}

	/**
	 * @throws IOException
	 *             popout
	 */
	public void popout() throws IOException {
		int lastmode = ((Integer) this.stack.pop()).intValue();

		if (lastmode < ARRAY2)
			this.outprint(separator[HASH * 2 + 1]);
		if ((lastmode & ENDMARK) > 0)
			this.outprint(separator[ARRAY + 1]);
		if (lastmode == ARRAY2E || lastmode == ARRAY2CE || lastmode == ARRAY2SE)
			this.outprint(separator[ARRAY + 1]);
	}

	/**
	 * @param source
	 *            Object
	 * @throws IOException
	 *             printClassName
	 */
	public void printClassName(Object source) throws IOException {

		printNodeName(source.getClass().getName());

	}

	/**
	 * @param nodename
	 *            String
	 * @throws IOException
	 *             printClassName
	 */
	public void printNodeName(String nodename) throws IOException {

		if (hasKey()) {
			nodename = nodename.substring(nodename.lastIndexOf(".") + 1);
			nodename = nodename.substring(0, 1).toLowerCase(Locale.ENGLISH)
					+ nodename.substring(1);

			this.outprint(this.Q + nodename + this.Q + " : ");
		}
	}

	/**
	 * @param value
	 *            Object
	 * @throws IOException
	 *             exception
	 */
	private void outprint(Object value) throws IOException {
		lastout = "" + value;
		out.write("" + value);
	}

	/**
	 * @throws IOException
	 *             exception outcomma
	 */
	public void outcomma() throws IOException {
		// if (!lastout.equals("[") && !lastout.equals("{"))
		if (lastout!=null&&!lastout.equals("[") && !lastout.equals("{")
				&& !lastout.equals(","))
			this.outprint(",");
	}

	/**
	 * @param key
	 *            String
	 * @param value
	 *            String
	 * @throws IOException
	 *             exception
	 */
	
	public void out(String key, String value) throws IOException {
		if (value != null&&key.indexOf("_$xml")<0) {
					// for reserved words
					if (!F) {
						if (key.charAt(0)=='_')
							key = key.substring(1).replace("$", "___");;
					}else {
					if (key.startsWith("_")&&!key.startsWith("_$")) {
						key = key.substring(1);
					}
						key = key.replace("$", "___");
					}
					outcomma();
					this.outprint(this.Q + key + this.Q + " : " + this.Q
					+ escape(value) + this.Q);
		}
	}

	/**
	 * @param value
	 *            String
	 * @throws IOException
	 *             exception
	 */
	public void out(String value) throws IOException {
		if (value != null) {
			outcomma();
			this.outprint(this.Q + escape(value) + this.Q);
		}
	}

	/**
	 * @param key
	 *            String
	 * @param value
	 *            int
	 * @throws IOException
	 *             exception
	 */
	public void out(String key, int value) throws IOException {
		outcomma();
		// for reserved words
		if (!F) {
			if (key.charAt(0)=='_')
				key = key.substring(1).replace("$", "___");;
		}else {
		if (key.startsWith("_")&&!key.startsWith("_$")) {
			key = key.substring(1);
		}
			key = key.replace("$", "___");
		}
		this.outprint(this.Q + key + this.Q + " : " + value);
	}

	/**
	 * @param key
	 *            String
	 * @param value
	 *            long
	 * @throws IOException
	 *             exception
	 */
	public void out(String key, long value) throws IOException {
		outcomma();
		// for reserved words
		if (!F) {
			if (key.charAt(0)=='_')
				key = key.substring(1).replace("$", "___");;
		}else {
		if (key.startsWith("_")&&!key.startsWith("_$")) {
			key = key.substring(1);
		}
			key = key.replace("$", "___");
		}
		this.outprint(this.Q + key + this.Q + " : " + value);
	}

	/**
	 * @param key
	 *            String
	 * @param value
	 *            float
	 * @throws IOException
	 *             exception
	 */
	public void out(String key, float value) throws IOException {
		outcomma();
		// for reserved words
		if (!F) {
			if (key.charAt(0)=='_')
				key = key.substring(1).replace("$", "___");;
		}else {
		if (key.startsWith("_")&&!key.startsWith("_$")) {
			key = key.substring(1);
		}
			key = key.replace("$", "___");
		}
		this.outprint(this.Q + key + this.Q + " : " + value);
	}

	/**
	 * @param key
	 *            String
	 * @param value
	 *            double
	 * @throws IOException
	 *             exception
	 */
	public void out(String key, double value) throws IOException {
		outcomma();
		// for reserved words
		if (!F) {
			if (key.charAt(0)=='_')
				key = key.substring(1).replace("$", "___");;
		}else {
		if (key.startsWith("_")&&!key.startsWith("_$")) {
			key = key.substring(1);
		}
			key = key.replace("$", "___");
		}
		this.outprint(this.Q + key + this.Q + " : " + value);
	}

	/**
	 * @param key
	 *            String
	 * @param value
	 *            boolean
	 * @throws IOException
	 *             exception
	 */
	public void out(String key, boolean value) throws IOException {
		outcomma();
		// for reserved words
		if (!F) {
			if (key.charAt(0)=='_')
			key = key.substring(1).replace("$", "___");;
		}else {
		if (key.startsWith("_")&&!key.startsWith("_$")) {
			key = key.substring(1);
		}
			key = key.replace("$", "___");
		}
		this.outprint(this.Q + key + this.Q + " : " + value);
	}

	public void outarraynull(String key) throws IOException {
		outcomma();
		this.outprint(this.Q + key + this.Q + " : []");
	}

	/**
	 * xorPlural() {
	 */
	public void xorPlural() {
		int mode = ((Integer) this.stack.pop()).intValue();
		mode = mode ^ 0x2;
		this.stack.push(new Integer(mode));
	}

	private static final String[] CNTLCHRS = {
		"\\u0000", "\\u0001", "\\u0002", "\\u0003", "\\u0004", "\\u0005", "\\u0006", "\\u0007",
		"\\b", "\\t", "\\n", "\\u000B", "\\f","\\r", "\\u000E", "\\u000F",
		"\\u0010", "\\u0011", "\\u0012", "\\u0013", "\\u0014", "\\u0015", "\\u0016", "\\u0017", 
		"\\u0018", "\\u0019", "\\u001A", "\\u001B", "\\u001C", "\\u001D", "\\u001E", "\\u001F"
	};

	public static String escape(String s) {

		StringBuilder sb = new StringBuilder();

			for (int i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					switch (c) {
//					サーバからJSONで出力する場合には、バックスラッシュ自体がエスケープ文字とみなされてしまうため、「\\n」のようにバックスラッシュをエスケープする必要がある。
					case '"':
						sb.append("\\\"");
						break;
//					case '\'':
//						sb.append("\\\\'");
//						break;
					case '\\': 	// \
						sb.append("\\\\");
						break;
					case '\u007F': 
						sb.append("\\u007F");
						break;
					case '<':
						sb.append("\\u003C");
						break;
					case '>':
						sb.append("\\u003E");
						break;
					case '\u2028': 
						sb.append("\\u2028");
						break;
					case '\u2029': 
						sb.append("\\u2029");
						break;
					default:
						if (c < CNTLCHRS.length) {
							sb.append(CNTLCHRS[c]);
						} else {
							sb.append(c);
						}
					}
			}
			
		return sb.toString();
	}

	/**
	 * @param date
	 *            Date
	 * @return String
	 */
	public String dateformat(Date date) {

		return isoformat.format(date);

	}

	// for Java 1.4 users
	/**
	 * @param org
	 *            String
	 * @param src
	 *            String
	 * @param tgt
	 *            String
	 * @return String
	 */
	public String replace(String org, String src, String tgt) {

		if (org == null)
			return null;
		Pattern pattern = Pattern.compile(src);
		Matcher matcher = pattern.matcher(org);
		return matcher.replaceAll(tgt);

	}


}
