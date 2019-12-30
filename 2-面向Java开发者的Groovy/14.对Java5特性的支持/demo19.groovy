// 范型
// Groovy 也支持范型，不过不会在编译器检查。下例中，会将String强制转换为 int，如果失败会抛出异常。

import java.util.ArrayList;

public class Generics {
    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add("hello");

        System.out.println("List populated");
        for(int e : list){
            System.out.println(e);
        }
    }
}