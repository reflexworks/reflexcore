package jp.sourceforge.reflex.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

public class FileUtil {

	public static final int BUFFER_SIZE = 8192;
	public static final String DEFAULT_ENCODING = "UTF-8";
	public static final String USER_DIR = "user.dir";
	public static final String LINE_SEPARATOR = "line.separator";

	private static Logger logger = Logger.getLogger(FileUtil.class.getName());

	private static final ThreadLocal<String> defaultpath = new ThreadLocal<String>();
	private static final ThreadLocal<String> errorpath = new ThreadLocal<String>();

	/**
	 * クラスパス配下に存在する、指定ファイル名の絶対パスを返却します.
	 * <p>
	 * URLエンコードされたパスはデコードして返却します.
	 * </p>
	 */
	public static String getResourceFilename(String resource)
	throws FileNotFoundException {
		if (StringUtils.isBlank(resource)) {
			return null;
		}
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

			return URLDecoderPlus.urlDecode(filename);

		} else {
			// デフォルトパスがセットされていたら相対パス扱いとする
			if (defaultpath.get()!=null) {
				errorpath.set(defaultpath.get()+resource);
				return defaultpath.get()+resource;
			}
			throw new FileNotFoundException(resource);
		}

	}

	public static String getErrorpath() {
		return errorpath.get();
	}

	/**
	 * クラスパス配下に存在する、指定ファイル名のURLを返却します。
	 */
	public static String getResourceUrl(String resource) throws FileNotFoundException {
		if (StringUtils.isBlank(resource)) {
			return null;
		}
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
			throw new FileNotFoundException(resource);
		}

	}

	public static String changeBlankString(String str) {
		if (str == null) {
			return str;
		}
		String ret = str.replaceAll("%20", " ");

		return ret;
	}

	public static InputStream getResourceStream(Class instance, String resource)
	throws FileNotFoundException {
		InputStream inStream = instance.getResourceAsStream(resource);
		return inStream;
	}

	/**
	 * 指定されたパスまたはURLに存在する、ファイルのInputStreamを返却します。
	 * @param resource 指定されたパスまたはURL
	 */
	public static InputStream getResourceStream(String resource)
	throws IOException {
		return getResourceStream(resource, false);
	}

	/**
	 * 指定されたパスまたはURLに存在する、ファイルのInputStreamを返却します。
	 * @param resource 指定されたパスまたはURL
	 * @param reqGZip gzip送受信する・しないを指定する（URLの場合有効）
	 */
	public static InputStream getResourceStream(String resource, boolean reqGZip)
	throws IOException {
		return getResourceStream(resource, reqGZip, 0);
	}

	/**
	 * 指定されたパスまたはURLに存在する、ファイルのInputStreamを返却します。
	 * @param resource 指定されたパスまたはURL
	 * @param reqGZip gzip送受信する・しないを指定する（URLの場合有効）
	 * @param num_retries リトライ回数
	 */
	public static InputStream getResourceStream(String resource,
			boolean reqGZip, int num_retries)
	throws IOException {
		if (StringUtils.isBlank(resource)) {
			return null;
		}
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
	public static InputStream getUrlStream(String resource)
	throws IOException {
		return getUrlStream(resource, 0);
	}

	/**
	 * 指定されたパスまたはURLに存在する、ファイルのInputStreamを返却します。
	 * @param resource 指定されたパスまたはURL
	 * @param num_retries リトライ回数
	 */
	public static InputStream getUrlStream(String resource, int num_retries)
	throws IOException {
		if (StringUtils.isBlank(resource)) {
			return null;
		}
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
	public static byte[] readInputStream(InputStream in) throws IOException {
		return readInputStream(in, BUFFER_SIZE);
	}

	/**
	 * InputStreamを読み込み、byte配列に変換します。
	 * @param in InputStream
	 * @param bufferSize buffer size
	 * @return byte array
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream in, int bufferSize)
	throws IOException {
		if (in == null) {
			return null;
		}
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
	 * ファイルを読み込み、byte配列に変換します。
	 * ファイルは絶対パスを指定してください。
	 * @param filepath ファイルの絶対パス
	 * @return byte array
	 * @throws IOException
	 */
	public static byte[] readFile(String filepath)
	throws IOException {
		return readFile(filepath, BUFFER_SIZE);
	}

	/**
	 * ファイルを読み込み、byte配列に変換します。
	 * ファイルは絶対パスを指定してください。
	 * @param filepath ファイルの絶対パス
	 * @return byte array
	 * @throws IOException
	 */
	public static byte[] readFile(File file)
	throws IOException {
		if (file == null || !file.exists() || !file.isFile()) {
			return null;
		}
		return readInputStream(new FileInputStream(file), BUFFER_SIZE);
	}

	/**
	 * ファイルを読み込み、byte配列に変換します。
	 * ファイルは絶対パスを指定してください。
	 * @param filepath ファイルの絶対パス
	 * @param bufferSize buffer size
	 * @return byte array
	 * @throws IOException
	 */
	public static byte[] readFile(String filepath, int bufferSize)
	throws IOException {
		if (StringUtils.isBlank(filepath)) {
			return null;
		}
		if (bufferSize <= 0) {
			bufferSize = BUFFER_SIZE;
		}
		InputStream in = null;
		try {
			in = getInputStreamFromFile(filepath);
			return readInputStream(in, bufferSize);

		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	/**
	 * InputStreamから文字列を読み、Stringにして返却します
	 * <p>
	 * ストリームは本メソッド内でクローズします。
	 * </p>
	 * @param in InputStream
	 * @return InputStreamから読み込んだ文字列
	 */
	public static String readString(InputStream in) throws IOException {
		return readString(new InputStreamReader(in));
	}

	/**
	 * InputStreamから文字列を読み、Stringにして返却します
	 * <p>
	 * ストリームは本メソッド内でクローズします。
	 * </p>
	 * @param in InputStream
	 * @param encoding エンコード
	 * @return InputStreamから読み込んだ文字列
	 */
	public static String readString(InputStream in, String encoding) throws IOException {
		return readString(new InputStreamReader(in, encoding));
	}

	/**
	 * Readerから文字列を読み、Stringにして返却します.
	 * <p>
	 * Readerは本メソッド内でクローズします。
	 * </p>
	 * @param reader Reader
	 * @return Readerから読み込んだ文字列
	 */
	public static String readString(Reader reader) throws IOException {
		if (reader == null) {
			return null;
		}
		BufferedReader breader = null;
		StringWriter swriter = null;
		String result = null;
		String newline = getLineSeparator();
		if (reader instanceof BufferedReader) {
			breader = (BufferedReader)reader;
		} else {
			breader = new BufferedReader(reader);
		}
		try {
			swriter = new StringWriter();
			boolean isFirst = true;
			String line;
			while ((line = breader.readLine()) != null) {
				if (isFirst) {
					isFirst = false;
				} else {
					swriter.write(newline);
				}
				swriter.write(line);
			}
			swriter.flush();
			result = swriter.toString();
		} finally {
			if (swriter != null) {
				swriter.close();
			}
			if (breader != null) {
				breader.close();
			}
		}
		return result;
	}

	/**
	 * InputStreamから値を読み込み、OutputStreamに書き込みます
	 * @param in InputStream
	 * @param out OutputStream
	 */
	public static void fromInputToOutput(InputStream in, OutputStream out)
	throws IOException {
		fromInputToOutput(in, out, 4096);
	}

	/**
	 * InputStreamから値を読み込み、OutputStreamに書き込みます
	 * @param in InputStream
	 * @param out OutputStream
	 * @param bufferSize バッファサイズ
	 */
	public static void fromInputToOutput(InputStream in, OutputStream out,
			int bufferSize)
	throws IOException {
		if (in == null || out == null) {
			return;
		}
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
	public static InputStream getInputStreamFromFile(String filename) {
		if (StringUtils.isBlank(filename)) {
			return null;
		}
		InputStream in = null;
		File file = new File(filename);
		if (file.exists()) {
			try {
				in = new FileInputStream(file);
			} catch (IOException e) {
				logger.log(Level.WARNING, e.getClass().getName(), e);
			}
		}

		if (in == null) {
			ClassLoader loader = FileUtil.class.getClassLoader();
			URL url = loader.getResource(filename);
			if (url != null) {
				try {
					in = url.openStream();
				} catch (IOException e) {
					logger.log(Level.WARNING, e.getClass().getName(), e);
				}
			}
		}
		return in;
	}

	/**
	 * ファイル名から出力ストリームを取得します.
	 * @param filename ファイル名
	 * @return OutputStream
	 */
	public static OutputStream getOutputStreamFromFile(String filename) {
		if (StringUtils.isBlank(filename)) {
			return null;
		}
		OutputStream out = null;
		File propertyFile = new File(filename);
		try {
			out = new FileOutputStream(propertyFile);
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getClass().getName(), e);
		}
		return out;
	}

	/**
	 * ファイル名からReaderを取得します.
	 * <p>
	 * UTF-8でエンコーディングしたReaderを返却します。
	 * </p>
	 * @param filename ファイル名
	 * @return Reader
	 */
	public static BufferedReader getReaderFromFile(String filename) {
		return getReaderFromFile(filename, null);
	}

	/**
	 * ファイル名からReaderを取得します.
	 * @param filename ファイル名
	 * @param enctype エンコードタイプ。デフォルト値はUTF-8。
	 * @return Reader
	 */
	public static BufferedReader getReaderFromFile(String filename,
			String enctype) {
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
				logger.log(Level.WARNING, e.getClass().getName(), e);
			}
		}
		return reader;
	}

	/**
	 * ファイル名からWriterを取得します.
	 * <p>
	 * UTF-8エンコーディングするWriterを返却します。
	 * </p>
	 * @param filename ファイル名
	 * @return Writer
	 */
	public static BufferedWriter getWriterFromFile(String filename) {
		return getWriterFromFile(filename, DEFAULT_ENCODING);
	}

	/**
	 * ファイル名からWriterを取得します.
	 * @param filename ファイル名
	 * @param enctype エンコードタイプ。デフォルト値はUTF-8。
	 * @return Writer
	 */
	public static BufferedWriter getWriterFromFile(String filename,
			String enctype) {
		String encoding = enctype;
		if (encoding == null || encoding.length() == 0) {
			encoding = DEFAULT_ENCODING;
		}

		OutputStream out = getOutputStreamFromFile(filename);

		BufferedWriter writer = null;
		if (out != null) {
			try {
				writer = new BufferedWriter(new OutputStreamWriter(out, encoding));
			} catch (UnsupportedEncodingException e) {
				logger.log(Level.WARNING, e.getClass().getName(), e);
			}
		}
		return writer;
	}

	/**
	 * カレントディレクトリを取得します.
	 * <p>
	 * System.getProperty("user.dir") の結果を返却します。
	 * </p>
	 * @return カレントディレクトリ
	 */
	public static String getUserDir() {
		return System.getProperty(USER_DIR);
	}

	/**
	 * ディレクトリの区切り文字を取得します.
	 * <p>
	 * File.separator を返却します。
	 * </p>
	 * @return ディレクトリの区切り文字
	 */
	public static String getFileSeparator() {
		return File.separator;
	}

	/**
	 * 改行コードを取得します.
	 * <p>
	 * System.getProperty("line.separator") の結果を返却します。
	 * </p>
	 * @return 改行コード
	 */
	public static String getLineSeparator() {
		String separator = "\n";
		try {
			separator = System.getProperty(LINE_SEPARATOR);
		} catch (SecurityException e) {
			logger.log(Level.WARNING, e.getClass().getName(), e);
		}
		return separator;
	}

	/**
	 * ファイルやディレクトリを削除します.
	 * <p>
	 * ディレクトリ指定された場合、再帰的にファイルを削除します。
	 * </p>
	 * @param dir ファイルまたはディレクトリ
	 */
	public static void delete(File dir) {
		if (dir != null && dir.exists()) {
			if (dir.isDirectory()) {
				File[] files = dir.listFiles();
				if (files != null) {
					for (File file : files) {
						delete(file);
					}
				}
			}
			dir.delete();
		}
	}

	/**
	 * ディレクトリ配下のファイルを削除します.
	 * <p>
	 * 指定されたディレクトリは残します。
	 * </p>
	 * @param dir ディレクトリ
	 */
	public static void deleteFiles(File dir) {
		if (dir != null && dir.exists() && dir.isDirectory()) {
			File[] files = dir.listFiles();
			if (files != null) {
				for (File file : files) {
					delete(file);
				}
			}
		}
	}

	/**
	 * バイト配列データを指定されたファイルに出力します.
	 * @param data データ
	 * @param outFile 出力ファイル
	 * @throws IOException IOエラー
	 */
	public static void writeToFile(byte[] data, File outFile)
	throws IOException {
		if (data != null && outFile != null) {
			OutputStream out = null;
			try {
				out = new BufferedOutputStream(new FileOutputStream(outFile));
				out.write(data);

			} finally {
				if (out != null) {
					out.close();
				}
			}
		}
	}

	/**
	 * ストリームのデータを指定されたファイルに出力します.
	 * @param in データ
	 * @param outFile 出力ファイル
	 * @throws IOException IOエラー
	 */
	public static void writeToFile(InputStream in, File outFile)
	throws IOException {
		if (in != null && outFile != null) {
			OutputStream out = new FileOutputStream(outFile);
			writeToOutputStream(in, out);

			/*
			OutputStream out = null;
			try {
				out = new BufferedOutputStream(new FileOutputStream(outFile));

				// default buffer size = 8192
				BufferedInputStream bis = new BufferedInputStream(in);
				int size;
				while ((size = bis.read()) != -1) {
					out.write(size);
				}

			} finally {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			}
			*/
		}
	}

	/**
	 * 入力ストリームのデータを出力ストリームに出力します.
	 * @param in データ
	 * @param outFile 出力ファイル
	 * @throws IOException IOエラー
	 */
	public static void writeToOutputStream(InputStream in, OutputStream out)
	throws IOException {
		try {
			fromInputToOutput(in, out);
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 指定ミリ秒実行を止めます
	 * @param msec 停止ミリ秒
	 */
	public static synchronized void sleep(long msec) {
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			logger.log(Level.WARNING, e.getClass().getName(), e);
		}
	}

}
