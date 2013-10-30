package jp.sourceforge.reflex.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class DeflateUtil {
	
	/**
	 * 圧縮する
	 * 
	 * @param dataByte
	 * @return deflated bytes
	 * @throws IOException
	 */
	public byte[] deflate(byte[] dataByte) throws IOException {

		Deflater def = new Deflater(Deflater.BEST_SPEED,true);	// GZIP 互換の圧縮をサポート(JSとの通信のため）
//		def.setLevel(Deflater.BEST_SPEED);
		def.setInput(dataByte);
		def.finish();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
				dataByte.length);
		byte[] buf = new byte[1024];
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
	public byte[] inflate(byte[] dataByte) throws IOException,
			DataFormatException {

		Inflater inf = new Inflater(true);
		inf.setInput(dataByte);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		while (!inf.finished()) {
			int resultByte = inf.inflate(buf);
			byteArrayOutputStream.write(buf, 0, resultByte);
		}
		byteArrayOutputStream.close();
		inf.end(); 	// メモリ対策

		return byteArrayOutputStream.toByteArray();
	}

}
