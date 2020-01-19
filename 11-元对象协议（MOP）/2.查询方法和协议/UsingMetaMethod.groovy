/**
 * MetaObjectProtocol 接口定义了一系列的方法用来在运行时获得和调用方法，MetaClass 接口继承了它。
 *
 * ----------------------------------------
 * getMetaMethod()          获得指定元方法
 * getStaticMetaMethod()    获得指定静态元方法
 *
 * getMetaMethods()         获得所有元方法
 * getStaticMetaMethods()   获得所有静态元方法
 *
 * getMetaProperty()        获得指定元属性
 * getStaticMetaProperty()  获得指定静态元属性
 *
 * respondsTo()             检查方法
 * hasProperty()            检查属性
 * ......
 * ----------------------------------------
 *
 * @author Harry Zhang
 * @since 2020/1/16 5:25 PM
 */

/**
 * 案例1：使用 getMetaMethod() 获得方法
 */
str = 'Hello'
methodName = 'toUpperCase'

MetaMethod methodOfInterest = str.metaClass.getMetaMethod(methodName)
println methodOfInterest.invoke(str)

/**
 * 案例2：使用 respondsTo() 查询方法是否存在
 *
 * getMetaMethod() 和 respondsTo() 操作方法时，如果方法有参数，可以简单的把方法的参数传进去而不必指定参数的 Class 类型。
 *
 * respondsTo() 返回一个列表，对其结果使用 ? 操作符，如果结果存在会返回 true，否则 false。
 */
print "Does String respond to toUpperCase()? "
println String.metaClass.respondsTo(str, 'toUpperCase') ? 'yes' : 'no'

print "Does String respond to compareTo(String)? "
println String.metaClass.respondsTo(str, 'compareTo', "test") ? 'yes' : 'no'

print "Does String respond to toUpperCase(int)? "
println String.metaClass.respondsTo(str, 'toUpperCase', 5) ? 'yes' : 'no'
