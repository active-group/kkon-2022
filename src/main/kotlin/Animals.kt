// Tiere auf dem texanischen Highway

// Datendefinition:
// Gürteltier hat folgende Eigenschaften:
// - tot oder lebendig - UND -
// - Gewicht

// "Lebendigkeit" ist eins der folgenden:
// - tot - ODER -
// - lebendig
// Fallunterscheidung
// hier speziell: Aufzählung

enum class Liveness { DEAD, ALIVE }

typealias Weight = Integer

data class Dillo(val liveness: Liveness, val weight: Weight)