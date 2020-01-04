/**
 * 一）使用 Sleep，Groovy 睡眠有两种：
 *
 * 1. 一个是 Groovy 拓展在 Object 类上的方法：Object.sleep()
 *    此 sleep 会在睡眠时「忽略中断」。即：中断方法对其无效。
 *
 * 2. 一个是源自 Java 的：Thread.sleep()
 *    不会忽略中断，被中断的线程会抛出 InterruptedException 异常。
 *
 * -----------------------------------
 * 二）Object.sleep() 方法有两个重载方法：
 *
 *  一个是：
 *       Sleep for so many milliseconds, even if interrupted. 【睡眠指定时间，即使线程被中断。】
 *       public static void sleep(Object self, long milliseconds){}
 *
 *  一个是：
 *       Sleep for so many milliseconds, using a given closure for interrupt processing.【睡眠指定时间，但可以提供一个闭包参数用作被中断时处理。】
 *       public static void sleep(Object self, long milliseconds, Closure onInterrupt){}
 *
 * @author Harry Zhang 
 * @since 2020/1/3 11:21 PM
 */

/**
 * 案例1：Object.sleep(long milliseconds) 会忽略中断。
 * （什么是线程中断？看 SleepJavaVersion.java，在 Java 中中断子线程，子线程会抛出异常的。）
 *
 * Thread.start 会启动一个子线程
 */
thread = Thread.start {
    println "Thread started"
    def startTime = System.nanoTime()
    new Object().sleep(2000)
    def endTime = System.nanoTime()
    println "Thread done in ${(endTime - startTime) / 10**9} seconds"
}

// 这里先睡 100 毫秒是让子线程先执行。
new Object().sleep(100)
println "Let's interrupt that thread"
// 试图调用 interrupt() 中断正在睡眠中的 thread 线程，但是会失败。thread 依然会睡完之后继续执行！
thread.interrupt()
thread.join()


/**
 * 案例2：Object.sleep(long milliseconds, Closure onInterrupt) 提供一个闭包参数，告诉 Groovy 遇到中断时怎么处理。
 *
 * 如果线程被中断，会把 InterruptException 作为参数传递给闭包。
 * - 在闭包内返回 true 会继续执行忽略中断
 * - 在闭包内返回 false 会中断执行。
 */
println("------------------------------------")
def playWithSleep(flag) {
    def thread = Thread.start {
        println "Thread started"
        def startTime = System.nanoTime()
        new Object().sleep(2000) {
            println "interrupted.... $it"
            flag
        }
        def endTime = System.nanoTime()
        println "Thread done in ${(endTime - startTime) / 10**9} seconds"
    }
    thread.interrupt()
    thread.join()
}

playWithSleep(true)
playWithSleep(false)
