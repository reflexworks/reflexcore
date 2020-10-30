package jp.sourceforge.reflex;

import java.text.Normalizer;
import java.util.Locale;

import org.junit.Test;

import jp.sourceforge.reflex.util.StringUtils;

public class NormalizerTest {

	@Test
	public void test() {
		try {
			System.out.println("--- test start ---");

			// 英数字
			String target = "[半角英字]ABCDEabcde[全角英字]ＦＧＨＩＪｆｇｈｉｊ[半角数字]012[全角数字]３４５";
			System.out.println("target :" + target);
			System.out.println("NFC    :" + Normalizer.normalize(target, Normalizer.Form.NFC));
			System.out.println("NFD    :" + Normalizer.normalize(target, Normalizer.Form.NFD));
			System.out.println("NFKC   :" + Normalizer.normalize(target, Normalizer.Form.NFKC));
			System.out.println("NFKD   :" + Normalizer.normalize(target, Normalizer.Form.NFKD));

			// 記号
			System.out.println("--------");
			target = "[半角記号]!\"#$%&'()-=^~\\|@`[]{};:+*,./<>?_[全角記号]！”＃＄％＆’（）ー＝＾〜￥｜＠｀［］｛｝；：＋＊，．／＜＞？＿";
			System.out.println("target :" + target);
			System.out.println("NFC    :" + Normalizer.normalize(target, Normalizer.Form.NFC));
			System.out.println("NFD    :" + Normalizer.normalize(target, Normalizer.Form.NFD));
			System.out.println("NFKC   :" + Normalizer.normalize(target, Normalizer.Form.NFKC));
			System.out.println("NFKD   :" + Normalizer.normalize(target, Normalizer.Form.NFKD));

			// 半角カナ
			System.out.println("--------");
			target = "[半角カナ]ｱｶｻﾀﾅｶﾞｷﾞｸﾞｹﾞｺﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟ[全角カナ]アカサタナガギグゲゴパピプペポ";
			System.out.println("target :" + target);
			System.out.println("NFC    :" + Normalizer.normalize(target, Normalizer.Form.NFC));
			System.out.println("NFD    :" + Normalizer.normalize(target, Normalizer.Form.NFD));
			System.out.println("NFKC   :" + Normalizer.normalize(target, Normalizer.Form.NFKC));
			System.out.println("NFKD   :" + Normalizer.normalize(target, Normalizer.Form.NFKD));

			// 丸囲み文字、組文字
			System.out.println("--------");
			target = "[丸囲み文字、組文字]①②③④⑤㈱〒";
			System.out.println("target :" + target);
			System.out.println("NFC    :" + Normalizer.normalize(target, Normalizer.Form.NFC));
			System.out.println("NFD    :" + Normalizer.normalize(target, Normalizer.Form.NFD));
			System.out.println("NFKC   :" + Normalizer.normalize(target, Normalizer.Form.NFKC));
			System.out.println("NFKD   :" + Normalizer.normalize(target, Normalizer.Form.NFKD));

			System.out.println("--- test end ---");

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test
	public void normalizeTest() {
		try {
			System.out.println("--- normalizeTest start ---");

			// 英数字
			String target = "[半角英字]ABCDEabcde[全角英字]ＦＧＨＩＪｆｇｈｉｊ[半角数字]012[全角数字]３４５";
			String normalized = StringUtils.normalize(target);
			System.out.println("target      :" + target);
			System.out.println("normalized  :" + normalized);
			System.out.println("toLowerCase :" + normalized.toLowerCase(Locale.ENGLISH));

			// 記号
			System.out.println("--------");
			target = "[半角記号]!\"#$%&'()-=^~\\|@`[]{};:+*,./<>?_[全角記号]！”＃＄％＆’（）ー＝＾〜￥｜＠｀［］｛｝；：＋＊，．／＜＞？＿";
			normalized = StringUtils.normalize(target);
			System.out.println("target      :" + target);
			System.out.println("normalized  :" + normalized);
			System.out.println("toLowerCase :" + normalized.toLowerCase(Locale.ENGLISH));

			// 半角カナ
			System.out.println("--------");
			target = "[半角カナ]ｱｶｻﾀﾅｶﾞｷﾞｸﾞｹﾞｺﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟ[全角カナ]アカサタナガギグゲゴパピプペポ";
			normalized = StringUtils.normalize(target);
			System.out.println("target      :" + target);
			System.out.println("normalized  :" + normalized);
			System.out.println("toLowerCase :" + normalized.toLowerCase(Locale.ENGLISH));

			// 丸囲み文字、組文字
			System.out.println("--------");
			target = "[丸囲み文字、組文字]①②③④⑤㈱〒";
			normalized = StringUtils.normalize(target);
			System.out.println("target      :" + target);
			System.out.println("normalized  :" + normalized);
			System.out.println("toLowerCase :" + normalized.toLowerCase(Locale.ENGLISH));

			System.out.println("--- normalizeTest end ---");

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
