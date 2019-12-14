import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

abstract class Citer {
    abstract fun doWhat(): String
}

/*
class Student(val age: Age) : Citer() {
    override fun doWhat(): String {
        return age.myAge()
    }
}
*/
class Student(val age: Age) {
    fun doWhat(): String {
        return age.myAge()
    }
}

class Worker : Citer() {
    override fun doWhat(): String {
        return "work"
    }
}

class Age {
    fun myAge(): String {
        return "22"
    }
}
/*
class Age @Inject constructor() {
    fun myAge(): String {
        return "22"
    }
}
*/

@Module
class CiterModule {
    @Provides
    fun provideAge(): Age {
        return Age()
    }
    @Provides
    fun providedStudent(age: Age): Student {
        return Student(age)
    }

    @Provides
    fun providedWorker(): Worker {
        return Worker()
    }
}

@Component(modules = [CiterModule::class])
interface CiterComponent {
    fun inject(house: House)
}

class House {
    @Inject
    lateinit var student: Student

    @Inject
    lateinit var worker: Worker

    fun showTime() {
        DaggerCiterComponent.create().inject(this)
        println(student.doWhat())
        println(worker.doWhat())
    }
}

fun main(args: Array<String>) {
    val house = House()
    house.showTime()
}