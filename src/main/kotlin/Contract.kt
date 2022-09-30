/*
1. einfaches Beispiel
Zero-Coupon Bond / Zero-Bond
"Ich bekomme 100€ an Weihnachten."

2. zerlegen in "atomare Bestandteile" / Ideen / Aspekte

- Währung: "Ich bekomme 1€ jetzt."
- Vielfaches: "Ich bekomme 100€ jetzt."
- Später
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

val c1 = One(Currency.EUR) // "Ich bekomme 1€ jetzt."
val c2 = Multiple(100.0, c1) // "Ich bekomme 100€ jetzt."