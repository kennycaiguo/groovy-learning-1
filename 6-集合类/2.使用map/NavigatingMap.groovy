/**
 * 在 Map 上遍历
 *
 * @author Harry Zhang 
 * @since 2020/1/1 4:53 PM
 */
/**
 * 案例1：如果 each() 中的闭包只接受一个参数，那么它是 Entry，如果接受两个参数，那么他们是 key，value。
 */
langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'McCarthy']
println langs.getClass().name
langs.each() { entry ->
    println "Language $entry.key was authored by $entry.value"
}
println "------------"
langs.each() { key, value ->
    println "Language $key was authored by $value"
}

/**
 * 案例2：Map 中的 collect()，与 list 的区别是这里的闭包可以接受一个或两个参数。
 * 遍历每一个 entry，将 key 中包含 + 字符都替换成 P 后返回一个 list
 */
println langs.collect() { entry ->
    entry.key.replaceAll("[+]", "P")
}


/**
 * 案例3：Map 中的 find() 和 findAll()
 */
println "Looking for th first language with name greater than 3 characters"
entry = langs.find { key, value ->
    key.size() > 3
}
println "Found $entry.key written by $entry.value"
println "-------------"

println "Looking for th all language with name greater than 3 characters"
selected = langs.findAll { key, value ->
    key.size() > 3
}
selected.each { entry ->
    println "Found $entry.key written by $entry.value"
}
