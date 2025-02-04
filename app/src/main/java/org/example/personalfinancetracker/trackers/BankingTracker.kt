package org.example.personalfinancetracker.trackers

import org.example.personalfinancetracker.FirebaseDBConnector
import org.example.personalfinancetracker.models.BankAccount

class BankingTracker {
//    private var activeAccount: BankAccount? = null
//    var lastActive: Long = System.currentTimeMillis()
//
//    fun updateLastActive() {
//        lastActive = System.currentTimeMillis()
//    }
//
//    fun createAccount(
//        accountHolder: String,
//        bankName: String,
//        pin: String,
//        dbConnector: FirebaseDBConnector
//    ): String {
//        val account = BankAccount()
//        account.accountHolder = (accountHolder)
//        account.bankName = (bankName)
//        account.pin = (pin)
//        val accountNumber = dbConnector.addBankAccount(account)!!
//        account.accountNumber = accountNumber
//        activeAccount = account
//        return accountNumber
//    }
//
//    fun logIn(
//        accountNumber: String,
//        pin: String,
//        dbConnector: FirebaseDBConnector
//    ): Boolean {
//        val account = dbConnector.getBankAccount(accountNumber)
//        if (account == null || pin != account.pin) {
//            return false
//        }
//        activeAccount = account
//        return true
//    }
//
//    fun logOut() {
//        activeAccount = null
//    }
//
//    fun viewAccount() {
//        if (activeAccount == null) {
//            println("No active account. Please log in first.")
//            return
//        }
//        println("\n=== Account Information ===")
//        println("Account Number: ${activeAccount?.accountNumber!!}")
//        println("Account Holder: ${activeAccount?.accountHolder}")
//        println("Bank Name: ${activeAccount?.bankName}")
//        println("Balance: ${activeAccount?.balance} ${activeAccount?.currency}")
//    }
//
//    fun getActiveAccount(): BankAccount? {
//        return activeAccount
//    }
//
//    fun deposit(
//        amount: Double,
//        description: String?,
//        tracker: TransactionTracker,
//        dbConnector: FirebaseDBConnector
//    ): Boolean {
//        if (activeAccount == null) {
//            println("No active account. Please log in first.")
//            return false
//        }
//        activeAccount?.deposit(amount, description, tracker, dbConnector)
//        dbConnector.depositToAccount(activeAccount!!.accountNumber, amount)
//        println("Deposit successful. New balance: $${activeAccount?.balance}")
//        return true
//    }
//
//    fun withdraw(
//        amount: Double,
//        description: String?,
//        tracker: TransactionTracker,
//        dbConnector: FirebaseDBConnector,
//    ): Boolean {
//        if (activeAccount == null) {
//            println("No active account. Please log in first.")
//            return false
//        }
//        if (activeAccount?.balance!! < amount) {
//            println("Insufficient funds.")
//            return false
//        }
//        activeAccount?.withdraw(amount, description, tracker, dbConnector)
//        dbConnector.withdrawFromAccount(activeAccount!!.accountNumber, amount)
//        println("Withdrawal successful. New balance: $${activeAccount?.balance}")
//        return true
//    }
//
//    fun transferFunds(receiver: BankAccount, amount: Double, dbConnector: FirebaseDBConnector, tracker: TransactionTracker, description: String = "Transfer") {
//        try {
//            activeAccount?.withdraw(amount, description, tracker, dbConnector)
//            receiver.deposit(amount, description, tracker, dbConnector)
//            dbConnector.transferFundsToAccount(activeAccount!!.accountNumber, receiver.accountNumber, amount)
//            println("Transfer successful. New balance: \$${activeAccount?.balance}")
//        } catch (e: Exception) {
//            println("Transfer failed. $e")
//        }
//    }
//
//    fun deleteAccount(tracker: TransactionTracker, dbConnector: FirebaseDBConnector) {
//        if (activeAccount == null) {
//            println("No active account. Please log in first.")
//            return
//        }
//        withdraw(activeAccount!!.balance, "Deleting Account", tracker, dbConnector)
//        if (dbConnector.deleteAccount(activeAccount!!.accountNumber)) {
//            println("Account deleted successfully.")
//            logOut()
//        } else {
//            println("Account deletion failed.")
//        }
//    }
}