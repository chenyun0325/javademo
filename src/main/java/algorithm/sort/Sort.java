package algorithm.sort;

import java.util.Arrays;

/**
 * https://juejin.im/post/5cff49e75188257a6b40de80
 */
public class Sort {

    public static void main(String[] args) {

        int[] arr = {1, 5, 6, 2, 8, 8, 9, 10};

//        mpSort(arr);
//        mergeSort(arr,0,arr.length-1,new int[arr.length]);
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }


    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void mpSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            /**
             * 每一轮排序就有一个元素就是最小或者最大值,i表示
             */
            for (int j = 0; j < arr.length - 1 - i; j++) {
                int temp;
                if (arr[j] < arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void mpSortOpt(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            boolean isSort = true;
            /**
             * 每一轮排序就有一个元素就是最小或者最大值,i表示
             */
            for (int j = 0; j < arr.length - 1 - i; j++) {
                int temp;
                if (arr[j] < arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSort = false;
                }
            }
            if (isSort) {
                break;
            }
        }
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void slSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;//最小元素下标
            for (int j = i + 1; j < arr.length; j++) {
                /**
                 * 一次循环min可能变化多次
                 */
                if (arr[j] < arr[min]) {
                    min = j; //最小值
                }
            }
            //交换位置
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    public static void insertSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int value = arr[i];
            int j;//插入位置
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > value) {
                    arr[j + 1] = arr[j]; //移动数据
                } else {
                    break;
                }
            }
            arr[j + 1] = value;//插入数据
        }
    }


    public static void mergeSort(int[] arr,int left,int right,int[] temp){

        if (left<right){
            int mid = (left+right)/2;
            mergeSort(arr,left,mid,temp);//左边归并排序，使得左子序列有序
            mergeSort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序
            merge_v1(arr,left,mid,right,temp);//将两个有序子数组合并操作
        }
    }



    /**
     * https://www.cnblogs.com/chengxiao/p/6194356.html
     * <p>
     * 两个有序数组合并
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param temp
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {

        int i = left;//左序列指针
        int j = mid + 1;//右序列指针
        int t = 0;//临时数组指针
        /**
         * 左右两边序列
         */
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while (j <= right) {//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;

        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

    private static void merge_v1(int[] arr, int left, int mid, int right, int[] temp) {
        //复制需要合并的数据
        for (int s=left;s<=right;s++){
            temp[s]=arr[s];
        }
        int i = left;//左边首位下标
        int j= mid+1;//右边首位下标

        for (int k= left;k<= right;k++){
            if(i > mid){
                //如果左边的首位下标大于中部下标，证明左边的数据已经排完了。
                arr[k] = temp[j++];
            } else if (j > right){
                //如果右边的首位下标大于了数组长度，证明右边的数据已经排完了。
                arr[k] = temp[i++];
            } else if (temp[j] < temp[i]){
                arr[k] = temp[j++];//将右边的首位排入，然后右边的下标指针+1。
            } else {
                arr[k] = temp[i++];//将左边的首位排入，然后左边的下标指针+1。
            }

        }
    }

    public static void quickSort(int[] arr,int left,int right){
        int i,j,t,temp;

        if (left>right){
            return;
        }
        temp = arr[left];//temp存储基准值
        i = left;
        j= right;
        while (i != j) { //顺序很重要，要先从右边开始找
            while (arr[j] >= temp && i < j) {
                j--;
            }
            while (arr[i]<= temp && i<j){
                i++;
            }
            if (i<j){ //交换两个数在数组中的位置
                t = arr[i];
                arr[i]=arr[j];
                arr[j]=t;
            }
        }

        //最终将基数归位
        arr[left]=arr[i];
        arr[i] = temp;
        quickSort(arr,left, i-1);//继续处理左边的，这里是一个递归的过程
        quickSort(arr,i+1, right);//继续处理右边的 ，这里是一个递归的过程
    }
}
