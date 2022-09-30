// Eine Liste ist eins der folgenden:
// - die leere Liste
// - eine Cons-Liste aus erstem Element und Rest-Liste
//                                               ^^^^^ Selbstbezug
sealed interface List<out A>

object Empty : List<Nothing>

// Eine Cons-Liste besteht aus:
// - erstes Element
// - Rest-Liste
data class Cons<A>(val first: A, val rest: List<A>): List<A>

// Liste mit 1 Element: 5
val list1 = Cons(5, Empty)

// Any / (Java: Object) - Supertyp aller (Referenz)typen
// Nothing: Subtyp aller Typen (keine Werte dieses Typs)
// List<out A>: Die Listenelemente können Typ A oder einen Subtyp von A
// Kovarianz