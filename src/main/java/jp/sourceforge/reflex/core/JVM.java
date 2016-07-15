package jp.sourceforge.reflex.core;

import java.security.AccessControlException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;

public class JVM {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private ReflectionProvider reflectionProvider;

	private static final float majorJavaVersion;

	static {
		String javaVersionStr = System.getProperty("java.version");
		float javaVersion = 0.0f;
		if (javaVersionStr != null && javaVersionStr.length() >= 3) {
			try {
				javaVersion = Float.parseFloat(javaVersionStr.substring(0, 3));
			} catch (Exception e) {}	// Do nothing.
		}
		
		if (javaVersion < 0.1f) {
			javaVersion = 1.6f;	// 設定がなければ1.6以上とみなす。
		}
		majorJavaVersion = javaVersion;
	}

	public static boolean is14() {
		return majorJavaVersion >= 1.4f;
	}

	public static boolean is15() {
		return majorJavaVersion >= 1.5f;
	}

	private static boolean isSun() {
		return System.getProperty("java.vm.vendor").indexOf("Sun") != -1;
	}

	private static boolean isApple() {
		return System.getProperty("java.vm.vendor").indexOf("Apple") != -1;
	}

	private static boolean isHPUX() {
		return System.getProperty("java.vm.vendor").indexOf(
				"Hewlett-Packard Company") != -1;
	}

	private static boolean isIBM() {
		return System.getProperty("java.vm.vendor").indexOf("IBM") != -1;
	}

	public Class loadClass(String name) {
		try {
			return Class.forName(name, false, getClass().getClassLoader());
		} catch (ClassNotFoundException e) {
			logger.log(Level.WARNING, e.getClass().getName(), e);			
			return null;
		}
	}

	public synchronized ReflectionProvider bestReflectionProvider() {
		if (reflectionProvider == null) {
			try {
				if (canUseSun14ReflectionProvider()) {
					String cls = "com.thoughtworks.xstream.converters.reflection.Sun14ReflectionProvider";
					reflectionProvider = (ReflectionProvider) loadClass(cls)
							.newInstance();
				} else {
					reflectionProvider = new PureJavaReflectionProvider();
				}
			} catch (InstantiationException e) {
				reflectionProvider = new PureJavaReflectionProvider();
			} catch (IllegalAccessException e) {
				reflectionProvider = new PureJavaReflectionProvider();
			} catch (NoClassDefFoundError e) {
				reflectionProvider = new PureJavaReflectionProvider();
			} catch (AccessControlException e) {
				// thrown when trying to access sun.misc package in Applet
				// context.
				reflectionProvider = new PureJavaReflectionProvider();
			}
		}
		return reflectionProvider;
	}

	private boolean canUseSun14ReflectionProvider() {
		return (isSun() || isApple() || isHPUX() || isIBM()) && is14()
				&& loadClass("sun.misc.Unsafe") != null;
	}

}
