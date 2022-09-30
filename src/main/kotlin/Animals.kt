// Tiere auf dem texanischen Highway

// Ein Tier ist eins der folgenden:
// - Gürteltier - ODER -
// - Papagei
sealed interface Animal {

}

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

data class Dillo(val liveness: Liveness, val weight: Weight): Animal {
    // Gürteltier überfahren
    fun runOver(): Dillo =
        Dillo(Liveness.DEAD, this.weight)
}

// Gürteltier überfahren
fun runOverDillo(dillo: Dillo): Dillo =
    Dillo(Liveness.DEAD, dillo.weight)

// lebendiges Gürteltier, 10kg
val dillo1 = Dillo(Liveness.ALIVE, 10)
// totes Gürteltier, 8kg
val dillo2 = Dillo(Liveness.DEAD, 8)

// unveränderliche Daten

// Ein Papagei hat folgende Eigenschaften:
// - Satz
// - Gewicht

data class Parrot(val sentence: String, val weight: Weight): Animal {
    // Papagei überfahren
    fun runOver(): Parrot = Parrot("", this.weight)
}

// Papagei überfahren
fun runOverParrot(parrot: Parrot): Parrot =
    Parrot("", parrot.weight)