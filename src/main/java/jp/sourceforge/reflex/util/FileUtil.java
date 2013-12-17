package jp.sourceforge.reflex.util;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

import com.sun.jmx.snmp.defaults.DefaultPaths;

//import org.apache.log4j.helpers.Loader;

public class FileUtil {
	
	public static final int BUFFER_SIZE = 8192;
	public static final String DEFAULT_ENCODING = "UTF-8";
	private static Logger logger = Logger.getLogger(FileUtil.class.getName());

	private static final ThreadLocal<String> defaultpath = new ThreadLocal<String>();
	private static final ThreadLocal<String> errorpath = new ThreadLocal<String>();

	/**
	 * クラスパス配下に存在する、指定ファイル名の絶対パスを返却します。
	 */
	public static String getResourceFilename(String resource) throws FileNotFoundException {
		
		if ((resource.length() > 5 && "http:".equals(resource.substring(0, 5))) ||
				(resource.length() > 6 && "https:".equals(resource.substring(0, 5)))) {
			return resource;
		}

		//URL url = Loader.getResource(resource);
		
		File file = new File(resource);
		if (file.exists()) {
			return file.getPath();
		}
		ClassLoader loader = FileUtil.class.getClassLoader();
		URL url = loader.getResource(resource);

		if (url != null) {
			String filename = url.getFile();
			if (filename != null && filename.indexOf("!") >= 0) {
				filename = "jar:" + filename;
    		}

			return changeBlankString(filename);

		} else {
			// デフォルトパスがセットされていたら相対パス扱いとする
			if (defaultpath.get()!=null) {
				errorpath.set(defaultpath.get()+resource);
				return defaultpath.get()+resource;
			}
			throw new FileNotFoundException();
		}

	}
	
	public static String getErrorpath() {
		return errorpath.get();
	}

	/**
	 * クラスパス配下に存在する、指定ファイル名のURLを返却します。
	 */
	public static String getResourceUrl(String resource) throws FileNotFoundException {

		if ((resource.length() > 5 && "http:".equals(resource.substring(0, 5))) ||
				(resource.length() > 6 && "https:".equals(resource.substring(0, 5)))) {
			return resource;
		}

		//URL url = Loader.getResource(resource);
		File file = new File(resource);
		if (file.exists()) {
			return "file:" + file.getPath();
		}
		ClassLoader loader = FileUtil.class.getClassLoader();
		URL url = loader.getResource(resource);

		if (url != null) {
			String filename = url.getFile();
			if (filename != null && filename.indexOf("!") >= 0) {
				filename = "jar:" + filename;
    		} else {
				filename = "file:" + filename;
    		}

			return changeBlankString(filename);

		} else {
			throw new FileNotFoundException();
		}

	}

	public static String changeBlankString(String str) {
		if (str == null) {
			return str;
		}
		String ret = str.replaceAll("%20", " ");

		return ret;
	}

	public InputStream getResourceStream(Class instance, String resource) 
	throws FileNotFoundException {
		InputStream inStream = instance.getResourceAsStream(resource);
		return inStream;
	}

	/**
	 * 指定されたパスまたはURLに存在する、ファイルのInputStreamを返却します。
	 * @param resource 指定されたパスまたはURL
	 */
	public InputStream getResourceStream(String resource)
	throws IOException {
		return getResourceStream(resource, false);
	}

	/**
	 * 指定されたパスまたはURLに存在する、ファイルのInputStreamを返却します。
	 * @param resource 指定されたパスまたはURL
	 * @param reqGZip gzip送受信する・しないを指定する（URLの場合有効）
	 */
	public InputStream getResourceStream(String resource, boolean reqGZip)
	throws IOException {
		return getResourceStream(resource, reqGZip, 0);
	}

