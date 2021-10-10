import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject

fun main(args: Array<String>) {

    exampleOf("PublishSubject") {

        val subscriptions = CompositeDisposable()
        val dealtHand = PublishSubject.create<List<Pair<String, Int>>>()

        fun deal(cardCount: Int) {
            val deck = cards
            var cardsRemaining = 52
            val hand = mutableListOf<Pair<String, Int>>()

            (0 until cardCount).forEach { _ ->
                val randomIndex = (0 until cardsRemaining).random()
                hand.add(deck[randomIndex])
                deck.removeAt(randomIndex)
                cardsRemaining -= 1
            }

            if(points(hand) > 21) {
                dealtHand.onError(HandError.Busted())
            } else {
                dealtHand.onNext(hand)
            }
        }

        val subFirst = dealtHand.subscribeBy(
            onNext = { println("${cardString(it)} for ${points(it)} points.") },
            onError = { HandError.Busted() }
        )
        subscriptions.add(subFirst)

        deal(3)
    }
}