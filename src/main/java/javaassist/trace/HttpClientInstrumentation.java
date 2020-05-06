package javaassist.trace;

import javassist.*;

public class HttpClientInstrumentation {

    private static final String ENHANCE_CLASS = "org.apache.http.impl.client.InternalHttpClient"; // 增强的 client
    private static final String ENHANCE_METHOD = "doExecute"; // 增强的方法

    public static void enhance() throws NotFoundException, CannotCompileException {
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.getOrNull(ENHANCE_CLASS);
        if (ctClass == null) {
            System.out.println("http client not found");
            return;
        }
        CtMethod doExecuteMethod = ctClass.getDeclaredMethod(ENHANCE_METHOD);
        String sb = "{" +
                "javaassist.trace.HttpClientInterceptor.intercept" + "($2);" + // 获取入参 HttpRequest
                "}";
        doExecuteMethod.insertBefore(sb); // 植入代码片段
        ctClass.toClass();
    }

}
