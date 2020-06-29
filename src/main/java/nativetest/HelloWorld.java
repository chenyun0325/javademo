package nativetest;

public class HelloWorld {

    public native void hello(String name);

    static{
        System.out.println(System.getProperty("java.library.path"));
        System.setProperty("java.library.path","/Users/chenyun/workspace/javademo/src/main/java/nativetest/");
        System.out.println(System.getProperty("java.library.path"));
        System.loadLibrary("hello");
//        System.load("/Users/chenyun/workspace/javademo/src/main/java/nativetest/libhello.jnilib");
    }

    public static void main(String[] args){
        new HelloWorld().hello("jni");
    }
}
