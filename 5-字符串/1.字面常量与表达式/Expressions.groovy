/**
 * 一）字符串（String）惰性求值特性
 */
what = new StringBuilder('fence')
text = "The cow jumped over the ${what}"
// The cow jumped over the fence
println text

what.replace(0, 5, 'moon')
// 修改了 StringBuilder 值之后但是并没有修改 text 输出：The cow jumped over the moon
println text


/**
 * 二）使用单引号和使用双引号（或正斜杠）创建的字符串不一样，前者是普通的 java.lang.String，而后者被称之为 GString。
 */
def printClassInfo(obj){
    println "Class: ${obj.getClass().name}"
    println "Superclass: ${obj.getClass().superclass.name}"
}

val = 125
// Class: org.codehaus.groovy.runtime.GStringImpl
// Superclass: groovy.lang.GString
printClassInfo("The Stock closed at ${val}")
// Class: org.codehaus.groovy.runtime.GStringImpl
// Superclass: groovy.lang.GString
printClassInfo(/The Stock closed at ${val}/)
// Class: java.lang.String
// Superclass: java.lang.Object
// 可以看到：虽然使用双引号定义字符串，但是Groovy会智能的分析是否使用了一个普通的 String 而决定是否创建 GString。
printClassInfo("The is a simple String")