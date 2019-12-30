package example_java;

/**
 * 共享资源
 *
 * @author Harry Zhang
 * @since 2019/12/21 11:45 AM
 */
public class Resource {

    private int apple = 0;

    public void setApple(int apple) {
        this.apple = apple;
    }

    public int getApple() {
        return apple;
    }
}
