package algorithm.graph;


import java.util.LinkedList;

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
        ndg.bfs();
        System.out.println();

        ListNDG listNDG = new ListNDG(vexs, edges);
        listNDG.print();
        listNDG.dfs();
        listNDG.bfs();
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
                    /**
                     * 行列转换以达到深度优先
                     */
                    dfs(y,0,traversed);
                }
                y++;
            }
        }


        public void bfs(){
            boolean[] traversed= new boolean[size];
            for (int i =0;i<size;i++){
                traversed[i]= false;
            }
            System.out.print(vertexs[0]);
            traversed[0]=true;
            bfs(0,traversed);
        }

        private void bfs(int x, boolean[] traversed) {
            LinkedList<Character> list = new LinkedList<>();
            int y =0;
            while (y <= size - 1) {//获取1层所有节点
                if (matrix[x][y]!=0&&traversed[y]==false) {
                    System.out.print(vertexs[y]);
                    traversed[y]=true;
                    list.add(vertexs[y]);
                }
                y++;
            }
            /**
             * 下一层
             */
            while (!list.isEmpty()) {
                char ch = list.poll();
                int p = getPosition(ch);
                bfs(p,traversed);
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


    static class ListNDG{

        Vertex[] vertexLists;//邻接表数组

        int size;

        class Vertex{//邻接表
            char ch;
            Vertex next;

            public Vertex(char ch) {
                this.ch = ch;
            }

            public void add(char ch){
                Vertex head = this;
                while (head.next != null) {
                    head=head.next;
                }
                head.next = new Vertex(ch);
            }
        }

        public ListNDG(char[] vertexs,char[][] edges) {
            this.size = vertexs.length;
            this.vertexLists = new Vertex[size];
            //设置邻接表每个点
            for (int i=0;i<size;i++){
                this.vertexLists[i]= new Vertex(vertexs[i]);
            }
            //存储边信息
            for (char[] edge : edges) {
                int p1 = getPosition(edge[0]);
                vertexLists[p1].add(edge[1]);
                int p2 = getPosition(edge[1]);
                vertexLists[p2].add(edge[0]);
            }
            
        }

        public void dfs(){
            boolean[] traversed = new boolean[size];
            for (int i=0;i<size;i++){
                traversed[i]= false;
            }
            for (int i=0;i<size;i++){
                if (!traversed[i]){
                    dfs(traversed,vertexLists[i]);
                }
            }
        }

        public void dfs(boolean[] traversed,Vertex temp){
            System.out.print(temp.ch);
            traversed[getPosition(temp.ch)]=true;
            while (temp != null){
                if (!traversed[getPosition(temp.ch)]){
                    dfs(traversed,vertexLists[getPosition(temp.ch)]);
                }
                temp = temp.next;
            }
        }

        public void bfs(){
            boolean[] traversed= new boolean[size];
            for (int i =0;i<size;i++){
                traversed[i]= false;
            }
            System.out.print(vertexLists[0].ch);
            traversed[0]=true;
            bfs(0,traversed);
        }

        private void bfs(int i, boolean[] isTraversed) {
            Vertex temp=vertexLists[i];
            LinkedList<Vertex> list= new LinkedList<>();
            while(temp!=null){
                if(isTraversed[getPosition(temp.ch)]==false){
                    System.out.print(temp.ch + " ");
                    isTraversed[getPosition(temp.ch)]=true;
                    list.add(temp);
                }
                temp=temp.next;
            }
            while(!list.isEmpty()){
                Vertex v=list.pop();
                int t=getPosition(v.ch);
                bfs(t,isTraversed);
            }
        }


        public void print(){
            for (Vertex vertex : vertexLists) {
                while (vertex != null) {
                    System.out.print(vertex.ch);
                    vertex=vertex.next;
                }
                System.out.println();
            }
        }


        //根据顶点名称获取对应的矩阵下标
        private int getPosition(char ch) {
            for (int i = 0; i < size; i++)
                if (vertexLists[i].ch == ch)
                    return i;
            return -1;

        }
    }
}
