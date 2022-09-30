/*
1. einfaches Beispiel
Zero-Coupon Bond / Zero-Bond
"Ich bekomme 100€ an Weihnachten."

 */


typealias Amount = Double

enum class Currency { EUR, GBP, CHF, USD, YEN }

data class Date(val iso: String)

val christmas = Date("2022-12-24")

data class ZeroCouponBond(val amount: Amount, val currency: Currency, val date: Date)