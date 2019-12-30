/**
 *  可选形参，给方法的最后一个参数设置一个默认值，其就成为了可选形参。
 *  实参没有传值时使用默认值。
 *
 *  如果方法形参默认是一个数组，那么其也是可选参数。
 */

def static log(x, base = 10) {
    Math.log(x) / Math.log(base)
}

println log(1024)
println log(1024, 10)
println log(1024, 2)

// -------------------------------------------------
def task(name, String[] details) {
    println "$name - $details"
}

// task('Call', '123-456-7890') 方法调用可以不加括号
task 'Call', '123-456-7890'
task 'Call', '123-456-7890', '231-456-0987'
task 'Check Mail'
