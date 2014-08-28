package jp.sourceforge.reflex.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 * Deflate圧縮・解凍ユーティリティ.
 * <p>
 * バイト配列をDeflate圧縮・解凍します。<br>
 * <br>
 * Deflaterのメモリ管理について、zlibをJNIで呼び出しているようです。<br>
 * DeflaterのnewのタイミングでJVMの管理外の領域に少なくとも辞書窓サイズ分のメモリを確保しているようです。
 * (Deflateの辞書窓サイズはだいたい32KiBぐらいあるようです。)
 * JVMの外のシステムのメモリを使用するため、使い終わったら必ずend()メソッドを呼び出してください。<br>
 * スレッドセーフではないので、スレッドごとに本ユーティリティを生成して使用してください。<br>
 * もしくはstaticメソッドを使用してください。
 * </p>
 */
public class DeflateUtil {

	/** デフォルト圧縮レベル */
	public static final int DEFAULT_LEVEL = Deflater.BEST_SPEED;
	/** デフォルト nowrap */
	public static final boolean DEFAULT_NOWRAP = true;
	
	private static final int BUF_SIZE = 4096;
	
	private int level;
	private boolean nowrap;
	private Deflater def;
	private Inflater inf;
	
	/**
	 * コンストラクタ.
	 * <p>
	 * 圧縮レベル、GZIP互換かどうかはデフォルトの設定を使用します。
	 * </p>
	 */
	public DeflateUtil() {
		this(DEFAULT_LEVEL, DEFAULT_NOWRAP);
	}

	/**
	 * コンストラクタ.
	 * @param level 圧縮レベル (0 〜 9)
	 * @param nowrap true の場合は GZIP 互換の圧縮を使用
	 */
	public DeflateUtil(int level, boolean nowrap) {
		this.level = level;
		this.nowrap = nowrap;
	}
	
	/**
	 * コンプレッサを閉じ、圧縮解除された入力をすべて破棄します。
	 */
	public void end() {
		if (def != null) {
			try {
				def.end();
			} catch (Exception e) {}	// Do nothing.
		}
		if (inf != null) {
			try {
				inf.end();
			} catch (Exception e) {}	// Do nothing.
		}
	}
	
	/**
	 * 圧縮.
	 * <p>
	 * Deflater生成、圧縮、JNIメモリ解放を行います。<br>
	 * 圧縮レベル、nowrapについてはデフォルトの値を使用します。
	 * </p>
	 * @param dataByte 圧縮対象データ
	 * @return deflate圧縮データ
	 */
	public static byte[] deflateOneTime(byte[] dataByte) 
	throws IOException {
		return deflateOneTime(dataByte, DEFAULT_LEVEL, DEFAULT_NOWRAP);
	}
	
	/**
	 * 圧縮.
	 * <p>
	 * Deflater生成、圧縮、JNIメモリ解放を行います。
	 * </p>
	 * @param dataByte 圧縮対象データ
	 * @param level 圧縮レベル (0 〜 9)
	 * @param nowrap true の場合は GZIP 互換の圧縮を使用
	 * @return deflate圧縮データ
	 */
	public static byte[] deflateOneTime(byte[] dataByte, int level, boolean nowrap) 
	throws IOException {
		DeflateUtil deflateUtil = null;
		try {
			deflateUtil = new DeflateUtil(level, nowrap);
			return deflateUtil.deflate(dataByte);
			
		} finally {
			if (deflateUtil != null) {
				deflateUtil.end();
			}
		}
	}
	
	/**
	 * 解凍.
	 * <p>
	 * Inflater生成、解凍、JNIメモリ解放を行います。<br>
	 * nowrapについてはデフォルトの値を使用します。
	 * </p>
	 * @param dataByte deflate圧縮データ
	 * @return 解凍データ
	 */
	public static byte[] inflateOneTime(byte[] dataByte) 
	throws IOException, DataFormatException {
		return inflateOneTime(dataByte, DEFAULT_NOWRAP);
	}
	
	/**
	 * 解凍.
	 * <p>
	 * Inflater生成、解凍、JNIメモリ解放を行います。<br>
	 * </p>
	 * @param dataByte deflate圧縮データ
	 * @param nowrap true の場合は GZIP 互換の圧縮を使用
	 * @return 解凍データ
	 */
	public static byte[] inflateOneTime(byte[] dataByte, boolean nowrap) 
	throws IOException, DataFormatException {
		DeflateUtil deflateUtil = null;
		try {
			deflateUtil = new DeflateUtil(DEFAULT_LEVEL, nowrap);
			return deflateUtil.inflate(dataByte);
			
		} finally {
			if (deflateUtil != null) {
				deflateUtil.end();
			}
		}
		
	}

