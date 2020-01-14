/**
 * 在 Groovy 类中引用一个 Groovy 脚本，使用 GroovyShell()
 *
 * @author Harry Zhang 
 * @since 2020/1/14 8:40 AM
 */
println "In script2"
def shell = new GroovyShell()
shell.evaluate(new File("script1.groovy"))
// 简化写法
evaluate(new File("script1.groovy"))
