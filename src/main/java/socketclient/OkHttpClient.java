package socketclient;

import io.netty.handler.codec.http.HttpHeaders;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.asynchttpclient.*;

import java.io.IOException;

/**
 * Created by chenyun on 2019/11/15.
 */
public class OkHttpClient {

    public static void main(String[] args) throws IOException {
        String gatewayEntryUrl = "http://172.16.101.170:8081";
        String uri = "/gateway/api/001003002/dataSharing/Ib8X2CypW9nfQ618.htm?sign=a2c2a865c872c309cb87574d1cacdca2&message={\"creditkey\":\"475AH98DV9MK8BU2\",\"keyword\":\"91330783790994117P\",\"serviceid\":\"0e365c0d0dbe45619f90dd1bc9f8fa17\",\"servicename\":\"供应商注册\",\"use\":\"供应商注册\"}&requestTime=1573694573795&appKey=88dbeb4e51514fea9cfa7c1f1d72fb20";

        String uriEncoderOkHttpClient = "/gateway/api/001003002/dataSharing/Ib8X2CypW9nfQ618.htm?sign=a2c2a865c872c309cb87574d1cacdca2&message={%22creditkey%22:%22475AH98DV9MK8BU2%22,%22keyword%22:%2291330783790994117P%22,%22serviceid%22:%220e365c0d0dbe45619f90dd1bc9f8fa17%22,%22servicename%22:%22%E4%BE%9B%E5%BA%94%E5%95%86%E6%B3%A8%E5%86%8C%22,%22use%22:%22%E4%BE%9B%E5%BA%94%E5%95%86%E6%B3%A8%E5%86%8C%22}&requestTime=1573694573795&appKey=88dbeb4e51514fea9cfa7c1f1d72fb20";

        String url = gatewayEntryUrl + uri;
        HttpUrl parse = HttpUrl.parse(url);

        System.out.println(parse.uri().getRawQuery());
        System.out.println(parse.uri().getQuery());
        System.out.println(parse.uri().getPath());

        String rawQuery = parse.uri().getRawQuery();
        rawQuery = rawQuery != null ? "?" + rawQuery: "";
        uri = parse.uri().getPath() + rawQuery;

        System.out.println(uri);

        System.out.println(url);

        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient();


        url = gatewayEntryUrl + uriEncoderOkHttpClient;

        okhttp3.Request requestQueryStr = new okhttp3.Request.Builder().url(url).method("POST", RequestBody.create(MediaType.parse("application/json; charset=utf-8"),"{a:1}")).build();

        okhttp3.Response response = okHttpClient.newCall(requestQueryStr).execute();

        System.out.println(response);


        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient(new DefaultAsyncHttpClientConfig.Builder()
                .setConnectTimeout(10000)
                .setRequestTimeout(10000)
                .build());
        RequestBuilder requestBuilder = new RequestBuilder();
        url = gatewayEntryUrl + uriEncoderOkHttpClient;
        requestBuilder.setUrl(url);
        requestBuilder.setMethod("POST");
        requestBuilder.setHeaders(HttpHeaders.EMPTY_HEADERS);

        Request request = requestBuilder.build();

        asyncHttpClient.executeRequest(request, new AsyncCompletionHandler<Response>() {
            @Override
            public Response onCompleted(Response response) throws Exception {
                return response;
            }
        });


    }
}
