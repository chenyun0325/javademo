package algorithm.graph;

import java.util.Stack;

/**
 *
 * https://www.cnblogs.com/thousfeet/p/9229395.html
 *
 * https://www.jianshu.com/p/ff6db00ad866
 *
 */
public class Dijkstra {

    public static final int M = 100000;//代表无穷大

    public static final int N =100;//代表城市数量

    public static int[][] map = {
            {M,4,M,2,M},
            {4,M,4,1,M},
            {M,4,M,1,3},
            {2,1,1,M,7},
            {M,M,3,7,M}
    };

    public static int[] dist ,p;//距离与前驱节点

    public static boolean[] flag;//如果flag[i]=true,代表定点i加入结合S；否则定点i属于V-S

    public static int len;

    public static void main(String[] args) {

        dijKstra(0,map);
        findPath(0);
    }


    public static void dijKstra(int u,int[][] map){
        int n = map.length;//顶点数量
        len = n;

        //数组初始化
        dist = new int[n];
        p = new int[n];
        flag = new boolean[n];

        for (int i =0;i<n;i++){
            dist[i]=map[u][i];//初始化源点u到其它各个定点的最短路径长度
            flag[i]=false;
            if (dist[i] == M){
                p[i] = -1;
            }else {
                p[i] = u;
            }
        }
        dist[u]=0;
        flag[u]=true;//初始化集合S只有一个u

        for (int i=1;i<n;i++){//要加入n-1个顶点
            int temp = M,t=u;//在集合V-S选出一个距离源点u最近的节点t
            for (int j=1;j<n;j++){//找最短路径
                if (!flag[j]&&dist[j]<temp){
                    t = j;
                    temp = dist[j];
                }
            }
            if (t==u){//找不到t跳出循环
                return;
            }
            flag[t]=true;

            for (int j=1;j<n;j++){//更新V-S中与t临接顶点到源点u的距离
                if (!flag[j]&&map[t][j]<M){
                    if (dist[j]>dist[t]+map[t][j]){
                        dist[j]=dist[t]+map[t][j];
                        p[j]=t;
                    }
                }
            }
        }
    }

    public static void findPath(int u){
        int x;
        Stack<Integer> s = new Stack<>();
        System.out.println("源点:"+u);

        for (int i=1;i<len;i++){
            String path = "";
            x=p[i];
            while (x!=-1){
                s.push(x);
                x=p[x];
            }

            while (!s.isEmpty()){
                Integer top = s.peek();
                s.pop();
                path+=top+"--";
            }
            path = path + i;
            System.out.println("路径:"+path+",路径长度:"+dist[i]);
        }
    }

}
