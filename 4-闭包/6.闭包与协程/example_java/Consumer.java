package example_java;

/**
 * @author Harry Zhang
 * @since 2019/12/21 11:44 AM
 */
public class Consumer extends Thread {

    private final Resource resource;

    Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (resource) {
                int apple = resource.getApple();
                if (apple > 0) {
                    int eatCount = 1;
                    apple -= eatCount;
                    System.out.println("消费者：吃了[" + eatCount + "]个苹果，还剩[" + apple + "]个。");
                    resource.setApple(apple);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        System.out.println("消费者：没苹果了～～～");
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
