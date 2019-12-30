package example_java;

/**
 * 在 Java 中，wait() 和 notify() 与多线程组合使用，实现了协程。
 *
 * @author Harry Zhang
 * @since 2019/12/21 11:45 AM
 */
public class ExecuteMain {
    public static void main(String[] args) {
        Resource resource = new Resource();
        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);

        consumer.start();
        producer.start();
    }
}
