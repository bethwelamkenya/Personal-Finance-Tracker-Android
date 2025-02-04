package org.example.personalfinancetracker.models

import org.example.personalfinancetracker.enums.TransactionType
import java.util.*

data class Transaction(
    var id: String? = "",
    val account: String = "",
    val date: Date = Date(),
    val type: TransactionType = TransactionType.Deposit,
    val description: String? = "",
    val amount: Double = 0.0,
    val currency: String? = "USD"
) {
//    constructor(
//        id: String?,
//        account: String,
//        date: LocalDate,
//        type: TransactionType,
//        description: String?,
//        amount: Double
//    ) : this(
//        id = id,
//        account = account,
//        date = date,
//        type = type,
//        description = description,
//        amount = amount
//    )

    constructor(
        account: String,
        date: Date,
        type: TransactionType,
        description: String?,
        amount: Double
    ) : this(
        id = null,
        account = account,
        date = date,
        type = type,
        description = description,
        amount = amount
    )

    constructor(
        account: String,
        date: Date,
        type: TransactionType,
        description: String?,
        amount: Double,
        currency: String?
    ) : this(
        id = null,
        account = account,
        date = date,
        type = type,
        description = description,
        amount = amount,
        currency = currency
    )

//    fun getLocalDate(): LocalDate {
//        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
//    }

}
