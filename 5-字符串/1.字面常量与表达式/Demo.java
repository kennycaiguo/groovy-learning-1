import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Harry Zhang
 * @since 2019/12/29 10:58 AM
 */
public class Demo {
    public static void main(String[] args) {
        String company = "Google";
        Float price = 653.21f;
        String quote = company + " 公司的股价是：" + price;
        System.out.println(quote);

        Map<String, Float> map = new HashMap<>();
        map.put("Microsoft", 123.12f);
        map.put("Apple", 999.12f);
        Set<Map.Entry<String, Float>> entries = map.entrySet();
        for (Map.Entry<String, Float> entry : entries) {
            company = entry.getKey();
            price = entry.getValue();
            System.out.println(quote);
        }

    }
}
