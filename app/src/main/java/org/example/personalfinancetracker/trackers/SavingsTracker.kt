package org.example.personalfinancetracker.trackers

import org.example.personalfinancetracker.FirebaseDBConnector
import org.example.personalfinancetracker.models.SavingsGoal
import java.util.Locale

class SavingsTracker {
//    fun createSavingsGoal(
//        bankingTracker: BankingTracker,
//        goalName: String,
//        targetAmount: Double,
//        dbConnector: FirebaseDBConnector
//    ) {
//        val savingsGoal = SavingsGoal(bankingTracker.getActiveAccount()!!.accountNumber, goalName, targetAmount)
//        val savingsId = dbConnector.addSavingsGoal(savingsGoal)
//        if (savingsId != null) {
//            println("Savings goal added successfully.")
//        } else {
//            println("Failed to add savings goal.")
//        }
//    }
//
//    fun viewSavingsGoals(bankingTracker: BankingTracker, dbConnector: FirebaseDBConnector) {
//        val accountNumber = bankingTracker.getActiveAccount()!!.accountNumber
//        val savingsGoals = dbConnector.getSavingsGoalsForAccount(accountNumber)
//
//        if (savingsGoals.isEmpty()) {
//            println("| No savings goals found. Add one to get started!     |")
//            println("|-----------------------------------------------------|")
//            return
//        }
//
//        println("\n=== Your Savings Goals ===")
//        println("|          ID          | Goal                 | Target     | Saved     |")
//        println("|----------------------|----------------------|------------|-----------|")
//
//        for (goal in savingsGoals) {
//            val formattedRow = String.format(
//                Locale.US,
//                "| %-22s| %-20s| %10.2f | %9.2f |",
//                goal.id,
//                goal.goalName.take(20),  // Truncate long names
//                goal.targetAmount,
//                goal.savedAmount
//            )
//            println(formattedRow)
//        }
//    }
//
//    fun depositIntoSavings(
//        bankingTracker: BankingTracker,
//        goalId: String,
//        amount: Double,
//        tracker: TransactionTracker,
//        dbConnector: FirebaseDBConnector,
//        description: String = "Savings Deposit"
//    ) {
//        val account = bankingTracker.getActiveAccount()!!
//        if (account.balance >= amount) {
//            if (dbConnector.depositToSavings(goalId, amount)) {
//                bankingTracker.withdraw(
//                    amount,
//                    description,
//                    tracker,
//                    dbConnector
//                )
//                println("Deposit successful!")
//            } else {
//                println("Deposit failed. Check goal ID or amount.")
//            }
//        } else {
//            println("Deposit failed. Check amount.")
//        }
//    }
//
//    fun withdrawFromSavings(
//        bankingTracker: BankingTracker,
//        goalId: String,
//        amount: Double,
//        tracker: TransactionTracker,
//        dbConnector: FirebaseDBConnector,
//        description: String = "Savings Withdrawal"
//    ) {
////        val account = bankingTracker.getActiveAccount()!!
//        if (dbConnector.withdrawFromSavings(goalId, amount)) {
//            bankingTracker.deposit(
//                amount,
//                description,
//                tracker,
//                dbConnector
//            )
//            println("Withdrawal successful!")
//        } else {
//            println("Withdrawal failed. Check goal ID or amount.")
//        }
//    }
}