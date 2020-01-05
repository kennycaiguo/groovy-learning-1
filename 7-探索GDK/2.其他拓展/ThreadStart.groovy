/**
 * Groovy 中使用守护线程只需要 Thread.startDaemon，当主线程和非守护线程都退出后守护线程会被中止。
 *
 * @author Harry Zhang 
 * @since 2020/1/5 6:00 PM
 */

def printThreadInfo(msg) {
    def thread = Thread.currentThread()
    println "$msg Thread is ${thread} Daemon? ${thread.isDaemon()}"
}

// 主线程
printThreadInfo "Main"

// 子线程1
Thread.start {
    printThreadInfo("Started")
    sleep(3000) { println "Interrupted" }
    println "Finished Started"
}

// 子线程2（守护线程）
sleep(1000)
Thread.startDaemon {
    printThreadInfo("Started Daemon")
    sleep(5000) { println "Interrupted" }
    // 不会执行到这里
    println "Finished Started Daemon"
}
