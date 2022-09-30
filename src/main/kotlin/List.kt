// Eine Liste ist eins der folgenden:
// - die leere Liste
// - eine Cons-Liste aus erstem Element und Rest-Liste
//                                               ^^^^^ Selbstbezug
sealed interface List<out A> {
// leider nicht:
//    fun sum(): A
}

object Empty : List<Nothing> {
//    override fun sum(): A = 0
}

// Eine Cons-Liste besteht aus:
// - erstes Element
// - Rest-Liste
data class Cons<A>(val first: A, val rest: List<A>): List<A> {
//    override fun sum(): Int = this.first + this.rest.sum()
}

// Liste mit 1 Element: 5
val list1 = Cons(5, Empty)
// Liste mit 2 Elementen: 5 8
val list2 = Cons(5, Cons(8, Empty))
// Liste mit 3 Elementen: 5 8 6
val list3 = Cons(5, Cons(8, Cons(6, Empty)))
// Liste mit 4 Elementen: 3 5 8 6
val list4 = Cons(3, list3)

// Any / (Java: Object) - Supertyp aller (Referenz)typen
// Nothing: Subtyp aller Typen (keine Werte dieses Typs)
// List<out A>: Die Listenelemente können Typ A oder einen Subtyp von A
// Kovarianz

// Elemente einer Liste addieren
fun listSum(list: List<Int>): Int =
    when (list) {
        is Empty -> 0
        is Cons ->
            list.first + listSum(list.rest)
    }

// extension method
fun List<Int>.sum(): Int = listSum(this)

// Elemente einer Liste multiplizieren
fun listProduct(list: List<Int>): Int =
    when (list) {
        is Empty -> 1
        is Cons ->
            list.first * listProduct(list.rest)
    }

// Abstraktion

// Zwei Listen aneinanderhängen

val highway = Cons(dillo1, Cons(dillo2, Cons(parrot1, Cons(parrot2, Empty))))

fun runOverAnimals(list: List<Animal>): List<Animal> =
    when (list) {
        is Empty -> Empty
        is Cons ->
            Cons(runOverAnimal(list.first), runOverAnimals(list.rest))
    }

// Alle Elemente einer Liste mit 2 multiplizieren
fun doubleList(list: List<Int>): List<Int> =
    when (list) {
        is Empty -> Empty
        is Cons ->
            Cons(times2(list.first), doubleList(list.rest))
    }

fun times2(n: Int): Int = 2 * n

fun <A> listMap(f: (A) -> A, list: List<A>): List<A> =
    when (list) {
        is Empty -> Empty
        is Cons ->
            Cons(f(list.first), listMap(f, list.rest))
    }
