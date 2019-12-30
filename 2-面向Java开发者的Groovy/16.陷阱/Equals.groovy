/**
 * Groovy 中，== 操作符映射到 java 的 equals()，而引用地址的比较需要用 is() 方法
 *
 * 注意：当且仅当被比较的类没有实现 Comparable 接口时，== 操作符才会映射到 equals()
 * 若目标类实现了 Comparable 接口，则会映射到 compareTo() 方法。
 *
 * @author Harry Zhang 
 * @since 2019/12/18 9:17 PM
 */

str1 = 'hello'
str2 = str1
str3 = new String('hello')
str4 = 'Hello'

// true
println("str1 == str2: ${str1 == str2}")
// true
println("str1 == str3: ${str1 == str3}")
// false
println("str1 == str4: ${str1 == str4}")

// true
println("str1.is(str2): ${str1.is(str2)}")
// false
println("str1.is(str3): ${str1.is(str3)}")
// false
println("str1.is(str4): ${str1.is(str4)}")
