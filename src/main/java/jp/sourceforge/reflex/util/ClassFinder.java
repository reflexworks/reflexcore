package jp.sourceforge.reflex.util;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;
import java.util.HashSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassFinder {

	private static final String SUFFIX_CLASS = ".class";
	private static final int SUFFIX_CLASS_LEN = SUFFIX_CLASS.length();
	private static final String PROTOCOL_FILE = "file";
	private static final String PROTOCOL_JAR = "jar";

    private ClassLoader classLoader;
    
    public ClassFinder() {
        this.classLoader = Thread.currentThread().getContextClassLoader();
    }
    
    public ClassFinder(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
    
    /**
     * 指定されたクラスをロードします.
     * <p>
     * クラスが見つからなかった場合、nullを返却します.
     * </p>
     * @param clsName クラス名
     * @return クラス
     */
    public Class<?> loadClass(String clsName) {
    	try {
    		return classLoader.loadClass(clsName);
    	} catch (ClassNotFoundException e) {}	// Do nothing.
    	return null;
    }

    /**
     * パッケージ名からクラス名一覧を取得します.
     * <p>
     * 例えば、引数に"jp.sourceforge.reflex"と指定した場合は、
     * "jp.sourceforge.reflex.core"のクラスは取得しません。
     * "jp.sourceforge.reflex"のクラスのみ取得します。
     * </p>
     * @param packageName パッケージ
     * @return 指定されたパッケージ配下のClass名リスト
     */
    public Set<String> getClassNamesFromPackage(String packageName) 
    		throws IOException {
    	Set<String> retClasses = new HashSet<String>();

    	String resourceName = packageNameToResourceName(packageName);
        Enumeration<URL> urls = classLoader.getResources(resourceName);
        if (urls != null) {
        	while (urls.hasMoreElements()) {
        		URL url = urls.nextElement();
                String protocol = url.getProtocol();
                if (PROTOCOL_FILE.equals(protocol)) {
                    // fileの場合
                	Set<String> classes = findClassesWithFile(packageName, new File(url.getFile()));
                	retClasses.addAll(classes);
                } else if (PROTOCOL_JAR.equals(protocol)) {
                    // JARファイルからロードした場合
                	Set<String> classes = findClassesWithJarFile(packageName, url);
                	retClasses.addAll(classes);
                }
        	}
        }
        return retClasses;
    }

    /*
     * fileプロトコルの場合
     */
    private Set<String> findClassesWithFile(String packageName, File dir) {
    	Set<String> classes = new HashSet<String>();

        for (String path : dir.list()) {
            File entry = new File(dir, path);
            if (entry.isFile() && isClassFile(entry.getName())) {
            	String classname = packageName + "." + fileNameToClassName(entry.getName());
            	System.out.println("classname="+classname);
        		classes.add(classname);
            } else if (entry.isDirectory()) {
            	// 再起呼び出しはしない
            }
        }

        return classes;
    }

    /*
     * jarプロトコルの場合
     */
    private Set<String> findClassesWithJarFile(String packageName, URL jarFileUrl) 
    throws IOException {
    	Set<String> classes = new HashSet<String>();

        JarURLConnection jarUrlConnection = (JarURLConnection)jarFileUrl.openConnection();
        JarFile jarFile = null;

        try {
            jarFile = jarUrlConnection.getJarFile();
            Enumeration<JarEntry> jarEnum = jarFile.entries();

            String packageNameAsResourceName = packageNameToResourceName(packageName);
            int idx = packageNameAsResourceName.length() + 1;

            while (jarEnum.hasMoreElements()) {
                JarEntry jarEntry = jarEnum.nextElement();
                String name = jarEntry.getName();
                if (name.startsWith(packageNameAsResourceName) && 
                		isClassFile(name) &&
                		name.indexOf("/", idx) == -1) {
            		classes.add(resourceNameToClassName(name));
                }
            }
        } finally {
            if (jarFile != null) {
                jarFile.close();
            }
        }

        return classes;
    }

    private boolean isClassFile(String name) {
    	if (name != null && name.endsWith(SUFFIX_CLASS)) {
    		return true;
    	}
    	return false;
    }
    
    private String fileNameToClassName(String name) {
    	return name.substring(0, name.length() - SUFFIX_CLASS_LEN);
    }
    
    private String packageNameToResourceName(String packageName) {
    	return packageName.replace(".", "/");
    }
    
    private String resourceNameToClassName(String resourceName) {
    	String tmp = resourceName.replace("/", ".");
    	return fileNameToClassName(tmp);
    }

}
