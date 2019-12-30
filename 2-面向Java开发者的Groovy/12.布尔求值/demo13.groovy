
// Groovy 会自动把表达式计算求布尔值

str = 'Hello'
// 自动检查引用是否为 null，null为false，非 null 为true
if(str) {println 'hello'}


// 如果是集合，引用非null，集合非空才为true
list1 = null
println list1 ? 'list1 true' : 'list1 false'
list2 = [1,2,3]
println list2 ? 'list2 true' : 'list2 false'
list3 = []
println list3 ? 'list3 true' : 'list3 false'