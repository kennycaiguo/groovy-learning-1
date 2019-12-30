/**
 * 一）Groovy 中可以使用单引号创建字符串字面常量，'' 和 "" 创建出来的都是 String 的实例。
 * 如果想显式的创建字符串，需要指定类型如：'a' as char
 */

// 双引号都可以放在里面而不需要转义字符
println 'He said, "That is Groovy"'

// 验证：单引号创建的字符串也是 String 类型
str = 'A string'
// 输出：java.lang.String
println str.getClass().name


/*
 * 二）使用单引号创建的String会被看作是一个纯粹的字面量，如果里面放了表达式，Groovy不会计算他们。 
 * 要对 String 中的表达式进行求值运算，必须使用双引号。
 */
value = 25
// 输出：The value is ${value}
println 'The value is ${value}'


/*
 * 三）Java 的 String 是可不变的，Groovy 也是如此。一旦创建就不能修改其内容了，可以通过 [] 操作符取其中某个字符。
 */
str = 'hello'
println str[2]
try {
    // 修改会失败
    str2[2] = '!'
} catch (e) {
    println e
}


/*
 * 四）可以使用双引号（""）或正斜杠（//）创建一个表达式，不过双引号经常被用于定义字符串表达式，正斜杠用于正则表达式。
 */
value = 12
// \$ 用来转义输出第一个 $，第二个 $ 用来输出表达式。
// 输出：He paid $12 for that
println "He paid \$${value} for that"

// 如果定义字符串使用的是正斜杠，可以不必转义 $，如：
println(/He paid $${value} for that/)
