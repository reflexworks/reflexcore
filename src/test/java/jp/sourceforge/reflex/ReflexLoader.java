package jp.sourceforge.reflex;

import java.io.IOException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class ReflexLoader  extends ClassLoader{

	private ClassPool pool;

    public ReflexLoader(ClassLoader parent) throws NotFoundException {
    	super(parent);
        pool = ClassPool.getDefault();
    }
    
    @Override
	protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

		// "java"で始まるパッケージはシステム系なのでデフォルトのクラスローダーに任せる
		if (name.startsWith("java") || name.startsWith("sun.")) {
			return super.loadClass(name, resolve);
		}

		try {
			// ロードしたいクラスをJavassist内から取得
			ClassPool classPool = ClassPool.getDefault();
			CtClass cc = classPool.get(name);

			// 自分のクラスローダーを指定してJavaVMのクラスに変換
			ProtectionDomain pd = this.getClass().getProtectionDomain();
			Class c = cc.toClass(this, pd);

			// resolveClass()が何してるんだか知らないけど、ClassLoaderの真似して呼んでおく
			if (resolve) {
				resolveClass(c);
			}
			return c;

		} catch (Exception e) {
			// e.printStackTrace();
			return super.loadClass(name, resolve);
		}
	}
    /* Finds a specified class.
     * The bytecode for that class can be modified.
     */
    protected Class findClass(String name) throws ClassNotFoundException {
        try {
            CtClass cc = pool.get(name);
            // modify the CtClass object here
            byte[] b = cc.toBytecode();
            return defineClass(name, b, 0, b.length);
        } catch (NotFoundException e) {
            throw new ClassNotFoundException();
        } catch (IOException e) {
            throw new ClassNotFoundException();
        } catch (CannotCompileException e) {
            throw new ClassNotFoundException();
        }
    }
    
    public ClassPool getPool() {
    	return pool;
    }
    
}
