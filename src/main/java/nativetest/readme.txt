https://blog.csdn.net/u010853261/article/details/53470514
https://www.chilkatsoft.com/java-loadLibrary-MacOSX.asp

java -Djava.library.path=".:/Users/chenyun/workspace/javademo/src/main/java/nativetest" nativetest.HelloWorld

gcc -dynamiclib -I /Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home/include/ -I /Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contes/Home/include/darwin/ HelloWorldImpl.c -o libhello.jnilib