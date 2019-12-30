import java.util.ArrayList;
import java.util.Collection;

/**
 * 案例：java 中装箱带来的陷阱
 *
 * @author Harry Zhang
 * @since 2019/12/19 8:57 PM
 */
public class UsingCollection {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collection<String> collection = list;

        list.add("one");
        list.add("two");
        list.add("three");
        // 执行之后 list 中有：[two, three]
        list.remove(0);
        /*
            执行之后 list 应该中有：[three] ？错误！
            Collection 中的 remove(Object o); 方法传递的是对象，不是索引。在 Java 中：0 会被强转为 Integer，而 Integer 不是 list 的实例。
            故删除失败，list 中的元素仍然是：[two, three]
         */
        collection.remove(0);

        System.out.println("Added three items, remove two, so 1 item to remain.");
        // 2
        System.out.println("Number of elements is: " + list.size());
        // 2
        System.out.println("Number of elements is: " + collection.size());
    }

}
