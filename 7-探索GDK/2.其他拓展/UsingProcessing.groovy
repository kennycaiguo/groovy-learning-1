/**
 * Groovy 与系统级进程交互
 * <p>
 * 在 2.3 中（Demo1.groovy）我们看到了与系统进程交互的例子。Groovy 提供了访问 stdin stdout stderr 命令的便捷方法，分别对应为 in out err 属性。
 *
 * @author Harry Zhang
 * @since 2020/1/5 4:53 PM
 */

/**
 * 案例：向 wc 的标准输入写入数据，从标准输出读取。
 *
 * wc 是类 Unix 系统上流行的程序，它会向标准输出打印其从标准输入中发现的单词数、行数、和字符数。
 *
 * process.out 得到的是一个 OutputStream。
 * withWriter() 方法接受一个闭包作为参数，它会为当前流创建一个 Writer，将其传递给闭包。该方法确保闭包返回后将其流关闭。
 */
process = "wc".execute()

// 若不使用 withWriter()，则需要手动刷新关闭流。
// OutputStream outputStream = process.out
// outputStream.write("Let the work know..\n".getBytes())
// outputStream.write("Groovy Rocks!\n".getBytes())
// outputStream.flush()
// outputStream.close()

process.out.withWriter() { it ->
    // 将输入发送给进程，使用 << 操作符将数据写入到流。
    it << "Let the work know..\n"
    it << "Groovy Rocks!\n"
}

// 从进程读取输入
println process.in.text
