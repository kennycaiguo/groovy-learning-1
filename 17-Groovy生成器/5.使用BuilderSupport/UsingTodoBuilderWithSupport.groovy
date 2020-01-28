
def todoBuilder = new TodoBuilderWithSupport()
todoBuilder.build {
    Prepare_Vacation(start: '02/15', end: '02/22') {
        Reserve_Flight(on: '01/01', status: 'done')
        Reserve_Hotel(on: '01/02')
        Reserve_Car(on: '01/02')
    }
    Buy_New_MAC {
        Install_QuickSilver
        Install_TextMate
        Install_Groovy {
            Run_All_test
        }
    }
}