	/**
	 * 圧縮する
	 * 
	 * @param dataByte
	 * @return deflated bytes
	 * @throws IOException
	 */
	public byte[] deflate(byte[] dataByte) 
	throws IOException {
		if (dataByte == null || dataByte.length == 0) {
			return null;
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream(
				dataByte.length);
		/*
		try {
			if (def == null) {
				def = new Deflater(level, nowrap);
			}
			def.setInput(dataByte);
			def.finish();

			byte[] buf = new byte[BUF_SIZE];
			while (!def.finished()) {
				int compByte = def.deflate(buf, 0, BUF_SIZE, Deflater.SYNC_FLUSH);
				out.write(buf, 0, compByte);
			}
		
		} finally {
			if (def != null) {
				try {
					def.reset();
				} catch (Exception e) {}	// Do nothing.
			}
			try {
				out.close();
			} catch (Exception e) {}	// Do nothing.
		}
		*/
		
		/*
		DeflaterInputStream inStream = null;
		try {
			if (def == null) {
				def = new Deflater(level, nowrap);
			}
			
			inStream = new DeflaterInputStream(
					new ByteArrayInputStream(dataByte), def);
			
			byte[] buf = new byte[BUF_SIZE];
			int size;
			while ((size = inStream.read(buf)) > 0) {
				out.write(buf, 0, size);
			}

		} finally {
			if (def != null) {
				try {
					def.reset();
				} catch (Exception e) {}	// Do nothing.
			}
			if (inStream != null) {
				try {
					inStream.close();
				} catch (Exception e) {}	// Do nothing.
			}
			try {
				out.close();
			} catch (Exception e) {}	// Do nothing.
		}
		*/
		
		// Androidでは DeflaterOutputStream を使用するのが効率が良いらしい。
		DeflaterOutputStream dout = null;
		try {
			if (def == null) {
				def = new Deflater(level, nowrap);
			}
			dout = new DeflaterOutputStream(out, def, BUF_SIZE);
			dout.write(dataByte);
			
		} finally {
			if (dout != null) {
				dout.close();
			}
		}
		
		return out.toByteArray();
	}

	/**
	 * 解凍する
	 * 
	 * @param dataByte
	 * @return inflated bytes
	 * @throws IOException
	 * @throws DataFormatException
	 */
	public byte[] inflate(byte[] dataByte) 
	throws IOException, DataFormatException {
		if (dataByte == null || dataByte.length == 0) {
			return null;
		}

		// 1バイト余分な領域を加える必要がある。
		byte[] src = null;
		if (nowrap) {
			src = new byte[dataByte.length + 1];
			System.arraycopy(dataByte, 0, src, 0, dataByte.length);
			src[dataByte.length] = 0;
		} else {
			src = dataByte;
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		/*
		try {
			if (inf == null) {
				inf = new Inflater(nowrap);
			}
			inf.setInput(src);

			byte[] buf = new byte[BUF_SIZE];
			while (!inf.finished()) {
				int resultByte = inf.inflate(buf, 0, BUF_SIZE);
				out.write(buf, 0, resultByte);
			}

		} finally {
			if (inf != null) {
				try {
					inf.reset();
				} catch (Exception e) {}	// Do nothing.
			}
			try {
				out.close();
			} catch (Exception e) {}	// Do nothing.
		}
		*/
		
		InflaterInputStream inStream = null;
		try {
			if (inf == null) {
				inf = new Inflater(nowrap);
			}
			inStream = new InflaterInputStream(
					new ByteArrayInputStream(src), inf, BUF_SIZE);
			
			byte[] buf = new byte[BUF_SIZE];
			int size;
			while ((size = inStream.read(buf)) > 0) {
				out.write(buf, 0, size);
			}

		} finally {
			if (inf != null) {
				try {
					inf.reset();
				} catch (Exception e) {}	// Do nothing.
			}
			if (inStream != null) {
				try {
					inStream.close();
				} catch (Exception e) {}	// Do nothing.
			}
			try {
				out.close();
			} catch (Exception e) {}	// Do nothing.
		}
		
		return out.toByteArray();
	}

}
