package javaassist.trace;

import org.apache.http.HttpRequest;

public class HttpClientInterceptor {

    public static void intercept(HttpRequest httpRequest) {
        httpRequest.setHeader("source", "test");
    }
}
