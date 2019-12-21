import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject

class Age {
    fun myAge(): String {
        return "22"
    }
}

abstract class Citer {
    abstract fun doWhat(): String
}

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

    init {
        DaggerCiterComponent.create().inject(this)
    }

    fun showTime() {
        println(student.doWhat())
        println(worker.doWhat())
    }
}

fun main(args: Array<String>) {
    val house = House()
    house.showTime()
}