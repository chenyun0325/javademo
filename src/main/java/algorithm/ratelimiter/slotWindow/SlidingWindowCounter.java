package algorithm.ratelimiter.slotWindow;

public class SlidingWindowCounter {
    /**
     * 计数容器
     */
    private volatile SlotBaseCounter slotBaseCounter;

    /**
     * 窗口大小
     */
    private volatile int windowSize;

    /**
     * 循环数组指针
     */
    private volatile int head;

    public SlidingWindowCounter(int windowSize) {
        resizeWindow(windowSize);
    }

    public synchronized void resizeWindow(int windowSize) {
        this.windowSize = windowSize;
        this.slotBaseCounter = new SlotBaseCounter(windowSize);
        this.head = 0;
    }

    public void increase() {
        slotBaseCounter.increaseSlot(head);
    }

    public int totalAndAdvance() {
        int total = totalCount();
        advance();
        return total;
    }

    public void advance() {
        int tail = (head + 1) % windowSize;
        slotBaseCounter.wipeSlot(tail);
        head = tail;
    }

    public int totalCount() {
        return slotBaseCounter.totalCount();
    }

    @Override
    public String toString() {
        return "total = " + totalCount() + " head = " + head + " >> " + slotBaseCounter;
    }
}
