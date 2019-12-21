import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named


class Ball(val color: String) {}

@Module
class BallModule {
    @Named("Type_red")
    @Provides
    fun provideBallR(): Ball {
        return Ball("Red")
    }

    @Named("Type_blue")
    @Provides
    fun provideBallB(): Ball {
        return Ball("Blue")
    }
}

@Component(modules = [BallModule::class])
interface BallComponent {
    fun inject(store: Store)
}

class Store {
    @Named("Type_red")
    @Inject
    lateinit var ballR: Ball

    @Named("Type_blue")
    @Inject
    lateinit var ballB: Ball

    init {
        DaggerBallComponent.create().inject(this)
    }

    fun show() {
        println(ballR.color)
        println(ballB.color)
    }
}

fun main(args: Array<String>) {
    val store = Store()
    store.show()
}