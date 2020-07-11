package algorithm.bit;

/**
 * https://mp.weixin.qq.com/s/FzAMbks0PUorZFsCFw7KTg
 * https://blog.csdn.net/qq_43777322/article/details/94782879
 * https://blog.csdn.net/qq_29229567/article/details/88735540
 */
public class IpUtil {

    public static void main(String[] args) {
        String ip ="255.255.255.255";
//        String ip1 = "127.0.0.1";
        int ip2int = ip2int(ip);
        System.out.println(ip2int);
        String int2Ip = int2Ip(ip2int);
        System.out.println(int2Ip);
        long ip2Long = ip2Long(ip);
        System.out.println(ip2Long);
        System.out.println(long2Ip(ip2Long));
    }



    /**
     * 算法原理
     * https://blog.csdn.net/shb_derek1/article/details/8064308
     *
     * ip->long：
     *      *1.将ip地址按字符串读入，用分隔符分割开后成为一个字符串数组{xyzo}。
     *      * 2.将数组里的字符串强转为long类型后执行：x^24+y^16+z^8+o  得到最后的返回值。
     *      * 3.这里的加权采用移位(<<)完成。
     *      * @param strIp :ip地址 例：x.y.z.o
     *      * @return  转换后的long类型值
     *
     * @param ip
     * @return
     */
    public static long ip2Long(String ip){
        String[] ipSplit = ip.split("\\.");
        int length = ipSplit.length;
        long ipLong =0;
        for (int i =0;i<length;i++){
            /**
             * 解析ip,左移8*i位
             */
            long ipSlice = Long.parseLong(ipSplit[i])<<8*i;
            ipLong = ipLong | ipSlice;
        }
        return ipLong;
    }

    public static String long2Ip(long longIp){
        //采用SB方便追加分隔符 "."
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf(longIp>>24)).append(".").
                append(String.valueOf((longIp&0x00ffffff)>>16)).append(".").
                append(String.valueOf((longIp&0x0000ffff)>>8)).append(".").
                append(String.valueOf(longIp&0x000000ff));
        return sb.toString();
    }





    public static int ip2int(String ip){
        String[] ipSplit = ip.split("\\.");
        int length = ipSplit.length;
        int ipInt =0;
        for (int i =0;i<length;i++){
            /**
             * 解析ip,左移8*i位
             */
            int ipSlice = Integer.parseInt(ipSplit[i])<<8*i;
            ipInt = ipInt | ipSlice;
        }
        return ipInt;
    }

    /**
     * -1的原码为11111111 11111111 11111111 11111111
     *
     * 当i=0时，pos=0
     * and = ipv4_int &(255 << pos)
     * 进行与运算
     * 11111111 11111111 11111111 11111111
     * 00000000 00000000 00000000 11111111
     * 结果为
     * 00000000 00000000 00000000 11111111
     * and >>> pos
     * 进行无符号右移0位运算，结果为255
     *
     * 当i=1时，pos=8
     * and = ipv4_int &(255 << pos)
     * 进行与运算
     * 11111111 11111111 11111111 11111111
     * 00000000 00000000 11111111 00000000
     * 结果为
     * 00000000 00000000 11111111 00000000
     * and >>> pos
     * 进行无符号右移8位运算，结果为255
     *
     * 当i=2时，pos=16
     * and = ipv4_int (255 << pos)
     * 进行与运算
     * 11111111 11111111 11111111 11111111
     * 00000000 11111111 00000000 00000000
     * 结果为
     * 00000000 11111111 00000000 00000000
     * and >>> pos
     * 进行无符号右移16位运算，结果为255
     *
     * 当i=3时，pos=24
     * and = ipv4_int &(255 << pos)
     * 进行与运算
     * 11111111 11111111 11111111 11111111
     * 11111111 00000000 00000000 00000000
     * 结果为
     * 11111111 00000000 00000000 00000000
     * and >>> pos
     * 进行无符号右移24位运算，结果为255
     * ————————————————
     * @param ipInt
     * @return
     */
    public static String int2Ip(int ipInt){
        String[] ipString = new String[4];
        for (int i=0;i<4;i++){
            //每8位一段,这里取当前要处理的最高位的位置
            int pos = i*8;
            /**
             * 获取当前处理IP段
             */
            int ipSlice = ipInt & (255 << pos);
            // 将当前 ip 段转换为 0 ~ 255 的数字，注意这里必须使用无符号右移
            ipString[i]=String.valueOf(ipSlice>>>pos);
        }
        return String.join(".",ipString);
    }
}
