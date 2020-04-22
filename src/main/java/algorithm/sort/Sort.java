package algorithm.sort;

/**
 * https://juejin.im/post/5cff49e75188257a6b40de80
 */
public class Sort {

    public static void main(String[] args) {

        int[] arr = {1,5,6,2,8,8,9,10};

        mpSort(arr);
        System.out.println(arr);

    }


    /**
     * 冒泡排序
     * @param arr
     */
    public static void mpSort(int[] arr){

        for (int i = 0;i<arr.length-1;i++){

            /**
             * 每一轮排序就有一个元素就是最小或者最大值,i表示
             */
            for (int j=0;j<arr.length-1-i;j++){
                int temp;
                if (arr[j]<arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序
     * @param arr
     */
    public static void mpSortOpt(int[] arr){

        for (int i = 0;i<arr.length-1;i++){

            boolean isSort = true;
            /**
             * 每一轮排序就有一个元素就是最小或者最大值,i表示
             */
            for (int j=0;j<arr.length-1-i;j++){
                int temp;
                if (arr[j]<arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSort = false;
                }
            }
            if (isSort){
                break;
            }
        }
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void slSort(int[] arr){
        for (int i = 0; i<arr.length;i++){
            int min = i;//最小元素下标
            for (int j = i+1;j<arr.length;j++){
                /**
                 * 一次循环min可能变化多次
                 */
                if (arr[j]<arr[min]){
                    min = j; //最小值
                }
            }
            //交换位置
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    public static void insertSort(int[] arr){
        int n= arr.length;
        for (int i =1;i<n;++i){
            int value = arr[i];
            int j;//插入位置
            for (j = i-1;j>=0;j--){
                if (arr[j] >value){
                    arr[j+1]=arr[j] ; //移动数据
                }else {
                    break;
                }
            }
            arr[j+1] = value;//插入数据
        }
    }


    /**
     * https://www.cnblogs.com/chengxiao/p/6194356.html
     *
     * 两个有序数组合并
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param temp
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){

        int i = left;//左序列指针
        int j= mid+1;//右序列指针
        int t =0;//临时数组指针

    }
}
