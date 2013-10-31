package jp.sourceforge.reflex.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class DeflateUtil {
	
	private static final int BUF_SIZE = 4096;
	/**
	 * 圧縮する
	 * 
	 * @param dataByte
	 * @return deflated bytes
	 * @throws IOException
	 */
	public byte[] deflate(byte[] dataByte) throws IOException {

		Deflater def = new Deflater(Deflater.BEST_SPEED,true);	// GZIP 互換の圧縮をサポート(JSとの通信のため）
		def.setInput(dataByte);
		def.finish();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
				dataByte.length);
		byte[] buf = new byte[BUF_SIZE];
		while (!def.finished()) {
			int compByte = def.deflate(buf);
			byteArrayOutputStream.write(buf, 0, compByte);
		}
		byteArrayOutputStream.close();
		def.end(); 	// VM外メモリ解放　http://qiita.com/nishemon/items/031e476de08d623bfded

		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * 解凍する
	 * 
	 * @param dataByte
	 * @return inflated bytes
	 * @throws IOException
	 * @throws DataFormatException
	 */	
	public byte[] inflate(byte[] databyte) {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Inflater inf = new Inflater(true);
		InflaterInputStream inStream = new InflaterInputStream(
				new ByteArrayInputStream(databyte), inf);

		byte[] buf = new byte[BUF_SIZE];
		try {
			while (true) {
				int size = inStream.read(buf);
				if (size <= 0)	break;
				out.write(buf, 0, size);
			}
			out.close();
		} catch (IOException e) {
		}
		inf.end(); 	// メモリ対策

		return out.toByteArray();
	}
}
