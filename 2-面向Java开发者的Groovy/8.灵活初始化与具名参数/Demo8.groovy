/**
 * Groovy 中构造器可以动态生成，不需要自定义具体参数的构造器。
 * Groovy 中方法的参数是不需要指定类型的，并且形参的类型是可以根据实参传递的值的类型来决定的。
 */

class Robot{
    def type, height, width

    // 三个形参。如果实参传值有键值对，第一个参数将会是 Map，否则是非 Map 类型。
    def static access(location, width, fragile){
        println "Received fragile? $fragile, width: $width, loc: $location"
    }
}

// 以键值对的形式传入到构造器，并且 Groovy 编译器为其灵活的创建了一个构造器。
robot = new Robot(type: 'arm', width: 10, height: 40)
// arm, 40, 10
println "$robot.type, $robot.height, $robot.width"

/*
Received fragile? true, width: 50, loc: [x:30, y:20, z:10]
说明：如果方法的第一个参数是 Map，实参传值的时候可以以「键值对」的方式写入，
     如：x: 30, y: 20, z: 10
     所以 location 是一个 map。50, true 依次是 width、gragile
*/
robot.access(x: 30, y: 20, z: 10, 50, true)

/*
Received fragile? true, width: 50, loc: [x:30, y:20, z:10]
注意：如果实参的个数多于形参的个数，并且多出来的实参是键值对。Groovy 会假设第一个形参是 Map。
*/
robot.access(50, true, x: 30, y: 20, z: 10)

/*
Received fragile? true, width: 50, loc: 666
实参没有传键值对，第一个形参变成了普通类型。
*/
robot.access(666, 50, true)

// 报错，传递了四个实参，形参只有三个参数
// robot.access(x: 30, y: 20, z: 10, 50, true, 111)

// 报错，实参的个数多于形参的个数，多出来了两个参数，一个是键值对，一个其他类型。但是一个传了四个参数，而形参中只有三个参数。
// robot.access(50, true, x: 30, y: 20, z: 10, 11)
