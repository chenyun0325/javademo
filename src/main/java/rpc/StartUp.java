package rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by cy111966 on 2016/7/19.
 */
public class StartUp {
    public static final int port=9001;
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(port);
           // ss.bind(new InetSocketAddress("33.33.48.190",port));
            while (true){
                Socket socket = ss.accept();
                new RpcThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
