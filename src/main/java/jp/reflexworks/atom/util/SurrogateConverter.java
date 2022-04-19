package jp.reflexworks.atom.util;

public class SurrogateConverter {

	private String src;
	private String tgt;
	private int idx;
	private int ucs;
	private char c1;
	private char c2;

	private SurrogateConverter() {
	}

	public SurrogateConverter(String src) {
		this.src = src;
		this.tgt = "";
	}

	public String convertUcs() {
		convert();
		return tgt;
	}

	private void convert() {

		if (src.length() > idx) {
			ucs = src.substring(idx).indexOf("\\u");
			if (ucs >= 0) {
				tgt += src.substring(idx, idx + ucs);
				c1 = (char) Integer.parseInt(src.substring(idx + ucs + 2, idx + ucs + 6), 16);
				if ((c1 >= 0xD800) && (c1 <= 0xDFFF) && (src.substring(idx + ucs + 6).indexOf("\\u") == 0)) { 																												
					c2 = (char) Integer.parseInt(src.substring(idx + ucs + 8, idx + ucs + 12), 16);  // Surrogate Pair
					int codepoint = Character.toCodePoint(c1, c2);
					tgt += String.format("%c", codepoint);
					idx += ucs + 12;
					convert();
				} else {
					tgt += String.format("%c", c1); // codepoint
					idx += ucs + 6;
					convert();
				}
			} else {
				tgt += src.substring(idx);
			}
		}
		return;
	}

}