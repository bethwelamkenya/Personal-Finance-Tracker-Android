package org.example.personalfinancetracker.trackers

import org.example.personalfinancetracker.FirebaseDBConnector
import org.example.personalfinancetracker.models.BankAccount
import org.example.personalfinancetracker.models.Transaction
import org.example.personalfinancetracker.enums.TransactionType
import java.io.File
import java.util.*

class TransactionTracker {
//    fun addTransaction(
//        account: String,
//        date: Date,
//        type: TransactionType,
//        description: String?,
//        amount: Double,
//        currency: String? = "USD",
//        dbConnector: FirebaseDBConnector
//    ) {
//        val transaction = Transaction(account, date, type, description, amount, currency)
//        dbConnector.addTransaction(transaction)
//    }
//
//    fun viewTransactionsForAccount(account: BankAccount, dbConnector: FirebaseDBConnector) {
//        val transactions: ArrayList<Transaction> =
//            dbConnector.getTransactionsForAccount(account.accountNumber) as ArrayList<Transaction>
//        if (transactions.isEmpty()) {
//            println("| No transactions found.                                     |")
//        } else {
//            println("|         ID           | Date       | Description               | Amount        |")
//            println("|----------------------|------------|---------------------------|---------------|")
//
//            transactions.forEach { transaction ->
//                val formattedId = transaction.id!!.take(20)  // Adjust ID format as needed
//                val formattedDate = transaction.date.toString().take(10)  // Adjust date format as needed
//                val formattedDesc = transaction.description!!.take(25).padEnd(25)
//                val formattedAmount = String.format(Locale.US, "%12.2f", transaction.amount)
//
//                println("| $formattedId | $formattedDate | $formattedDesc | $ $formattedAmount |")
//            }
//        }
//    }
//
//    fun exportTransactionsForAccount(
//        account: BankAccount,
//        dbConnector: FirebaseDBConnector
//    ): String {
//        val transactions = dbConnector.getTransactionsForAccount(account.accountNumber) as ArrayList<Transaction>
//        transactions.sortBy { it.date }
//        val fileName = "${account.accountNumber}_transactions.csv"
//
//        File(fileName).bufferedWriter().use { writer ->
//            writer.write("Date,Type,Description,Amount\n")
//            for (transaction in transactions) {
//                writer.write("${transaction.date},${transaction.type},${transaction.description},${transaction.amount}\n")
//            }
//        }
//        println("Transactions exported to $fileName")
//        return File(fileName).absolutePath
//    }

}
