import dagger.Component
import javax.inject.Inject

class Tool @Inject constructor() {
    fun withFork(): String {
        return "eat with fork"
    }
}

class People @Inject constructor(val tool: Tool) {
    fun doWhat(): String {
        return tool.withFork()
    }
}

@Component
interface PComponent {
    fun into(home: Home)
}

class Home {
    @Inject lateinit var people: People

    fun doDinner() {
        DaggerPComponent.create().into(this)
        println(people.doWhat())
    }
}

fun main(args: Array<String>) {
    Home().doDinner()
}