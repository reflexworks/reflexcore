package jp.sourceforge.reflex.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * <p>データをメモリ上に保持する出力ストリームです。
 */
public class MemCachedMap {
	
	private static final ThreadLocal<HashMap<String,MemCachedOutputStream>> cachedmap = new ThreadLocal<HashMap<String,MemCachedOutputStream>>();
	
	public static MemCachedOutputStream getOutputStream(String key) {
		
		MemCachedOutputStream memCachedOutputStream = new MemCachedOutputStream(key);
		HashMap<String,MemCachedOutputStream> hashmap = cachedmap.get();
		if (hashmap==null) {
			hashmap = new HashMap<String,MemCachedOutputStream>();
		}
		hashmap.put(key, memCachedOutputStream);
		cachedmap.set(hashmap);
		
		return memCachedOutputStream;
	}
	
	public static InputStream getInputStream(String key) throws IOException {
		return new ByteArrayInputStream(((MemCachedOutputStream)cachedmap.get().get(key)).getData());
	}

	public static byte[] getData(String key) throws IOException {
		return ((MemCachedOutputStream)cachedmap.get().get(key)).getData();
	}

	public static void remove(String key) throws IOException {
		((MemCachedOutputStream)cachedmap.get().get(key)).getOutputStream().close();
		if (cachedmap.get()!=null) {
			cachedmap.get().remove(key);
		}
	}

}
