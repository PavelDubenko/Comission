fun main () {
     println(calculateCommission("VK Pay", 5000, 1000))
}

fun calculateCommission(cardType: String, previousTransfers: Int, transferAmount: Int): Double {
    val mastercardMaestroLimit = 75_000
    val visaMirMinimum = 35
    val mastercardMaestroCommissionRate = 0.006
    val mastercardMaestroCommissionFixed = 20
    val visaMirCommissionRate = 0.0075


    var commission = 0.0

    when (cardType) {
        "Mastercard", "Maestro" -> {
            if (previousTransfers >= mastercardMaestroLimit) {
                commission = transferAmount * mastercardMaestroCommissionRate + mastercardMaestroCommissionFixed
            }
        }
        "Visa", "Мир" -> {
            commission = transferAmount * visaMirCommissionRate
            if (commission < visaMirMinimum) {
                commission = visaMirMinimum.toDouble()
            }
        }
        "VK Pay" -> {
            commission = 0.0
        }
    }

    return commission
}