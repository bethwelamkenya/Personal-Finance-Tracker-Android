package org.example.personalfinancetracker.models

import org.example.personalfinancetracker.FirebaseDBConnector
import org.example.personalfinancetracker.enums.TransactionType
import org.example.personalfinancetracker.trackers.TransactionTracker
import java.util.*

class BankAccount {
    var balance: Double = 0.0
    var accountNumber: String = ""
    var accountHolder: String = ""
    var bankName: String = ""
    var pin: String = ""
    var currency: String = "USD"

//    fun deposit(amount: Double, description: String?, tracker: TransactionTracker, dbConnector: FirebaseDBConnector) {
//        balance += amount
//        tracker.addTransaction(accountNumber, Date(), TransactionType.Deposit, description, amount, currency, dbConnector)
//    }
//
//    fun withdraw(amount: Double, description: String?, tracker: TransactionTracker, dbConnector: FirebaseDBConnector) {
//        if (balance >= amount) {
//            balance -= amount
//            tracker.addTransaction(accountNumber, Date(), TransactionType.Withdrawal, description, amount, currency, dbConnector)
//        } else {
//            println("Insufficient funds.")
//        }
//    }

    override fun toString(): String {
        return "$accountNumber,$accountHolder,$bankName,$pin,$balance"
    }

    // Convert BankAccount to a Firestore-friendly format
    fun toMap(): Map<String, Any> {
        return mapOf(
            "accountHolder" to accountHolder,
            "balance" to balance,
            "pin" to pin,
            "currency" to currency
        )
    }

    companion object {
        fun fromString(data: String): BankAccount {
            val parts = data.split(",")
            val account = BankAccount()
            account.accountNumber = parts[0]
            account.accountHolder = parts[1]
            account.bankName = parts[2]
            account.pin = parts[3]
            account.balance = parts[4].toDouble()
            return account
        }
    }
}
