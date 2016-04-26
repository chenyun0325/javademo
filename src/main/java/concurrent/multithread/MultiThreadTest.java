package concurrent.multithread;

public class MultiThreadTest extends Thread {

    public static void main(String[] args) throws InterruptedException {
        MultiThreadTest[] threadnum = new MultiThreadTest[2];
        for (int i = 0; i < 2; i++) {
            threadnum[i] = new MultiThreadTest(i * 50 + 1);// 1,51
            threadnum[i].start();
        }
        for (int i = 0; i < 2; i++) {
            threadnum[i].join();
        }
        System.err.println(sum);

    }

    static int sum = 0;

    private int startnum = 0;

    public MultiThreadTest(int start) {
        this.startnum = start;
    }

    public void run() {
        int sum = 0;
        for (int j = 0; j < 50; j++) {
            sum += startnum + j;
        }
        addsum(sum);
    }

    public static synchronized void addsum(int sum) {
        MultiThreadTest.sum += sum;
    }

}
