/**
 * Map 上的便捷方法
 *
 * @author Harry Zhang 
 * @since 2020/1/1 5:16 PM
 */
langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'McCarthy']

/**
 * 案例1：使用 any() 查找 map 中单个匹配：是否有语言名字包含非字母的
 */
print "Map中是否包含编程语言不全部为字母的？"
println langs.any() { language, author ->
    language =~ "[^A-Za-z]"
}

/**
 * 案例2：使用 every() 全部匹配
 */
print "Map中是否包含编程语言不全部为字母的？"
println langs.every { language, author ->
    language =~ "[^A-Za-z]"
}

/**
 * 案例3：Map 分组
 * friends 是一个包含 value 为姓名的 Map, 其中有很多同姓的哥们儿。需求：按照姓来划分哥们儿。
 *
 * 分析：
 * groupBy() 接受一个闭包作为参数，调用者提供一个闭包，闭包返回用于分组的字段值。
 * entry.value.split(" ")[0] 将姓名切割并获取第一个（firstName）作为分组字段
 * 分组之后的数据结构是：key = 分组之后的字段值，value = friends 格式的 map
 */
friends = [
        briang : 'Brian Goetz',
        brians : 'Brian Sletten',
        davidb : 'David Bock',
        davidg : 'David Geary',
        scottd : 'Scott Davis',
        scottl : 'Scott Leberknight',
        stuarth: 'Stuart Halloway'
]
groupByFirstName = friends.groupBy() { entry -> entry.value.split(" ")[0] }
groupByFirstName.each({ firstName, buddies ->
    println "$firstName : ${buddies.collect { key, value -> value }.join(", ")}"
})
println groupByFirstName
