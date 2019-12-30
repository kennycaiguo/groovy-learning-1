/**
 * Groovy 的动态特性（动态类型）
 * 能力式设计：即方法的参数不指定类型，只提供一个形参，类型默认为 Object
 *
 * 如下 takeHelp 方法所示，不同于 Java 中为 helper 指定一个接口。在 Groovy 中，任何能提供帮助的 helper 只需要实现方法都能够提供帮助。
 *
 * 这也称之为：鸭子类型（如果它走路像鸭子，叫起来也像鸭子，那它就是一个鸭子）
 *
 * @author Harry Zhang
 * @since 2019/12/19 8:13 AM
 */

/*
    在 Java 中，如果想要「人」提供帮助，我们会给 helper 指定一个 Human 的接口，但是当后面希望大象也能提供帮助时，
    我们又得把 helper 类型替换为「Helper」，但是在 Groovy 中不需要修改代码就能让所有有帮助的人提供帮助。

    不指定具体接口也叫做「隐式接口」
 */

static def takeHelp(helper) {
    helper.helpMovingThings();

    // 使用 respondTo 可以处理可选的方法，在本例中，只有大象喜欢「吃甘蔗」
    if (helper.metaClass.respondsTo(helper, 'eatSugarcane')) {
        helper.eatSugarcane()
    }
}

class Man {
    static void helpMovingThings() {
        println("Man's helping")
    }
}

class Woman {
    static void helpMovingThings() {
        println("Woman's helping")
    }
}

class Elephant {
    static void helpMovingThings() {
        println("Elephant's helping")
    }

    static void eatSugarcane() {
        println("I love sugarcane...")
    }
}

takeHelp(new Man())
takeHelp(new Woman())
takeHelp(new Elephant())
