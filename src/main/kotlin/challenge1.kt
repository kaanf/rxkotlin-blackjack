import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

fun main(args: Array<String>) {
    exampleOf("Never - Challenge I") {
        val observable = Observable.never<Any>()
        val subscriptions = CompositeDisposable()
        val sub = observable.doOnSubscribe {
            println("On subscribe $it")
            println(it.isDisposed)
        }.doOnDispose {
            println("On dispose")
        }.subscribeBy(
            onNext = { println(it) },
            onComplete = { println("Completed.") }
        )
        subscriptions.add(sub)
        subscriptions.clear()

    }
}