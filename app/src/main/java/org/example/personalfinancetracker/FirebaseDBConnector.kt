package org.example.personalfinancetracker

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import org.example.personalfinancetracker.models.BankAccount
import org.example.personalfinancetracker.models.SavingsGoal
import org.example.personalfinancetracker.models.Transaction
import org.example.personalfinancetracker.utils.Result

class FirebaseDBConnector {
    private val firestore = Firebase.firestore

    suspend fun addBankAccount(account: BankAccount): Result<String> = try {
        val data = hashMapOf(
            "accountHolder" to account.accountHolder,
            "bankName" to account.bankName,
            "balance" to account.balance,
            "pin" to account.pin,
            "currency" to account.currency
        )

        val result = firestore.collection("bank_accounts")
            .add(data)
            .await()

        Result.Success(result.id)
    } catch (e: Exception) {
        Result.Failure(e)
    }

    suspend fun getBankAccount(accountNumber: String): Result<BankAccount> = try {
        val document = firestore.collection("bank_accounts")
            .document(accountNumber)
            .get()
            .await()

        if (document.exists()) {
            val account = document.toObject(BankAccount::class.java)!!
            account.accountNumber = document.id
            Result.Success(account)
        } else {
            Result.Failure(Exception("Account not found"))
        }
    } catch (e: Exception) {
        Result.Failure(e)
    }// Add this to your FirebaseDBConnector class

    suspend fun getAccountByEmail(email: String, pin: String): Result<BankAccount> = try {
        val querySnapshot = firestore.collection("bank_accounts")
            .whereEqualTo("email", email) // Ensure you have an 'email' field in your documents
            .limit(1) // Since email should be unique
            .get()
            .await()

        if (querySnapshot.isEmpty) {
            Result.Failure(Exception("No account found with this email"))
        } else {
            val document = querySnapshot.documents[0]
            val storedPin = document.getString("pin")
            if (storedPin == pin.trim()) {
                val account = document.toObject(BankAccount::class.java)!!.apply {
                    accountNumber = document.id // Set the document ID as account number
                }
                Result.Success(account)
            } else {
                Result.Failure(Exception("Incorrect PIN"))
            }
        }
    } catch (e: Exception) {
        Log.e("FirebaseDBConnector", "Error getting account by email", e)
        Result.Failure(e)
    }

    suspend fun updateBankAccount(account: BankAccount): Result<Unit> = try {
        val data = hashMapOf(
            "accountHolder" to account.accountHolder,
            "bankName" to account.bankName,
            "balance" to account.balance,
            "pin" to account.pin,
            "currency" to account.currency
        )

        firestore.collection("bank_accounts")
            .document(account.accountNumber)
            .set(data)
            .await()

        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Failure(e)
    }

    suspend fun addTransaction(transaction: Transaction): Result<String> = try {
        val data = hashMapOf(
            "account" to transaction.account,
            "date" to transaction.date,
            "type" to transaction.type,
            "description" to transaction.description,
            "amount" to transaction.amount,
            "currency" to transaction.currency
        )

        val result = firestore.collection("transactions")
            .add(data)
            .await()

        Result.Success(result.id)
    } catch (e: Exception) {
        Result.Failure(e)
    }

    suspend fun getTransactionsForAccount(accountNumber: String): Result<List<Transaction>> = try {
        val querySnapshot = firestore.collection("transactions")
            .whereEqualTo("account", accountNumber)
            .get()
            .await()

        val transactions = querySnapshot.documents.mapNotNull { document ->
            val transaction = document.toObject(Transaction::class.java)
            transaction?.id = document.id
            transaction
        }

        Result.Success(transactions)
    } catch (e: Exception) {
        Result.Failure(e)
    }

    suspend fun addSavingsGoal(savings: SavingsGoal): Result<String> = try {
        val data = hashMapOf(
            "accountNumber" to savings.accountNumber,
            "goalName" to savings.goalName,
            "targetAmount" to savings.targetAmount,
            "savedAmount" to savings.savedAmount
        )

        val result = firestore.collection("savings_accounts")
            .add(data)
            .await()

        Result.Success(result.id)
    } catch (e: Exception) {
        Result.Failure(e)
    }

    suspend fun getSavingsGoalsForAccount(accountNumber: String): Result<List<SavingsGoal>> = try {
        val querySnapshot = firestore.collection("savings_accounts")
            .whereEqualTo("accountNumber", accountNumber)
            .get()
            .await()

        val goals = querySnapshot.documents.mapNotNull { document ->
            val goal = document.toObject(SavingsGoal::class.java)
            goal?.id = document.id
            goal
        }

        Result.Success(goals)
    } catch (e: Exception) {
        Result.Failure(e)
    }

    suspend fun depositToAccount(accountNumber: String, amount: Double): Result<Unit> = try {
        firestore.runTransaction { transaction ->
            val docRef = firestore.collection("bank_accounts").document(accountNumber)
            val snapshot = transaction.get(docRef)
            val currentBalance = snapshot.getDouble("balance") ?: 0.0
            transaction.update(docRef, "balance", currentBalance + amount)
        }.await()

        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Failure(e)
    }

    suspend fun withdrawFromAccount(accountNumber: String, amount: Double): Result<Unit> = try {
        firestore.runTransaction { transaction ->
            val docRef = firestore.collection("bank_accounts").document(accountNumber)
            val snapshot = transaction.get(docRef)
            val currentBalance = snapshot.getDouble("balance") ?: 0.0

            if (currentBalance >= amount) {
                transaction.update(docRef, "balance", currentBalance - amount)
            } else {
                throw Exception("Insufficient funds")
            }
        }.await()

        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Failure(e)
    }

    suspend fun transferFundsToAccount(
        fromAccount: String,
        toAccount: String,
        amount: Double
    ): Result<Unit> = try {
        firestore.runTransaction { transaction ->
            val fromRef = firestore.collection("bank_accounts").document(fromAccount)
            val toRef = firestore.collection("bank_accounts").document(toAccount)

            val fromSnapshot = transaction.get(fromRef)
            val toSnapshot = transaction.get(toRef)

            val fromBalance = fromSnapshot.getDouble("balance") ?: 0.0
            val toBalance = toSnapshot.getDouble("balance") ?: 0.0

            if (fromBalance >= amount) {
                transaction.update(fromRef, "balance", fromBalance - amount)
                transaction.update(toRef, "balance", toBalance + amount)
            } else {
                throw Exception("Insufficient funds")
            }
        }.await()

        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Failure(e)
    }

    suspend fun deleteAccount(accountNumber: String): Result<Unit> = try {
        firestore.collection("bank_accounts")
            .document(accountNumber)
            .delete()
            .await()

        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Failure(e)
    }

    suspend fun updatePin(accountNumber: String, oldPin: String, newPin: String): Result<Unit> =
        try {
            firestore.runTransaction { transaction ->
                val docRef = firestore.collection("bank_accounts").document(accountNumber)
                val snapshot = transaction.get(docRef)
                val currentPin = snapshot.getString("pin") ?: ""

                if (currentPin == oldPin) {
                    transaction.update(docRef, "pin", newPin)
                } else {
                    throw Exception("Invalid old PIN")
                }
            }.await()

            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Failure(e)
        }
}
