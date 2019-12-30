/**
 * @author Harry Zhang 
 * @since 2019/12/21 10:58 AM
 */
writer = new FileWriter("/Users/Harry/Workspace/groovy-learning/4-闭包/5.使用闭包进行资源清理/output.txt")
writer.write("Hello Groovy!")
// 忘记调用 write.close() 了。


/*使用闭包进行资源清理*/
new FileWriter("/Users/Harry/Workspace/groovy-learning/4-闭包/5.使用闭包进行资源清理/output.txt")
        .withWriter() { writer ->
            writer.write("Hello Groovy!")
        }
// 不需要自己调用 close 方法了，withWriter 这个方法已经帮我们做了 flush 操作。
