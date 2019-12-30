/**
 * Groovy 中的正则表达式
 *
 * 1. 从字符串创建一个模式，使用 ~ 操作符；
 * 2. 定义一个表达式，使用正斜杠，如：/(G|g)roovy/；
 * 3. 单个匹配，使用 =~；
 * 4. 精确匹配，使用 ==~。
 *
 * @author Harry Zhang 
 * @since 2019/12/30 12:20 PM
 */

/**
 * 案例1：使用 ~ 操作符创建的字符串是一个 Pattern 对象。
 * 可以使用正斜杠 / 和反斜杠 \ 来定义一个表达式，但是建议使用正斜杠，原因是 / 不需要对反斜杠进行转义。
 */
obj = ~'hello'
println obj.getClass().name // java.util.regex.Pattern

/**
 * 案例2：部分匹配和精确匹配
 * =~ 操作符会返回一个 Matter 对象
 */
pattern = ~'(G|g)roovy'
text = 'Groovy is Hip'
if (text =~ pattern)
    println "match" // 部分匹配成功
else
    println "no match"

if (text ==~ pattern)
    println "match"
else
    println "no match" // 精确匹配失败


/**
 * 案例3：如果有一个匹配，Matcher 会返回 true，如果有多个匹配会返回一个匹配的数组。
 */
matcher = 'Groovy is groovy' =~ /(G|g)roovy/
println "Size of matcher is ${matcher.size()}"
println "whith elements ${matcher[0]} and ${matcher[1]}"


/**
 * 案例4：使用 replaceFirst() 和 replaceAll() 替换
 */
str = "Groovy is groovy, really groovy"
println str
// 单个匹配，替换所有
result = (str =~ /groovy/).replaceAll("hip")
println result
