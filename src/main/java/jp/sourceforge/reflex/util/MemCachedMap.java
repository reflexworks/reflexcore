package jp.sourceforge.reflex.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * <p>データをメモリ上に保持する出力ストリームです。
 */
public class MemCachedMap
{
	
	private static MemCachedMap memCachedMap = new MemCachedMap();
	
    private HashMap cachedmap;
    
    public MemCachedMap() {
        cachedmap = new HashMap();
    }

    public static MemCachedMap getInstance() {
    	return memCachedMap;
    }
    
    public MemCachedOutputStream getOutputStream(String key) {
    	
    	MemCachedOutputStream memCachedOutputStream = new MemCachedOutputStream(key);
    	cachedmap.put(key, memCachedOutputStream);

    	return memCachedOutputStream;
    }
    
	public InputStream getInputStream(String key) throws IOException {
		return new ByteArrayInputStream(((MemCachedOutputStream)cachedmap.get(key)).getData());
	}

	public byte[] getData(String key) throws IOException {
		try {
			return ((MemCachedOutputStream)cachedmap.get(key)).getData();
		} catch (Exception e) {
			return null;
		}
	}

	public void remove(String key) {
		try {
			((MemCachedOutputStream)cachedmap.get(key)).getOutputStream().close();
		} catch (Exception e) {
		}
		cachedmap.remove(key);
	}


}
