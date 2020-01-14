/**
 * 调用带参数的脚本
 *
 * 知识点：binding 对象，每一个脚本都有一个 binding 对象，创建 GroovyShell 时传递 binding 对象，被调用脚本就能访问和设置调用方传入的 binding。
 *
 * 如果想将调用方的 binding 与脚本的 binding 分开，只需手动创建并通过方法赋值： new Binding().setProperty("name", "Harry")
 *
 * @author Harry Zhang
 * @since 2020/1/14 8:40 AM
 */
name = "Zhang"
def groovyShell = new GroovyShell(binding)
def result = groovyShell.evaluate(new File("script1a.groovy"))
println "result: $result"
println "name: $name"
