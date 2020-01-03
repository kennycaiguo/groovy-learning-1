/**
 * 创建 map 和 list 一样，不需要使用 new 操作符，并且不需要指定类名
 *
 * @author Harry Zhang 
 * @since 2020/1/1 4:36 PM
 */

/**
 * 案例1：如你所见，定义 map 时，key 可以不用引号包含。
 */
langs = ['C++': 'Stroustrup', Java: 'Gosling', Lisp: 'McCarthy']
println langs

/**
 * 案例2：查看一下实际上创建的是什么
 *
 * 输出：java.util.LinkedHashMap
 */
println langs.getClass().name


/**
 * 案例3：Map 中不能直接调用 class 属性，为何？观察一下输出
 *
 * 在 Map 中可以将 key 作为属性调用，实际输出的 key 所对应的 value。
 * 如果在 map 上调用 class，会以 key 为 class 去找 value，找不到会打印 null，这是一个陷阱需要注意。
 */
println langs['Java']
println langs.Java
println langs.Lisp
println langs.class


/**
 * 案例4：Groovy 的 map 可以以 key 作为属性名调用，但是下面这种带有隐晦含义的 key 去调用时会有问题。
 *
 * langs.C++
 * 1. C++ 会与 Groovy 的操作符重载冲突，++ 会被识别为操作符（对应 next() 方法）
 * 2. 首先 Groovy 会以 key 为「C」去找 value，然后对 value 调用操作符方法。但是并没有找到 C 对应的 value，故抛出：
 *    java.lang.NullPointerException: Cannot invoke method next() on null object
 *
 * 对于这种不规矩的 key，可以这样写：langs.'C++'
 */
// println langs.C++
println langs.'C++'
