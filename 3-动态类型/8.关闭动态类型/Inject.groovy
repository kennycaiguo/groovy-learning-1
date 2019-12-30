/**
 * 案例2：未开启静态检查的 Groovy 与开启静态检查的 Groovy
 *
 * @author Harry Zhang 
 * @since 2019/12/19 9:43 PM
 */
// @TypeChecked 如果开启了静态类型编译，以下的动态特性都不会生效，编译期报错。
def shoutingString(String str) {
    // str 调用了一个不属于 String 类的方法？这也是 Groovy 「动态方法」的体现。
    println str.shout();
}

// 定义一个字符串
str = 'hello'
// 通过 Groovy 的元编程给 String 类添加一个自定义方法 shout，右边是方法体。
str.metaClass.shout = { -> toUpperCase() }
// 把添加了动态方法的 String 类传入到 shoutingString 中，shoutingString 中调用了我们的动态方法。
shoutingString(str)


// ==========================================================================================
// 综上，如果没开启静态类型编译都不会出问题。但是 IDEA 还是智能的提示了「不存在」的方法。
