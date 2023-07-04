const val ERROR_LIMIT = -1.0
fun main() {
    println(calculateCommission("VK Pay", 0, 5000))
}

fun calculateCommission(cardType: String, previousTransfers: Int, transferAmount: Int): Double {
    val mastercardMaestroLimit = 75_000
    val visaMirMinimum = 35
    val mastercardMaestroCommissionRate = 0.006
    val mastercardMaestroCommissionFixed = 20
    val visaMirCommissionRate = 0.0075
    val cardDayLimit = 150_000
    val cardMonthLimit = 600_000


    var commission = 0.0

    when (cardType) {
        "Mastercard", "Maestro" -> {
            commission =
                if (transferAmount + previousTransfers <= cardMonthLimit && transferAmount <= cardDayLimit && previousTransfers >= mastercardMaestroLimit)
                    transferAmount * mastercardMaestroCommissionRate + mastercardMaestroCommissionFixed
                else ERROR_LIMIT


        }

        "Visa", "Мир" -> {
            commission = if (transferAmount + previousTransfers <= cardMonthLimit && transferAmount <= cardDayLimit)
                transferAmount * visaMirCommissionRate
            else ERROR_LIMIT
            if (commission < visaMirMinimum) {
                commission = visaMirMinimum.toDouble()
            }
        }

        "VK Pay" -> {
            commission = if (transferAmount <= 15_000 && (transferAmount + previousTransfers) <= 40_000)
                0.0
            else ERROR_LIMIT
        }
    }

    return commission
}