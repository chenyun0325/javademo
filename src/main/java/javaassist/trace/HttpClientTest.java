package javaassist.trace;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * https://juejin.im/post/5d96c5b4518825581f668265
 */
public class HttpClientTest {

    public static void main(String[] args) throws NotFoundException, CannotCompileException {
        HttpClientInstrumentation.enhance();
        HttpGet httpGet = sendGet();
        Header header = httpGet.getHeaders("source")[0];
        System.out.println(header.getValue());
    }

    private static HttpGet sendGet() {
        //创建默认的httpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = null;

        try {
            //用get方法发送http请求
            get = new HttpGet("http://www.baidu.com/");
            System.out.println("执行get请求:...." + get.getURI());
            CloseableHttpResponse httpResponse = null;

//            get.setHeader("source", "abc");

            //发送get请求
            httpResponse = httpClient.execute(get);
            try {
                //response实体
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    System.out.println("响应状态码:" + httpResponse.getStatusLine());
                    System.out.println("-------------------------------------------------");
                    System.out.println("响应内容:" + EntityUtils.toString(entity));
                    System.out.println("-------------------------------------------------");
                }
            } finally {
                httpResponse.close();
            }
        } catch (Exception ignore) {
            ;
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return get;
    }

}
