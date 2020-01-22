/**
 * 读取 car.dat 文件，为文件中的每一行数据创建一个 Expando 实例。以第一行作为 Expando 对象的属性。
 *
 * @author Harry Zhang 
 * @since 2020/1/21 10:25 AM
 */
def data = new File("car.dat").readLines()

props = data[0].split(', ') // 拿到属性值
data -= data[0] // 去掉 list 中的第一行

cars = data.collect() {
    def car = new Expando()
    it.split(", ").eachWithIndex { String value, int i ->
        car[props[i]] = value // 添加属性
    }
    // 添加方法，计算每年的行驶公里数
    car.ampy = { miles.toLong() / (2008 - year.toLong()) }
    car
}

props.each { print "$it " }
println " Avg. MPY"
println "-------------------------"

cars.each { car ->
    for (String property : props) {
        print "${car[property]} "
    }
    println car.ampy()
}
