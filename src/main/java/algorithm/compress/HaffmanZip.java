package algorithm.compress;

import java.util.*;

public class HaffmanZip {
    public static void main(String[] args) {
        //得到`"i like like like java do you like a java"`对应byte[]数组
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);
        List<NodeData> nodes = getNodes(contentBytes);
        NodeData rootNode = createHaffMan(nodes);
        preOrder(rootNode);
        //执行优化代码
        Map<Byte, String> huffmanCode = getCodes(rootNode);
        byte[] zipBytes = zip(contentBytes, huffmanCode);
        System.out.println(zipBytes);

        System.out.println(byteToBitString((byte) 1));
//        System.out.println(Integer.toBinaryString((byte)1));

        byte[] source = decode(huffmanCode, zipBytes);

        System.out.println("新字符数组经过根据新map获取对应字符：" + new String(source));

    }

    private static List<NodeData> getNodes(byte[] bytes) {
        List<NodeData> nodeList = new ArrayList<>();
        /**
         * 遍历bytes，统计每一个byte出现的次数->map[key,value]
         */
        Map<Byte, Integer> freqMap = new HashMap<>();
        for (byte b : bytes) {
            Integer freqItem = freqMap.get(b);
            if (freqItem == null) {
                freqMap.put(b, 1);
            } else {
                freqMap.put(b, freqItem + 1);
            }
        }
        for (Map.Entry<Byte, Integer> entry : freqMap.entrySet()) {
            nodeList.add(new NodeData(entry.getKey(), entry.getValue()));
        }
        return nodeList;
    }

    private static NodeData createHaffMan(List<NodeData> nodeDataList) {

        /**
         *  操作思路
         *  1.取出两个权值最小的节点二叉树
         *  2.根据两个权值最小的二叉树构建成一个新的二叉树
         *  3.删除原先两个权值最小的节点二叉树
         *  4.将新的二叉树放入队列，并构建新的队列
         *  5.新的队列进行从小到大排序
         */
        while (nodeDataList.size() > 1) {
            Collections.sort(nodeDataList);
            //取出第一个权值最小的二叉树
            NodeData leftNode = nodeDataList.get(0);
            //取出第二个权值最小的二叉树
            NodeData rightNode = nodeDataList.get(1);
            //根据两个权值最小的二叉树构建成一个新的二叉树 同时构建连接
            NodeData parentNode = new NodeData(null, leftNode.weight + rightNode.weight);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            //删除原先两个权值最小的节点二叉树
            nodeDataList.remove(leftNode);
            nodeDataList.remove(rightNode);
            //将新的二叉树放入队列，并构建新的队列
            nodeDataList.add(parentNode);
        }


        return nodeDataList.get(0);
    }

    private static void preOrder(NodeData root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("哈弗曼树为空！");
        }
    }

    //将哈夫曼编码以Key：Vale形式存放
    //比如说32->01 97->100 100->11000 等等[形式]
    static Map<Byte, String> huffmanCodes = new HashMap<>();

    //2.在生成赫夫曼编码表示，需要去拼接路径，定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 使用StringBuilder记录所经过的huffmanCodes路径:'a'的路径是:1->0->0
     * 使用Key:Value 键值的方式存放对应的字符：路径;比如：(a:1000)、(j:0010)、(o:0011)
     * 字符特点：data !=null （属于叶子节点)
     * 假设查询字符v的路径，发现左递归不符合后还需要进行右递归判断
     * <p>
     * 功能:将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param node          传入节点
     * @param code          路径：左节点是0 右节点是1
     * @param stringBuilder 用于拼接路径
     */

    private static void getCodes(NodeData node, String code, StringBuilder stringBuilder) {
        StringBuilder str = new StringBuilder(stringBuilder);
        //code 加入str
        str.append(code);

        if (node != null) {
            //字符特点 data != null 叶子节点
            if (node.data == null) {
                //向左递归
                getCodes(node.left, "0", str);
                //向右递归
                getCodes(node.right, "1", str);
            } else {
                //`使用Key:Value 键值`的方式存放对应的`字符：路径;`比如：`(a:110)、(j:0000)、(o:1000)`
                huffmanCodes.put(node.data, str.toString());
            }
        }

    }

    private static Map<Byte, String> getCodes(NodeData node) {
        //根节点为空则不做处理
        if (node == null) {
            return null;
        }
        //向左递归
        getCodes(node.left, "0", stringBuilder);
        //向右递归
        getCodes(node.right, "1", stringBuilder);
        return huffmanCodes;
    }

    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用huffmanCodes将bytes 转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        System.err.println("赫夫曼编码的补码串:" + stringBuilder.toString());
        System.err.println("赫夫曼编码的补码串长度:" + stringBuilder.length());
        //2.1010100010111111...思路：补码->反码->原码->转成Byte
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //3.创建压缩之后byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转成一个byte ,放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }


    /**
     * @param buffmanCodes 哈夫曼编码表map
     * @param huffmanBytes 哈夫曼得到的字节数组
     * @return就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> buffmanCodes, byte[] huffmanBytes) {
        //1.使用StringBuilder记录新字符组的补码
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //最后一个字符无需补位
            boolean flag = (i == huffmanBytes.length - 1 ? true : false);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        System.out.println("新字符组的补码串:" + stringBuilder.toString());
        System.out.println("新字符组的补码串长度:" + stringBuilder.length());
        //找到记录原字符byte转为自定义哈夫曼编码Map、根据自定义哈夫曼编码Map反获取原字符
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> item : huffmanCodes.entrySet()) {
            map.put(item.getValue(), item.getKey());
        }
        System.out.println("根据自定义哈夫曼编码Map反获取(编码:字符)组成新map = " + map);

        //根据前缀编码
        List<Byte> list = new ArrayList<>();

        for (int j = 0; j < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder.substring(j, j + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            j = j + count;
        }
        //当for循环结束后，list就存放了字符串"i like like like java do you like a java"的所有字符
        //把集合里的数据放入byte[]中返回 byte[] b = new byte[list.size()];
        byte[] b = new byte[list.size()];
        for (int k = 0; k < b.length; k++) {
            b[k] = list.get(k);
        }
        return b;

    }

    /**
     * 将一个byte转成-个二进制的字符串
     *
     * @param b
     * @return
     */
    private static String byteToBitString(byte b) {
        //使用变量保存b
        int temp = b;//将b转成int
        //如果是正数我们还存在补高位
        temp |= 256;
        String str = Integer.toBinaryString(temp);
        return str.substring(str.length() - 8);
    }

    /**
     * https://blog.csdn.net/liaomin416100569/article/details/7386242
     * https://segmentfault.com/a/1190000024545174
     * 将一个byte转成-个二进制的字符串
     *
     * @param flag 标志是否需要补高位、如果是最后一个字节则无需补高位
     * @param b    传入的byte
     * @return 返回b对应的二进制串（按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b;//将b转成int
        if (flag) {
            //如果是正数我们还存在补高位
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }


    //使用一个方法将需要的方法疯转起来，方便调用
    private static byte[] haffmanZip(byte[] bytes) {
        //将准备构建哈弗曼树的Node节点放到List：Node[data=97, weight= 5],Node[data=32,weight = .....
        List<NodeData> datalist = getNodes(bytes);
        //根据字符出现次数作为权重，构建哈弗曼树
        //获取哈弗曼树的根节点
        NodeData root = createHaffMan(datalist);
        //根据哈夫曼树进行自定义哈夫曼编码实现
        Map<Byte, String> huffmanCodes = getCodes(root);
        //将原字符的所有哈夫曼编码构建成编码串
        //根据编码串进行补码->反码->原码构建新字符
        byte[] huffmanCodeBy = zip(bytes, huffmanCodes);
        return huffmanCodeBy;
    }

}
