// Ein Fluss ist eins der folgenden:
// - ein Bach - ODER -
// - ein Zusammenfluss
// Fallunterscheidung

sealed interface River {
    fun flowsFrom(location: String): Boolean
}

// Ein Bach hat folgende Eigenschaften:
// - Name
// - Ursprungsort
data class Creek(val name: String, val origin: String): River {

}

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

val neckar1 = Confluence("Neckar", "Rottweil", prim, eschach)
val neckar2 = Confluence("Neckar", "Epfendorf", neckar1, schlichem)

// Fließt Wasser aus einem Ort in einen Fluss?
fun flowsFrom(location: String, river: River): Boolean =
    when (river) {
        is Creek -> location == river.origin
        is Confluence ->
            river.location == location
            // Selbstbezug in der Eingabe => rekursiver Aufruf
            // fließt Wasser aus Hauptfluss in location?
                    || flowsFrom(location, river.mainStem)
            // fließt Wasser aus Nebenfluss in location?
                    || flowsFrom(location, river.tributary)
    }