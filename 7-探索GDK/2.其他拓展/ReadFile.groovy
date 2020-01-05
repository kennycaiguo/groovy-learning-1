/**
 * Groovy 对 IO 的拓展
 *
 * Groovy 向 BufferedReader、InputStream 和 File 添加一个 text 属性，我们可以把文件全部内容读取到一个 String 中。
 *
 * @author Harry Zhang 
 * @since 2020/1/5 7:26 PM
 */

def filePath = '/Users/Harry/Workspace/groovy-learning/7-探索GDK/2.其他拓展/demo-output.txt'

/**
 * 案例1：使用 File 的 text 属性读取整个文件
 */
println new File(filePath).text

/**
 * 案例2：每次只读取文件的一行
 */
println "-----------------------------------"
println new File(filePath).eachLine { line ->
    // 可以在这里执行对某一行的处理
    println line
}

/**
 * 案例3：读取符合条件的行
 */
println "-----------------------------------"
println new File(filePath).filterLine() {
    // 模糊匹配，该行内容有 line 字符返回 true
    it =~ /line1/
}

/**
 * 案例4：Groovy 中可以方便的向文件或流中写入内容。OutputStream ObjectOutputStream 和 Writer 类都可以通过 leftShift()（<< 操作符）得到更新。
 */
new File(filePath).withWriter { file ->
    file << "some data..."
}
