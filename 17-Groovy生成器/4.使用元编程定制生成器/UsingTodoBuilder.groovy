/**
 * 使用自定义的生成器（TodoBuilder），希望生成一个待办事项清单。效果应该如下：
 *
 * To-Do:
 *   - Prepare Vacation [start: 02/15 end: 02/22]
 *     x Reserve Flight [on: 01/01]
 *     - Reserve Hotel [on: 01/02]
 *     - Reserve Car [on: 01/02]
 *   - Buy New MAC
 *     - Install QuickSilver
 *     - Install TextMate
 *     - Install Groovy
 *       - Run All test
 *
 * @author Harry Zhang
 * @since 2020/1/28 20:25
 */
def todo = new TodoBuilder()
todo.build {
    // 一级待办，包含两个属性的 Map。
    Prepare_Vacation(start: '02/15', end: '02/22') {
        // 二级待办，status 属性决定输出的是「X」还是「-」
        Reserve_Flight(on: '01/01', status: 'done')
        Reserve_Hotel(on: '01/02')
        Reserve_Car(on: '01/02')
    }
    // 一级待办
    Buy_New_MAC {
        Install_QuickSilver
        Install_TextMate
        Install_Groovy {
            // 三级待办
            Run_All_test
        }
    }
}