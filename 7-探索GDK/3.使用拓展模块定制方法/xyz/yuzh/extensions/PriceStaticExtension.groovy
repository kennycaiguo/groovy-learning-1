package xyz.yuzh.extensions
/**
 * 添加一个对 String 类的静态拓展方法
 *
 * @author Harry Zhang
 * @since 2020/1/5 8:00 PM
 */
class PriceStaticExtension {

    public static double getPrice(String selfType, String ticker) {
        def url = "http://ichart.finance.yahoo.com/table.csv?s=$ticker".toURL()
        def data = url.readLines()[1].split(",")
        Double.parseDouble(data[-1])
    }

}
