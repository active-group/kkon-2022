// Ein Fluss ist eins der folgenden:
// - ein Bach - ODER -
// - ein Zusammenfluss
// Fallunterscheidung

sealed interface River {}

// Ein Bach hat folgende Eigenschaften:
// - Name
// - Ursprungsort
data class Creek(val name: String, val origin: String): River

val eschach = Creek("Eschach", "Heimliswald")
val prim = Creek("Prim", "Dreifaltigkeitsberg")
val schlichem = Creek("Schlichem", "Tieringen")

// Ein Zusammenfluss hat folgende Eigenschaften:
// - Name
// - Ort des Zusammenflusses
// - Hauptfluss
// - Nebenfluss
data class Confluence(val name: String, val location: String,
                      val mainStem: River, val tributary: River)
//                                  ^^^^^                 ^^^^^
