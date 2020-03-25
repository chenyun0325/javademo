package javaassist;

import javassist.*;
import org.junit.Test;

import java.io.IOException;

public class JavaAssistUtil {

    public static void main(String[] args) throws Exception {

        ClassPool pool = new ClassPool(true);
//        pool.appendClassPath(new LoaderClassPath(classLoader));
//        pool.appendClassPath(new LoaderClassPath(ClassLoader.getSystemClassLoader()));
        pool.importPackage("javaassist");
        pool.importPackage("jvm");
        CtClass ctClass = pool.get("javaassist.Item");
        CtField field1 = CtField.make("private final ItemField field1 ;", ctClass);
        ctClass.addField(field1);
        CtField field2 = CtField.make("private Address address;", ctClass);
        ctClass.addField(field2);
        ctClass.writeFile();

    }


    @Test
    public void test() throws NotFoundException, CannotCompileException, IOException {
        CtClass ctClass = ClassPool.getDefault().get("javaassist.Item");
        CtMethod ctMethod = ctClass.getDeclaredMethod("setName");
        ctMethod.insertAt(22,"System.out.println(\"hello world\");");
        ctClass.writeFile();
    }

    @Test
    public void test1() throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        pool.importPackage("javaassist");
        CtClass ctClass = pool.get("javaassist.Item");
        CtMethod ctMethod = ctClass.getDeclaredMethod("setName");
        ctMethod.insertAt(22,"ItemField.test(\"xyz\");");
        ctClass.writeFile();
    }
}
