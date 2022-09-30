/*
1. einfaches Beispiel
Zero-Coupon Bond / Zero-Bond
"Ich bekomme 100€ an Weihnachten."

2. zerlegen in "atomare Bestandteile" / Ideen / Aspekte

- Währung: "Ich bekomme 1€ jetzt."
- Vielfaches: "Ich bekomme 100€ jetzt."
- Später

3. Wiederholen mit anderen Beispielen

"Currency swap": Weihnachten bekomme ich 100€ und ich zahle 100GBP.
 */


typealias Amount = Double

enum class Currency { EUR, GBP, CHF, USD, YEN }

data class Date(val iso: String)

val christmas = Date("2022-12-24")


/* gescheitert:
data class ZeroCouponBond(val amount: Amount, val currency: Currency, val date: Date): Contract
data class Future(val foo: Int): Contract
data class Call(val foo: Int): Contract
 */

sealed interface Contract
data class One(val currency: Currency): Contract
data class Multiple(val amount: Amount, val contract: Contract): Contract
//                                                    ^^^^^^^^ Selbstbezug
data class Later(val date: Date, val contract: Contract): Contract

data class Reverse(val contract: Contract): Contract

data class And(val contract1: Contract, val contract2: Contract): Contract

object Zero : Contract

val c1 = One(Currency.EUR) // "Ich bekomme 1€ jetzt."
val c2 = Multiple(100.0, c1) // "Ich bekomme 100€ jetzt."

// Weihnachten bekomme ich 100€.
val zcb1 = Later(christmas, Multiple(100.0, One(Currency.EUR)))

fun zeroCouponBond(amount: Amount, currency: Currency, date: Date): Contract =
    Later(date, Multiple(amount, One(currency)))

// Weihnachten zahle ich 100€.
val c3 = Reverse(zcb1)

fun currencySwap(date: Date,
                 amount1: Amount, currency1: Currency,
                 amount2: Amount, currency2: Currency) =
    And(zeroCouponBond(amount1, currency1, date),
        Reverse(zeroCouponBond(amount2, currency2, date)))

// Semantik

enum class Direction { LONG, SHORT }

data class Payment(val direction: Direction,
                   val date: Date, val amount: Amount, val currency: Currency) {
    fun reverse(): Payment =
        this.copy(direction = if (direction == Direction.LONG) Direction.SHORT else Direction.LONG)
    fun scale(factor: Amount) =
        this.copy(amount = this.amount * factor)
}

// Zahlungen bis zum Zeitpunkt now + Residualvertrag
fun semantics(contract: Contract, now: Date): Pair<List<Payment>, Contract> =
    when (contract) {
        is Zero ->
            Pair(Empty, Zero)
        is One ->
            Pair(Cons(Payment(Direction.LONG, now, 1.0, contract.currency), Empty),
                Zero)
        is Multiple -> {
            val p = semantics(contract.contract, now)
            p
        }

        is Reverse -> TODO()
        is Later -> TODO()
        is And -> TODO()
    }