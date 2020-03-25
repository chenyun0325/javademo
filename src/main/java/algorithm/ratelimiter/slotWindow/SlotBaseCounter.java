package algorithm.ratelimiter.slotWindow;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://www.iteye.com/blog/yunchow-2277593
 *
 *
 * 额外学习参考:
 *
 * https://blog.csdn.net/dbqb007/article/details/88082279
 *
 * https://blog.csdn.net/king0406/article/details/103129786
 *
 * https://blog.csdn.net/weixin_41485592/article/details/91825692
 *
 */
public class SlotBaseCounter {
    private int slotSize;
    private AtomicInteger[] slotCounter;

    public SlotBaseCounter(int slotSize) {
        this.slotSize = slotSize;
        this.slotCounter = new AtomicInteger[slotSize];
        for (int i = 0; i < slotSize; i++) {
            slotCounter[i] = new AtomicInteger(0);
        }
    }

    public void increaseSlot(int index) {
        slotCounter[index].incrementAndGet();
    }

    public void wipeSlot(int index) {
        slotCounter[index].set(0);
    }

    public int totalCount() {
        return Arrays.stream(slotCounter).mapToInt(i -> i.get()).sum();
    }

    @Override
    public String toString() {
        return Arrays.toString(slotCounter);
    }
}
