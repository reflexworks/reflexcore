package jp.sourceforge.reflex.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipException;

public class ZipUtil {
	
	public static final int BUFFER_SIZE = 8192;

	/**
	 * zip形式に圧縮する
	 * @param data 圧縮元バイトデータ
	 * @param out 圧縮データ(OutputStream)
	 */
	public void zip(ByteArrayOutputStream data, OutputStream out) throws IOException {
		zip(data, out, "default");
	}

	/**
	 * zip形式に圧縮する
	 * @param data 圧縮元バイトデータ
	 * @param out 圧縮データ(OutputStream)
	 * @param name 圧縮元データ名
	 */
	public void zip(ByteArrayOutputStream data, OutputStream out, String name) throws IOException {
		zip(data.toByteArray(), out, name);
	}

	/**
	 * zip形式に圧縮する
	 * @param data 圧縮元バイトデータ
	 * @param out 圧縮データ(OutputStream)
	 */
	public void zip(byte[] data, OutputStream out) throws IOException {
		zip(data, out, "default");
	}

	/**
	 * zip形式に圧縮する
	 * @param data 圧縮元バイトデータ
	 * @param out 圧縮データ(OutputStream)
	 * @param name 圧縮元データ名
	 */
	public void zip(byte[] data, OutputStream out, String name) throws IOException {
		ZipOutputStream zos = null;
		ZipEntry zent = null;
		if (data != null) {
			try {
				zent = new ZipEntry(name);
				zos = new ZipOutputStream(out);
				zos.putNextEntry(zent);
				zos.write(data);

			} finally {
				try {
					zos.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * zip形式に圧縮し、バイト配列で受け取る
	 * @param data 圧縮元バイトデータ
	 * @return zip圧縮されたバイトデータ
	 */
	public byte[] getZip(ByteArrayOutputStream data) throws IOException {
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		zip(data, bOut, "default");
		return bOut.toByteArray();
	}

	/**
	 * zip形式に圧縮し、バイト配列で受け取る
	 * @param data 圧縮元バイトデータ
	 * @return zip圧縮されたバイトデータ
	 */
	public byte[] getZip(byte[] data) throws IOException {
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		zip(data, bOut, "default");
		return bOut.toByteArray();
	}
	
	/**
	 * 複数ファイルを１個のzipファイルに圧縮する
	 * @param zipFile zipファイル出力先
	 * @param dataMap key : zipファイル名、value : 圧縮したいデータ（形式はbyte配列、File、InputStreamのいずれか）
	 */
	public void bringZip(File zipFile, Map dataMap) throws IOException {
		BufferedOutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(zipFile));
			bringZip(out, dataMap);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * 複数ファイルを１個のzipファイルに圧縮する
	 * @param out zipファイル出力先
	 * @param dataMap key : zipファイル名、value : 圧縮したいデータ（形式はbyte配列、File、InputStreamのいずれか）
	 */
	public void bringZip(OutputStream out, Map dataMap) throws IOException {
		ZipOutputStream zos = null;
		if (dataMap != null && dataMap.size() > 0) {
			zos = new ZipOutputStream(out);
			try {
				Set entrySet = dataMap.entrySet();
				Iterator it = entrySet.iterator();
				
				while (it.hasNext()) {
					Map.Entry entry = (Map.Entry)it.next();
					ZipEntry zent = new ZipEntry((String)entry.getKey());
					zos.putNextEntry(zent);
					
					Object obj = entry.getValue();
					if (obj instanceof byte[]) {
						zos.write((byte[])obj);

					} else if (obj instanceof File) {
						BufferedInputStream is = new BufferedInputStream(new FileInputStream((File)obj));
						
						try {
							byte[] buf = new byte[BUFFER_SIZE];
							int size = 0;
							while ((size = is.read(buf)) != -1) {
								zos.write(buf, 0, size);
							}

						} finally {
							if (is != null) {
								is.close();
							}
						}

					} else if (obj instanceof InputStream) {
						BufferedInputStream is = null;
						if (obj instanceof BufferedInputStream) {
							is = (BufferedInputStream)obj;
						} else {
							is = new BufferedInputStream((InputStream)obj);
						}
						
						if (is != null) {
							try {
								byte[] buf = new byte[BUFFER_SIZE];
								int size = 0;
								while ((size = is.read(buf)) != -1) {
									zos.write(buf, 0, size);
								}
	
							} finally {
								is.close();
							}
						}
					}
					
					zos.closeEntry();
				}

			} finally {
				if (zos != null) {
					zos.close();
				}
			}
		}
	}

	/**
	 * zip形式から解凍する
	 * @param data 圧縮データ
	 * @param out 解凍データ出力先
	 */
	public void unzip(byte[] data, OutputStream out) throws IOException {
		unzip(new ByteArrayInputStream(data), out);
	}


	/**
	 * zip形式から解凍する
	 * @param in 圧縮データ
	 * @param out 解凍データ出力先
	 */
	public void unzip(InputStream in, OutputStream out) throws IOException {
		ZipInputStream zis = new ZipInputStream(in);
		ZipEntry entry = null;
		while ((entry = zis.getNextEntry()) != null) {
			byte[] buf = new byte[BUFFER_SIZE];
			int size = 0;
			while ((size = zis.read(buf)) != -1) {
				out.write(buf, 0, size);
			}
			zis.closeEntry();
		}
	}

	/**
	 * zip形式から解凍する
	 * @param data 圧縮データ
	 * @return 解凍データ
	 */
	public byte[] getUnzip(byte[] data) throws IOException {
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		unzip(new ByteArrayInputStream(data), bOut);
		return bOut.toByteArray();
	}
	
	/**
	 * zipファイルからzipエントリのリストを取得する
	 * @param zipFile
	 * @return zipエントリのリスト
	 */
	public List getZipEntry(File zipFile) throws ZipException, IOException {
		ZipFile zipFileObj = new ZipFile(zipFile);
		Enumeration zipEntries = zipFileObj.entries();
		List ret = new ArrayList();
		while (zipEntries.hasMoreElements()) {
			ret.add(zipEntries.nextElement());
		}
		zipFileObj.close();
		return ret;
	}
	
	/**
	 * 複数のファイルが格納されているzipファイルを解凍する
	 * @param zipFile 圧縮ファイル
	 * @param unzipMap key : zipエントリ名、value : 解凍ファイルの出力先(OutputStreamまたはFile)
	 */
	public void unzip(File zipFile, Map unzipMap) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(zipFile));
		unzip(in, unzipMap);
	}
	
	/**
	 * 複数のファイルが格納されているzipファイルを解凍する
	 * @param is 圧縮ファイルのストリーム
	 * @param unzipMap key : zipエントリ名、value : 解凍ファイルの出力先(OutputStreamまたはFile)
	 */
	public void unzip(InputStream is, Map unzipMap) throws IOException {
		ZipInputStream zipis = null;
		if (is instanceof ZipInputStream) {
			zipis = (ZipInputStream)is;
		} else {
			zipis = new ZipInputStream(is);
		}

		try {
			ZipEntry entry = null;
            while ((entry = zipis.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                	Object obj = unzipMap.get(entry.getName());
                	OutputStream out = null;
                	if (obj instanceof OutputStream) {
                		out = (OutputStream)obj;
                	} else if (obj instanceof File) {
                		out = new BufferedOutputStream(new FileOutputStream((File)obj));
                	}
					
					if (out != null) {
						try {
							byte[] buf = new byte[BUFFER_SIZE];
							int size = 0;
							while ((size = zipis.read(buf)) != -1) {
								out.write(buf, 0, size);
							}

						} finally {
							out.flush();
							out.close();
						}
					}
                }
                zipis.closeEntry();
            }

		} finally {
        	if (zipis != null) {
        		zipis.close();
        	}
        }
	}

}