	/**
	 * 指定されたパスまたはURLに存在する、ファイルのInputStreamを返却します。
	 * @param resource 指定されたパスまたはURL
	 * @param reqGZip gzip送受信する・しないを指定する（URLの場合有効）
	 * @param num_retries リトライ回数
	 */
	public InputStream getResourceStream(String resource, boolean reqGZip, int num_retries)
	throws IOException {
		InputStream inStream = null;
		if ((resource.length() > 5 && "http:".equals(resource.substring(0, 5))) ||
				(resource.length() > 6 && "https:".equals(resource.substring(0, 6)))) {
			URL url = new URL(resource);
			defaultpath.set(resource.substring(0,resource.lastIndexOf("/")+1));

			for (int r = 0; r <= num_retries; r++) {

				try {
					URLConnection conn = url.openConnection();
					if (reqGZip) {
						conn.setRequestProperty("accept-encoding", "gzip");
					}

					// gzipの場合
					boolean respGZip = false;
					String contentEncoding = conn.getHeaderField("Content-Encoding");
					if (contentEncoding != null && contentEncoding.indexOf("gzip") > -1) {
						respGZip = true;
					}
					String contentType = conn.getHeaderField("Content-Type");
					if (contentType != null && contentType.indexOf("gzip") > -1) {
						respGZip = true;
					}

					if (respGZip) {
						inStream = new GZIPInputStream(conn.getInputStream());
					} else {
						inStream = conn.getInputStream();
					}

					break;

				} catch (IOException e) {
					if (r == num_retries) {
						throw e;
					}
					sleep(200);

				} catch (RuntimeException e) {
					if (r == num_retries) {
						throw e;
					}
					sleep(200);
				}
			}

		} else {
			inStream = new FileInputStream(resource);
		}
		return inStream;
	}

	/**
	 * 指定されたパスまたはURLに存在する、ファイルのInputStreamを返却します。
	 * @param resource 指定されたパスまたはURL
	 */
	public InputStream getUrlStream(String resource)
	throws IOException {
		return getUrlStream(resource, 0);
	}

	/**
	 * 指定されたパスまたはURLに存在する、ファイルのInputStreamを返却します。
	 * @param resource 指定されたパスまたはURL
	 * @param num_retries リトライ回数
	 */
	public InputStream getUrlStream(String resource, int num_retries)
	throws IOException {
		InputStream inStream = null;
		if ((resource.length() > 5 && "http:".equals(resource.substring(0, 5))) ||
				(resource.length() > 6 && "https:".equals(resource.substring(0, 6)))) {
			URL url = new URL(resource);

			for (int r = 0; r <= num_retries; r++) {

				try {
					inStream = url.openStream();

					break;

				} catch (IOException e) {
					if (r == num_retries) {
						throw e;
					}
					sleep(200);

				} catch (RuntimeException e) {
					if (r == num_retries) {
						throw e;
					}
					sleep(200);
				}
			}

		} else {
			inStream = new FileInputStream(resource);
		}
		return inStream;
	}

	/**
	 * クラスパス一覧を返却します。
	 */
	public static String[] getClassPath() {
		String path = System.getProperty("java.class.path");
		return path.split(System.getProperty("path.separator"));
	}

	/**
	 * クラスパスから、指定されたディレクトリの絶対パスを返却します。
	 * @param relDir 最下層のディレクトリ名
	 */
	public static String getDirectory(String relDir) {
		String ret = null;
		String[] classpath = getClassPath();
		if (classpath != null && relDir != null) {
			String separator = System.getProperty("file.separator");
			String searchDir = separator + relDir;
			int slen = searchDir.length();
			for (int i = 0; i < classpath.length; i++) {
				int clen = classpath[i].length();
				if (clen >= slen) {
					if (searchDir.equals(classpath[i].substring(clen - slen))) {
						ret = classpath[i];
						break;
					}
				}
			}
		}
		return ret;
	}

	/**
	 * InputStreamを読み込み、byte配列に変換します。
	 * @param in
	 * @return byte array
	 * @throws IOException
	 */
	public byte[] readInputStream(InputStream in) throws IOException {
		return readInputStream(in, BUFFER_SIZE);
	}

	/**
	 * InputStreamを読み込み、byte配列に変換します。
	 * @param in InputStream
	 * @param bufferSize buffer size
	 * @return byte array
	 * @throws IOException
	 */
	public byte[] readInputStream(InputStream in, int bufferSize)
	throws IOException {
		byte[] data = null;
		int dataPos = 0;
		byte[] temp = null;
		byte[] buffer = new byte[bufferSize];
		int size;
		while ((size = in.read(buffer)) != -1) {
			if (dataPos > 0) {
				temp = new byte[dataPos];
				System.arraycopy(data, 0, temp, 0, dataPos);
				data = new byte[dataPos + size];
				System.arraycopy(temp, 0, data, 0, dataPos);
			} else {
				data = new byte[size];
			}
			System.arraycopy(buffer, 0, data, dataPos, size);
			dataPos = dataPos + size;
		}

		return data;
	}

