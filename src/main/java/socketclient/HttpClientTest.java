package socketclient;

import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.*;
import org.asynchttpclient.uri.Uri;

import java.util.concurrent.ExecutionException;

/**
 * Created by chenyun on 2019/11/5.
 */
public class HttpClientTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient(new DefaultAsyncHttpClientConfig.Builder()
                .setConnectTimeout(10000)
                .setRequestTimeout(10000)
                .build());

        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.setUri(Uri.create("http://172.16.101.172:8080/meta/listAppMeta?appName=zcy-conf-web"));
//        requestBuilder.setUri(Uri.create("https://www.ccgp-chongqing.gov.cn/gwebsite/api/v1/gpwSecret/getNewToken?token=4242e5d016b4d24382f5398c442bc456"));
        requestBuilder.setUrl("https://www.ccgp-chongqing.gov.cn/gwebsite/api/v1/gpwSecret/getNewToken?token=4242e5d016b4d24382f5398c442bc456");
        requestBuilder.setMethod("GET");
        requestBuilder.setHeaders(HttpHeaders.EMPTY_HEADERS);
        requestBuilder.setBody(new byte[0]);
        Request request = requestBuilder.build();
        asyncHttpClient.executeRequest(request, new AsyncCompletionHandler<Response>() {
            @Override
            public Response onCompleted(Response response) throws Exception {
                System.out.println(response.getStatusCode());
                System.err.println(response.getResponseBody());
                return response;
            }
        });


//        BoundRequestBuilder boundRequestBuilder = asyncHttpClient.prepareGet("https://www.ccgp-chongqing.gov.cn/gwebsite/api/v1/gpwSecret/getNewToken?token=4242e5d016b4d24382f5398c442bc456");
//
//        Request requestGet = boundRequestBuilder.build();
//
//        asyncHttpClient.executeRequest(requestGet, new AsyncCompletionHandler<Response>() {
//            @Override
//            public Response onCompleted(Response response) throws Exception {
//                System.out.println(response.getStatusCode());
//                System.err.println(response.getResponseBody());
//                return response;
//            }
//        });


    }
}
