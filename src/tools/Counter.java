package tools;

/**
 * @author Gadi Didi <gadi.didi85@gmail.com>
 * @version 1.8.0_201
 * @since 2019-05-05
 */
public class Counter {
    private int counter;

    /**
     * constructor Counter.
     */
    public Counter() {
        this.counter = 0;
    }
    /**
     * constructor Counter.
     * @param num -- the number we get in the constructor.
     */
    public Counter(int num) {
        this.counter = num;
    }
    /**
     * add number to current count.
     * @param number -- the number we want to increase.
     */
    public void increase(int number) {
        counter += number;
    }
    /**
     * subtract number from current count.
     * @param number -- the number we want to increase.
     */
    public void decrease(int number) {
        counter -= number;
    }
    /**
     * get current count.
     * @return number -- the value of the counter.
     */
    public int getValue() {
        return this.counter;
    }
}
