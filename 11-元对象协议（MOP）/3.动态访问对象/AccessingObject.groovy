/**
 * @author Harry Zhang 
 * @since 2020/1/16 5:54 PM
 */
def printInfo(obj) {
    // 假设这是由用户输入的
    usrRequestProperty = 'bytes'
    usrRequestMethod = 'toUpperCase'

    // 动态调用一个属性使用 [] 操作符，或使用 . 号跟一个计算属性的 GString
    println obj[usrRequestProperty]
    println obj."$usrRequestProperty"

    // 动态调用方法使用 . 号，或调用 invokeMethod 传入方法名和参数列表，如果没有参数需要传 null.
    println obj."$usrRequestMethod"()
    println obj.invokeMethod(usrRequestMethod, null)
}

printInfo("hello")

println "访问对象的所有属性，使用 properties 属性或 getProperties()"
'hello'.properties.each { println it }
