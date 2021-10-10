import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import java.io.File
import kotlin.text.Charsets.UTF_8

sealed class Droid: Throwable() {
    class OU812: Droid()
}

sealed class FileReadError: Throwable() {
    class FileNotFound: FileReadError()
}

fun main(args: Array<String>) {
    exampleOf("Creating observable.") {
        val mostPopular: Observable<String> = Observable.just(episodeV)
        mostPopular.subscribe {
            println(it)
        }
        val originalTrilogy = Observable.just(episodeIV, episodeV, episodeVI)
        val prequelTrilogy = Observable.just(listOf(episodeI, episodeII, episodeIII))
        val stories = listOf(solo, rogue).toObservable()
    }
    exampleOf("Subscribe to observable.") {
        val observable = Observable.just(episodeIV, episodeV, episodeVI)
        /* Subscribe method.
        observable.subscribe {element ->
            println(element)
        }  */
        /* SubscribeBy method */
        observable.subscribeBy(
            onNext = { println(it) },
            onComplete = { println("SubscribeBy method complete.") }
        )
    }
    exampleOf("Empty observable.") {
        val observable = Observable.empty<Unit>()
        observable.subscribeBy(
            onNext = { println(it) },
            onComplete = { println("Completed.") }
        )
    }
    exampleOf("Never omit observable.") {
        val observable = Observable.never<Unit>()
        observable.subscribeBy(
            onNext = { println(it) },
            onComplete = { println("Completed.") }
        )
    }
    exampleOf("Dispose.") {
        val mostPopular: Observable<String> = Observable.just(episodeIV, episodeV, episodeVI)
        val subscription = mostPopular.subscribe {
            println(it)
        }
        subscription.dispose()
    }
    exampleOf("CompositeDisposable.") {
        val subscriptions = CompositeDisposable()
        subscriptions.add(listOf(episodeI, episodeII, episodeIII)
            .toObservable()
            .subscribe {
                println(it)
            })
    }
    exampleOf("Create observables with create.") {
        val subscriptions = CompositeDisposable()
        val droids = Observable.create<String> { emitter ->
            emitter.onNext("R2-D2")
            emitter.onError(Droid.OU812())
            emitter.onNext("C-3P0")
            emitter.onNext("K-2S0")
        }
        val observer = droids.subscribeBy(
            onNext = { println(it) },
            onError = { println("Error, $it") },
            onComplete = { println("Completed.") }
        )
        subscriptions.add(observer)
    }
    exampleOf("Single.") {
        val subscriptions = CompositeDisposable()
        fun loadText(fileName: String): Single<String> {
            return Single.create create@{ emitter ->
                val file = File(fileName)
                if(!file.exists()) {
                    emitter.onError(FileReadError.FileNotFound())
                    return@create
                }
                val content = file.readText(UTF_8)
                emitter.onSuccess(content)
            }
        }
    }
}