	/**
	 * InputStreamから文字列を読み、Stringにして返却します
	 * @param in InputStream
	 * @return InputStreamから読み込んだ文字列
	 * @throws IOException
	 */
	public String readString(InputStream in) throws IOException {
		return readString(new InputStreamReader(in));
	}

	/**
	 * Readerから文字列を読み、Stringにして返却します
	 * @param reader Reader
	 * @return Readerから読み込んだ文字列
	 * @throws IOException
	 */
	public String readString(Reader reader) throws IOException {

		BufferedReader breader = null;
		PrintWriter pwriter = null;
		StringWriter swriter = null;
		String result = null;
		if (reader instanceof BufferedReader) {
			breader = (BufferedReader)reader;
		} else {
			breader = new BufferedReader(reader);
		}
		try {
			swriter = new StringWriter();
			pwriter = new PrintWriter(swriter);
			String line;
			while ((line = breader.readLine()) != null) {
				pwriter.println(line);
			}
			pwriter.flush();
			result = swriter.toString();
		} finally {
			if (pwriter != null) {
				pwriter.close();
			} else if (swriter != null) {
				swriter.close();
			}
		}
		return (result);
	}

	/**
	 * InputStreamから値を読み込み、OutputStreamに書き込みます
	 * @param in InputStream
	 * @param out OutputStream
	 */
	public void fromInputToOutput(InputStream in, OutputStream out) 
	throws IOException {
		fromInputToOutput(in, out, 4096);
	}

	/**
	 * InputStreamから値を読み込み、OutputStreamに書き込みます
	 * @param in InputStream
	 * @param out OutputStream
	 * @param bufferSize バッファサイズ
	 */
	public void fromInputToOutput(InputStream in, OutputStream out, int bufferSize) 
	throws IOException {
		byte buffer[] = new byte[bufferSize];

		// InputStreamから読み込んでOutputStreamへ書き込む繰り返し
		int size;
		while ((size = in.read(buffer)) != -1) {
			out.write(buffer, 0, size);
		}
	}
	
	/**
	 * ファイル名から入力ストリームを取得します.
	 * @param filename ファイル名
	 * @return InputStream
	 */
	public InputStream getInputStreamFromFile(String filename) {
		InputStream in = null;
		File propertyFile = new File(filename);
		if (propertyFile.exists()) {
			try {
				in = new FileInputStream(propertyFile);
			} catch (IOException e) {
				logger.warning(e.getMessage());
			}
		}
		
		if (in == null) {
			ClassLoader loader = this.getClass().getClassLoader();
			URL propertyURL = loader.getResource(filename);
			if (propertyURL != null) {
				try {
					in = propertyURL.openStream();
				} catch (IOException e) {
					logger.warning(e.getMessage());
				}
			}
		}
		return in;
	}
	
	/**
	 * ファイル名からReaderを取得します.
	 * @param filename ファイル名
	 * @return Reader
	 */
	public BufferedReader getReaderFromFile(String filename) {
		return getReaderFromFile(filename, null);
	}

	/**
	 * ファイル名からReaderを取得します.
	 * @param filename ファイル名
	 * @return Reader
	 */
	public BufferedReader getReaderFromFile(String filename, String enctype) {
		String encoding = enctype;
		if (encoding == null || encoding.length() == 0) {
			encoding = DEFAULT_ENCODING;
		}

		InputStream in = getInputStreamFromFile(filename);
		
		BufferedReader reader = null;
		if (in != null) {
			try {
				reader = new BufferedReader(new InputStreamReader(in, encoding));
			} catch (UnsupportedEncodingException e) {
				logger.warning("UnsupportedEncodingException: " + e.getMessage());
			}
		}
		return reader;
	}

	/**
	 * 指定ミリ秒実行を止めます
	 * @param msec 停止ミリ秒
	 */
	public synchronized void sleep(long msec) {
		try {
			wait(msec);
		} catch (InterruptedException e) {}
	}

	
}
