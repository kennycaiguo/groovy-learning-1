// 变长参数
// groovy 支持两种方式的变长参数

// 方式一：最后一个参数使用省略号标记
def receiveVarArgs(int a, int...b){
    println "You passed $a and $b"
}

// 方式二：最后一个参数使用数组类型
def receiveArray(int a, int[] b){
    println "You passed $a and $b"
}

receiveVarArgs 1, 2,3,4,5 // 第一个参数被看作形参a，后面的离散值被看作可变参数b
receiveArray 1, 2,3,4,5 // 第一个参数被看作形参a，后面的离散值被看作可变参数b

// 注意：可以用离散值和数组的方式传递参数给可变形参，当发送的是数组而不是离散值时请谨慎。
// 因为 Groovy 会包括在方括号中的值当作是 ArrayList 的实例，而不是纯数组。若要发送数组，需要定义一个指向该数组的引用。

// receiveVarArgs 1,[2,3,4,5,6]  // 报错 groovy.lang.MissingMethodException
receiveVarArgs 1,[2,3,4,5,6] as int[]