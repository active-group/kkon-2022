// Ein Fluss ist eins der folgenden:
// - ein Bach - ODER -
// - ein Zusammenfluss
// Fallunterscheidung

sealed interface River {}

// Ein Bach hat folgende Eigenschaften:
// - Ursprungsort
data class Creek(val origin: String): River

val 