/**
 * @author Harry Zhang
 * @since 2020/1/4 10:16 PM
 */
public class SleepJavaVersion {

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();

        Thread.sleep(200);
        System.out.println("让我们中断子线程");
        threadDemo.interrupt();
        threadDemo.join();
    }

}

class ThreadDemo extends Thread {
    @Override
    public void run() {
        System.out.println("子线程启动");
        long startTime = System.nanoTime();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("子线程被中断了～～～～");
            e.printStackTrace();
            // 在 Java 中，使用 Sleep 必须使用 try catch；如果线程被中断不希望继续往下执行还需要手动抛出异常。
            throw new RuntimeException("子线程被中断了!");
        }
        long endTime = System.nanoTime();
        System.out.println("子线程执行完毕，耗费时间：" + (endTime - startTime));
    }
}
