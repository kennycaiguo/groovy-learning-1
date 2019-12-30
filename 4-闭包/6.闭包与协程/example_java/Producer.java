package example_java;

/**
 * @author Harry Zhang
 * @since 2019/12/21 11:44 AM
 */
public class Producer extends Thread {

    private final Resource resource;

    Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (resource) {
                int apple = resource.getApple();
                if (apple < 1) {
                    int buyCount = 10;
                    apple += buyCount;
                    resource.setApple(apple);
                    System.out.println("生产者：买了[" + buyCount + "]个苹果，共有[" + resource.getApple() + "]个苹果。");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        resource.notifyAll();
                        resource.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
