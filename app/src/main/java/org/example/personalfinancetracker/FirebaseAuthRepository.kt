package org.example.personalfinancetracker

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import org.example.personalfinancetracker.models.BankAccount
import javax.inject.Inject
import org.example.personalfinancetracker.utils.Result

class FirebaseAuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> = try {
        auth.signInWithEmailAndPassword(email, password).await()
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Failure(e)
    }

    override suspend fun register(email: String, password: String): Result<Unit> = try {
        auth.createUserWithEmailAndPassword(email, password).await()
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Failure(e)
    }

    override suspend fun log_in(
        accountNumber: String,
        pin: String,
        dbConnector: FirebaseDBConnector
    ): Result<String> {
        val account = BankAccount()
        account.accountNumber = accountNumber
        account.pin = pin
        account.balance = 0.0
        account.currency = "USD"
        return dbConnector.addBankAccount(account)
    }

    override suspend fun createUserProfile(
        userId: String,
        userData: Map<String, Any>
    ): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserProfile(userId: String): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserProfile(
        userId: String,
        userData: Map<String, Any>
    ): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUserProfile(userId: String): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun deposit(accountNumber: String, amount: Double): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun withdraw(accountNumber: String, amount: Double): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun transfer(
        fromAccountNumber: String,
        toAccountNumber: String,
        amount: Double
    ): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun createSavingsAccount(
        accountHolder: String,
        bankName: String,
        pin: String,
        dbConnector: FirebaseDBConnector
    ): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun depositSavings(accountNumber: String, amount: Double): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun withdrawSavings(accountNumber: String, amount: Double): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun transferSavings(
        fromAccountNumber: String,
        toAccountNumber: String,
        amount: Double
    ): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun createInvestmentAccount(
        accountHolder: String,
        bankName: String,
        pin: String,
        dbConnector: FirebaseDBConnector
    ): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun depositInvestment(accountNumber: String, amount: Double): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun withdrawInvestment(accountNumber: String, amount: Double): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun viewSavingsAccount(): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun viewInvestmentAccount(): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun viewTransactions(): Result<String> {
        TODO("Not yet implemented")
    }

    override suspend fun viewTransactionsForAccount(
        account: BankAccount,
        dbConnector: FirebaseDBConnector
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun viewBalance(): Result<String> {
        TODO("Not yet implemented")
    }
}