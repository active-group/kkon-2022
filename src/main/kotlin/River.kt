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
                      val mainStem: River, val tributary: River): River
//                                  ^^^^^                 ^^^^^
//                                  Selbstbezug

// Fließt Wasser aus einem Ort in einen Fluss?
fun flowsFrom(location: String, river: River): Boolean =
    when (river) {
        is Creek -> location == river.origin
        is Confluence ->
            // Selbstbezug in der Eingabe => rekursiver Aufruf
            flowsFrom(location, river.mainStem)
            flowsFrom(location, river.tributary)
    }