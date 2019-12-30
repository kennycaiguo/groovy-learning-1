// for each

// java 风格的循环，需要指定类型，下例中是 String，也可以设为 def
String[] greetins = ["a", "b", "c"]
for (String greet : greetins){
    println greet
}

// 不指定类型的循环，in 循环
for (greet in greetins){
    println greet
}