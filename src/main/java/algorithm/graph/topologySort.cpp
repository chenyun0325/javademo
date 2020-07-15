//https://www.cnblogs.com/xwh-blogs/p/12721648.html
#include <iostream>
#include <cstring>
#include <cstdio>
using namespace std;
int n,m;
int s[100][100];        //邻接矩阵，用于保存图的结构
int visit[100];        //顶点标志
int indegree[100];        //各个顶点的入度
int ans[100];
int sum=0;            //记录拓扑排序总数
void DFS(int num)        //num记录当前已经访问的顶点个数
{
    int i,j,k;
    if (num==n){        //访问完n个顶点代表这是一条新的可走的拓扑路径
        sum++;             //路径总数加1
        for (i=0;i<n;i++)
            printf("%d ",ans[i]);
        printf("\n");
        return;
    }

    for (i=0;i<n;i++){
        if ((!indegree[i])&&(!visit[i])){        //如果顶点i入度为0且没有被访问过
            for (j=0;j<n;j++){
                if (s[i][j])    indegree[j]--;    //遍历所有和它连接的点并且断开联系（即点的入度减1）
            }

            visit[i]=1;            //该入度为0的点标记访问过
            ans[num]=i;            //储存值
            DFS(num+1);            //以访问点数+1

            for (k=0;k<n;k++){    //回溯，恢复现场，将入度重新加一，并且将该顶点标记为未访问
                if (s[i][k])    indegree[k]++;
            }
            visit[i]=0;            //回溯标志 方便下一次for循环使用
                                //ans没有必要回溯，因为后续的值可以直接覆盖
        }
    }
    return;
}

int main(){
    int a,b,i,j,num;
    scanf("%d%d",&n,&m);
    memset(indegree,0,sizeof(indegree));
    memset(s,0,sizeof(s));
    memset(visit,0,sizeof(visit));
    memset(ans,0,sizeof(ans));

    for (i=1;i<=m;i++){
         scanf("%d%d",&a,&b);
        s[a][b]=1;
        indegree[b]++;
     }

    DFS(0);
    cout<<"sum:"<<sum;
    return  0;
}