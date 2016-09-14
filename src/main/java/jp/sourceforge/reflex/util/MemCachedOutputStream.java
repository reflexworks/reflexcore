package jp.sourceforge.reflex.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>データをメモリ上に保持する出力ストリームです。
 */
public class MemCachedOutputStream extends OutputStream {

	private ByteArrayOutputStream memOutputStream;

	public MemCachedOutputStream() {
		memOutputStream = new ByteArrayOutputStream();
	}

	public MemCachedOutputStream(String key) {
		memOutputStream = new ByteArrayOutputStream();
	}

	public ByteArrayOutputStream getOutputStream() {
		return memOutputStream;
	}

	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(this.getData());
	}

	public byte[] getData() {
		if (memOutputStream != null) {
			return memOutputStream.toByteArray();
		}
		return null;
	}

	public void write(int b) throws IOException {
		memOutputStream.write(b);
	}

	public void write(byte b[]) throws IOException {
		memOutputStream.write(b);
	}

	public void write(byte b[], int off, int len) throws IOException {
		memOutputStream.write(b, off, len);
	}

	public void flush() throws IOException {
		memOutputStream.flush();
	}

	public void close() throws IOException {
		try {
			flush();
		} catch (IOException ignored) {
			// ignore
		}
		memOutputStream.close();
	}

}
