package org.example.personalfinancetracker

import org.example.personalfinancetracker.models.BankAccount
import org.example.personalfinancetracker.utils.Result

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<BankAccount>
    suspend fun register(email: String, password: String): Result<Unit>
    suspend fun log_in(accountNumber: String, pin: String, dbConnector: FirebaseDBConnector): Result<String>
    suspend fun createUserProfile(userId: String, userData: Map<String, Any>): Result<String>
    suspend fun getUserProfile(userId: String): Result<String>
    suspend fun updateUserProfile(userId: String, userData: Map<String, Any>): Result<String>
    suspend fun deleteUserProfile(userId: String): Result<String>
    suspend fun deposit(accountNumber: String, amount: Double): Result<String>
    suspend fun withdraw(accountNumber: String, amount: Double): Result<String>
    suspend fun transfer(
        fromAccountNumber: String,
        toAccountNumber: String,
        amount: Double
    ): Result<String>
    suspend fun createSavingsAccount(
        accountHolder: String,
        bankName: String,
        pin: String,
        dbConnector: FirebaseDBConnector
    ): Result<String>
    suspend fun depositSavings(accountNumber: String, amount: Double): Result<String>
    suspend fun withdrawSavings(accountNumber: String, amount: Double): Result<String>
    suspend fun transferSavings(
        fromAccountNumber: String,
        toAccountNumber: String,
        amount: Double
    ): Result<String>
    suspend fun createInvestmentAccount(
        accountHolder: String,
        bankName: String,
        pin: String,
        dbConnector: FirebaseDBConnector
    ): Result<String>
    suspend fun depositInvestment(accountNumber: String, amount: Double): Result<String>
    suspend fun withdrawInvestment(accountNumber: String, amount: Double): Result<String>
    suspend fun viewSavingsAccount(): Result<String>
    suspend fun viewInvestmentAccount(): Result<String>
    suspend fun viewTransactions(): Result<String>
    suspend fun viewTransactionsForAccount(account: BankAccount, dbConnector: FirebaseDBConnector)
    suspend fun viewBalance(): Result<String>
}