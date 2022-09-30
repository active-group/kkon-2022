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

typealias Weight = Int

data class Dillo(val liveness: Liveness, val weight: Weight)

// lebendiges Gürteltier, 10kg
val dillo1 = Dillo(Liveness.ALIVE, 10)