import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

fun main(args: Array<String>) {
    exampleOf("PublishSubject.") {
        val quotes = PublishSubject.create<String>()
        val disposable = CompositeDisposable()
        quotes.onNext(itsNotMyFault)
        val subFirst = quotes.subscribeBy(
            onNext = { printWithLabel("1) ", it) },
            onError = { printWithLabel("1)", it) },
            onComplete = { printWithLabel("1) ", "Completed.") }
        )
        disposable.add(subFirst)
        quotes.onNext(doOrDoNot)
        val subSecond = quotes.subscribeBy(
            onNext = { printWithLabel("2) ", it) },
            onError = { printWithLabel("2)", it) },
            onComplete = { printWithLabel("2) ", "Completed.") }
        )
        disposable.add(subSecond)
        quotes.onNext(lackOfFaith)
        val subThird = quotes.subscribeBy(
            onNext = { printWithLabel("3) ", it) },
            onError = { printWithLabel("3)", it) },
            onComplete = { printWithLabel("3) ", "Completed.") }
        )
        disposable.add(subThird)
        quotes.onNext(eyesCanDeceive)
        quotes.onError(Quote.NeverSaidThat())
        disposable.dispose()
    }
    exampleOf("BehaviorSubject.") {
        val disposable = CompositeDisposable()
        val quotes = BehaviorSubject.createDefault(iAmYourFather)
        val subFirst = quotes.subscribeBy(
            onNext = { printWithLabel("1) ", it) },
            onError = { printWithLabel("1) ", it) },
            onComplete = { printWithLabel("1) ", "Completed.") }
        )
        disposable.add(subFirst)
        quotes.onNext(eyesCanDeceive)
        quotes.onNext(lackOfFaith)
        quotes.onError(Quote.NeverSaidThat())
        val subSecond = quotes.subscribeBy(
            onNext = { printWithLabel("2) ", it) },
            onError = { printWithLabel("2) ", it) },
            onComplete = { printWithLabel("2) ", "Completed.") }
        )
        disposable.add(subSecond)
        disposable.clear()
    }
    exampleOf("ReplaySubject.") {
        val disposable = CompositeDisposable()
        val subject = ReplaySubject.createWithSize<String>(2)
        subject.onNext(mayTheForceBeWithYou)
        subject.onNext(useTheForce)
        disposable.add(subject.subscribeBy(
            onNext = { printWithLabel("1) ", it) },
            onError = { printWithLabel("1) ", it) },
            onComplete = { printWithLabel("1) ", "Completed.") }
        ))
        disposable.add(subject.subscribeBy(
            onNext = { printWithLabel("2) ", it) },
            onError = { printWithLabel("2) ", it) },
            onComplete = { printWithLabel("2) ", "Completed.") }
        ))
        subject.onNext(eyesCanDeceive)
        subject.onNext(doOrDoNot)
        subject.onNext(lackOfFaith)
    }
}
