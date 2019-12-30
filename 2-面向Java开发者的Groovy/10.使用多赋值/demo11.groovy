
/*
    如果想要一个方法返回多个值，只需要将多个结果放在一个数组中。
    如果想要把返回的多个值一次性赋值给多个变量，如下：
*/

// 该方法返回的是数组
def static splitName(fullName) {fullName.split(' ')}

// 将值一次性赋值给多个变量
def (firstName, lastName) = splitName("Harry Zhang")
println "$lastName, $firstName $lastName" 

// 接受的变量个数少于返回值的个数怎么办？多余的返回值不会被赋值而已。
def (firstName1, lastName1) = splitName("Harry Zhang HAHAHA")
println "$lastName1, $firstName1 $lastName1" 

// 接受的变量个数多于返回值的个数怎么办？多余的接收变量会被设置为 null。
def (var1, var2, var3) = ["Harry","Zhang"]
println "var1 = $var1, var2 = $var2, var3 = $var3" 


/*
    利用值的多赋值特性，我们可以实现不借助第三方变量交换两个变量的值
*/
def name1 = "Harry"
def name2 = "Jessica"
println "$name1 and $name2"

(name1, name2) = [name2, name1]
println "$name1 and $name2"
