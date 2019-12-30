/**
 * @author Harry Zhang 
 * @since 2019/12/19 9:09 PM
 */
static def usingCollection() {
    ArrayList<String> list = new ArrayList<>()
    Collection<String> collection = list

    list.add("one")
    list.add("two")
    list.add("three")
    list.remove(0)
    /*
        Groovy 会把 ArrayList 的 remove(int index) 映射到 Collection 上，故调用的还是 remove(int index)。
        或者说，虽然 ArrayList 的引用赋值给了 Collection，但是 collection 调用的还是 ArrayList 的 remove 方法而不是自身的那个 remove(Object o)。

        在 Groovy 中：0 不会强转为 Integer 类型去调用 Collection 自身的方法，而是继续以 int 基本类型调用 ArrayList 的 remove。
     */
    collection.remove(0)

    println("Added three items, remove two, so 1 item to remain.")
    // 1
    println("Number of elements is: " + list.size())
    // 1
    println("Number of elements is: " + collection.size())
}

usingCollection()
