/**
 * Groovy 中创建数组的方法和 Java 不同，需要注意。
 *
 * java 的创建语法：int[] arr = new int[]{1,2,3,4,5} 在 Groovy 中会报错。
 *
 * @author Harry Zhang 
 * @since 2019/12/18 10:02 PM
 */

// Groovy 创建一个数组
int[] arr = [1, 2, 3, 4, 5]
println(arr)
println("class is " + arr.getClass().name)

// 如果省略了左侧类型 int[]，则会创建一个 ArrayList
arrayList = [1, 2, 3, 4, 5]
println(arrayList)
println("class is " + arrayList.getClass().name)

// 如果使用的是动态类型，可以通过 as 操作符指定具体类型
def array = [1, 2, 3, 4, 5] as int[]
println(array)
println("class is " + array.getClass().name)
