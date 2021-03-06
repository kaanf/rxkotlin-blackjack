import java.util.*

fun exampleOf(description: String, action: () -> Unit) {
    println("\n--- Example of: $description ---")
    action()
}

const val episodeI = "The Phantom Menace"
const val episodeII = "Attack of the Clones"
const val episodeIII = "The Clone Wars"
const val episodeIV = "Revenge of the Sith"
const val episodeV = "Solo: A Star Wars Story"
const val episodeVI = "A New Hope"
const val episodeVII = "The Empire Strikes Back"
const val episodeVIII = "Return of the Jedi"
const val episodeIX = "The Last Jedi"
const val solo = "Solo Movie"
const val rogue = "Rogue Movie"

fun <T> printWithLabel(label: String, element: T?) {
    println("$label $element")
}

sealed class Quote: Throwable() {
    class NeverSaidThat : Quote()
}

const val itsNotMyFault = "Itโs not my fault."
const val doOrDoNot = "Do. Or do not. There is no try."
const val lackOfFaith = "I find your lack of faith disturbing."
const val eyesCanDeceive = "Your eyes can deceive you. Donโt trust them."
const val stayOnTarget = "Stay on target."
const val iAmYourFather = "Luke, I am your father"
const val useTheForce = "Use the Force, Luke."
const val theForceIsStrong = "The Force is strong with this one."
const val mayTheForceBeWithYou = "May the Force be with you."
const val mayThe4thBeWithYou = "May the 4th be with you."

val cards = mutableListOf(
    Pair("๐ก", 11), Pair("๐ข", 2), Pair("๐ฃ", 3), Pair("๐ค", 4), Pair("๐ฅ", 5), Pair("๐ฆ", 6), Pair("๐ง", 7),
    Pair("๐จ", 8), Pair("๐ฉ", 9), Pair("๐ช", 10), Pair("๐ซ", 10), Pair("๐ญ", 10), Pair("๐ฎ", 10),
    Pair("๐ฑ", 11), Pair("๐ฒ", 2), Pair("๐ณ", 3), Pair("๐ด", 4), Pair("๐ต", 5), Pair("๐ถ", 6), Pair("๐ท", 7),
    Pair("๐ธ", 8), Pair("๐น", 9), Pair("๐บ", 10), Pair("๐ป", 10), Pair("๐ฝ", 10), Pair("๐พ", 10),
    Pair("๐", 11), Pair("๐", 2), Pair("๐", 3), Pair("๐", 4), Pair("๐", 5), Pair("๐", 6), Pair("๐", 7),
    Pair("๐", 8), Pair("๐", 9), Pair("๐", 10), Pair("๐", 10), Pair("๐", 10), Pair("๐", 10),
    Pair("๐", 11), Pair("๐", 2), Pair("๐", 3), Pair("๐", 4), Pair("๐", 5), Pair("๐", 6), Pair("๐", 7),
    Pair("๐", 8), Pair("๐", 9), Pair("๐", 10), Pair("๐", 10), Pair("๐", 10), Pair("๐", 10)
)

fun cardString(hand: List<Pair<String, Int>>): String {
    return hand.joinToString("") { it.first }
}

fun points(hand: List<Pair<String, Int>>) = hand.map { it.second }.fold(0) { s, a -> s + a }

fun IntRange.random() = Random().nextInt(endInclusive - start) +  start

sealed class HandError: Throwable() {
    class Busted: HandError()
}