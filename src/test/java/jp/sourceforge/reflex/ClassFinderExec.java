package jp.sourceforge.reflex;

import java.io.File;
import java.net.URL;
import java.net.JarURLConnection;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;

public class ClassFinderExec {

	public static final String SUFFIX_CLASS = ".class";
	public static final int SUFFIX_CLASS_LEN = SUFFIX_CLASS.length();
	public static final String PROTOCOL_FILE = "file";
	public static final String PROTOCOL_JAR = "jar";
	
	
    private ClassLoader classLoader;

    public static void main(String[] args) throws Exception {
    	ClassFinderExec classFinder = new ClassFinderExec();
    	if (args.length > 0) {
    		classFinder.printClasses(args[0]);
    	} else {
    		System.out.println("引数にパッケージを指定してください。");
    	}
    }

    public ClassFinderExec() {
        classLoader = Thread.currentThread().getContextClassLoader();
    }

    public ClassFinderExec(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public void printClasses(String packageName) throws Exception {
        //String resourceName = packageName.replace('.', '/');
        String resourceName = packageNameToResourceName(packageName);
        Enumeration<URL> urls = classLoader.getResources(resourceName);
        if (urls != null) {
        	while (urls.hasMoreElements()) {
        		URL url = urls.nextElement();
                System.out.println("URL = " + url);
                System.out.println("URLConnection = " + url.openConnection());
                
                String protocol = url.getProtocol();
                if (PROTOCOL_FILE.equals(protocol)) {
                    // fileの場合
                	List<Class<?>> classes = findClassesWithFile(packageName, new File(url.getFile()));
                	for (Class<?> cls : classes) {
                		System.out.println("    " + cls.getName());
                	}
                } else if (PROTOCOL_JAR.equals(protocol)) {
                    // JARファイルからロードした場合
                	List<Class<?>> classes = findClassesWithJarFile(packageName, url);
                	for (Class<?> cls : classes) {
                		System.out.println("    " + cls.getName());
                	}
                }

        	}
        }
    }

    /*
     * fileプロトコルの場合
     */
    private List<Class<?>> findClassesWithFile(String packageName, File dir) throws Exception {
        List<Class<?>> classes = new ArrayList<Class<?>>();

        for (String path : dir.list()) {
            File entry = new File(dir, path);
            if (entry.isFile() && isClassFile(entry.getName())) {
                classes.add(classLoader.loadClass(packageName + "." + fileNameToClassName(entry.getName())));
            } else if (entry.isDirectory()) {
            	// 再起呼び出しはしない
                //classes.addAll(findClassesWithFile(packageName + "." + entry.getName(), entry));
            }
        }

        return classes;
    }

    /*
     * jarプロトコルの場合
     */
    private List<Class<?>> findClassesWithJarFile(String packageName, URL jarFileUrl) throws Exception {
        List<Class<?>> classes = new ArrayList<Class<?>>();

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
                    classes.add(classLoader.loadClass(resourceNameToClassName(name)));
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
