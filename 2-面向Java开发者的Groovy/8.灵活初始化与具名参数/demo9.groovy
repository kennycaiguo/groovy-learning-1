class Robot2{
    def type, height, width

    // 如果需要传 map，应该显式的的执行类型，以避免混乱
    def access(Map location, width, fragile){
        println "Received fragile? $fragile, width: $width, loc: $location"
    }

    // Map 不在形参的第一个位置会不会有问题？
    def access2(width, Map location,  fragile){
        println "Received fragile? $fragile, width: $width, loc: $location"
    }
}

robot = new Robot2(type: 'arm', width: 10, height: 40)
robot.access(x: 30, y: 20, z: 10, 50, true)

// 键值对依然可以随意放在某个位置
robot.access(50, true, x: 30, y: 20, z: 10)
robot.access(50, x: 30, y: 20, z: 10, true)

// 如果 Map 不是在形参的第一个位置，就算实参顺序保持一致也会报错，所以 Map 必须在第一个形参位置。
// robot.access2(50, x: 30, y: 20, z: 10, true)
