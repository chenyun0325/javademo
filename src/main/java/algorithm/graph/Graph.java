package algorithm.graph;



/**
 * https://www.cnblogs.com/DarrenChan/p/9547869.html
 */
public class Graph {

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G','H','I','J','K'};
        char[][] edges = new char[][]{
                {'A', 'C'},
                {'A', 'D'},
                {'A', 'F'},
                {'B', 'C'},
                {'C', 'D'},
                {'E', 'G'},
                {'D', 'G'},
                {'I', 'J'},
                {'J', 'G'},
                {'E', 'H'},
                {'H', 'K'}
        };
        MatrixNDG ndg = new MatrixNDG(vexs,edges);
        ndg.print();
        ndg.dfs();
        System.out.println();
    }

    static class MatrixNDG {
        int size;//图顶点个数
        char[] vertexs;//图顶点名称
        int[][] matrix;//图关系矩阵


        public MatrixNDG(char[] vertexs, char[][] edges) {
            size = vertexs.length;
            matrix = new int[size][size];//设置图关系矩阵大小
            this.vertexs = vertexs;

            for (char[] edge : edges) {//设置矩阵值
                int p1 = getPosition(edge[0]);
                int p2 = getPosition(edge[1]);
                matrix[p1][p2] = 1;//无向图，在两个对称位置存储
                matrix[p2][p1] = 1;
            }

        }


        public void dfs(){
            boolean[] traversed= new boolean[size];
            for (int i =0;i<size;i++){
                traversed[i]= false;
            }
            System.out.print(vertexs[0]);
            traversed[0]=true;
            dfs(0,0,traversed);
        }

        private void dfs(int x,int y,boolean[]traversed){
            while (y<size-1){
                if (matrix[x][y]!=0&traversed[y]==false){//有路径&没有被访问
                    System.out.print(vertexs[y]);
                    traversed[y]=true;
                    dfs(y,0,traversed);
                }
                y++;
            }
        }


        //根据顶点名称获取对应的矩阵下标
        private int getPosition(char ch) {
            for (int i = 0; i < vertexs.length; i++)
                if (vertexs[i] == ch)
                    return i;
            return -1;

        }


        public void print(){
            for (int[] rows : matrix) {
                for (int col : rows) {
                    System.out.print(col+"");
                }
                System.out.println();
            }

        }


    }
}
