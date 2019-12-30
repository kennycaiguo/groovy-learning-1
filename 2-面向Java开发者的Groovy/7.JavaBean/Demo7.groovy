// 代替 Calendar.getInstance()
Calendar.instance

str = 'Hello'

// 代替 str.getClass().getName()
// 注意：不能用于 Map、Builder等类型
// 保险起见，请使用 str.getClass().name
str.class.name

// 慎用 class 属性，Map等类对该属性有特殊处理，一般使用 getClass()，而不是 class