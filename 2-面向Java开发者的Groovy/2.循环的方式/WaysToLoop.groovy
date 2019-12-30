/* 
    upto() 方法，Groovy 向 Integre 类中添加的方法。
    0 是 Integer 的实例，输出所选范围内的所有值。
    
    $it 是循环时的索引值，'$' 让 print() 方法打印 it 变量的值而不是 it 这两个字符。
*/
0.upto(2) { print "$it " }

println ''
println '------------ 分隔符 ------------'
/*
    upto() 方法可以设置范围的上限和下限，如 0.upto(2)，2.upto(4)。
    如果范围从 0 开始，可以使用 times()
*/
3.times {print "$it "} 


println ''
println '------------ 分隔符 ------------'
/*
    循环时跳过某些值，使用 step()
    如：循环从 0-10，每次加 2，跳过中间值
*/
0.step(10, 2) {print "$it "}

println ''
println '------------ 分隔符 ------------'
// 使用上面的方法重写 Greetings.groovy
3.times{print "ho "}
println 'Merry Groovy'
