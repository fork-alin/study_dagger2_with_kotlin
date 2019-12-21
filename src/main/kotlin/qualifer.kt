
import dagger.Component
import dagger.Module
import dagger.Provides
import java.lang.annotation.Documented
import javax.inject.Inject
import javax.inject.Qualifier


@Qualifier
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class SelfType(val value: Int = 1)

@Module
class QualifierBallModule {
    @SelfType(1)
    @Provides
    fun provideBallR(): Ball {
        return Ball("QualifierRed")
    }

    @SelfType(2)
    @Provides
    fun provideBallB(): Ball {
        return Ball("QualifierBlue")
    }
}

@Component(modules = [QualifierBallModule::class])
interface QualifierBallComponent {
    fun inject(qualifierStore: QualifierStore)
}

class QualifierStore {
    @SelfType(1)
    @Inject
    lateinit var ballR: Ball

    @SelfType(2)
    @Inject
    lateinit var ballB: Ball

    init {
        DaggerQualifierBallComponent.create().inject(this)
    }

    fun show() {
        println(ballR.color)
        println(ballB.color)
    }
}

fun main(args: Array<String>) {
    val qualifierStore = QualifierStore()
    qualifierStore.show()
}