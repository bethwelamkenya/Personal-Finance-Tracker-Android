package org.example.personalfinancetracker

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import org.example.personalfinancetracker.models.BankAccount
import org.example.personalfinancetracker.models.SavingsGoal
import org.example.personalfinancetracker.models.Transaction

class FirebaseDBConnector1 {
    fun addBankAccount(account: BankAccount): String? {
        val db = FirebaseHelper.getFirestore()
        val data = mapOf(
//            "accountNumber" to account.accountNumber,
            "accountHolder" to account.accountHolder,
            "bankName" to account.bankName,
            "balance" to account.balance,
            "pin" to account.pin,
            "currency" to account.currency
        )
        // Use the account number as the document ID for easy retrieval.
        var result: String? = null
        db.collection("bank_accounts")// .document(account.accountNumber)
            .add(data)
            .addOnSuccessListener {
                result = it.id
            }
        return result
    }

    fun getBankAccount(accountNumber: String): BankAccount? {
        val db = FirebaseHelper.getFirestore()
        var account: BankAccount? = null
        db.collection("bank_accounts").document(accountNumber).get()
            .addOnSuccessListener {
                account = it.toObject(BankAccount::class.java)
                account!!.accountNumber = it.id
            }
        return account // Assign the document ID to accountNumber
    }

    fun updateBankAccount(account: BankAccount): Boolean {
        val db = FirebaseHelper.getFirestore()
        val data = mapOf(
            "accountHolder" to account.accountHolder,
            "bankName" to account.bankName,
            "balance" to account.balance,
            "pin" to account.pin,
            "currency" to account.currency
        )
        // Use the account number as the document ID for easy retrieval.
        var result: String? = null
        db.collection("bank_accounts").document(account.accountNumber)
            .set(data)
            .addOnSuccessListener {
                result = "true"
            }
        return result != null
    }

    fun addTransaction(transaction: Transaction): String? {
        val db = FirebaseHelper.getFirestore()
        val data = mapOf(
            "account" to transaction.account,
            "date" to transaction.date,
            "type" to transaction.type,
            "description" to transaction.description,
            "amount" to transaction.amount,
            "currency" to transaction.currency
        )
        var result: String? = null
        db.collection("transactions")
            .add(data)
            .addOnSuccessListener {
                result = it.id
            }
        return result
    }

    fun getTransactionsForAccount(accountNumber: String): List<Transaction> {
        val db = FirebaseHelper.getFirestore()
        val collection = db.collection("transactions")
            .whereEqualTo("account", accountNumber)
        val docs: ArrayList<Transaction> = ArrayList()
        collection.get().addOnSuccessListener {
            docs.addAll(it.documents.mapNotNull { transact ->
                val transaction = transact.toObject(Transaction::class.java)
                transaction!!.id = transact.id // Set the transaction id
                transaction
            })
        }
        return docs
    }

    fun addSavingsGoal(savings: SavingsGoal): String? {
        val db = FirebaseHelper.getFirestore()
        val data = mapOf(
            "accountNumber" to savings.accountNumber,
            "goalName" to savings.goalName,
            "targetAmount" to savings.targetAmount,
            "savedAmount" to savings.savedAmount
        )
        // Let Firestore auto-generate a document ID or use a custom one.
        var result: String? = null
        db.collection("savings_accounts")
            .add(data)
            .addOnSuccessListener {
                result = it.id
            }
        return result
    }

    fun getSavingsGoalsForAccount(accountNumber: String): List<SavingsGoal> {
        val db = FirebaseHelper.getFirestore()
        val collection = db.collection("savings_accounts")
            .whereEqualTo("accountNumber", accountNumber)
        val docs: ArrayList<SavingsGoal> = ArrayList()
        collection.get().addOnSuccessListener {
            docs.addAll(it.documents.mapNotNull { saving ->
                val savingsGoal = saving.toObject(SavingsGoal::class.java)
                savingsGoal!!.id = saving.id // Set the saving id
                savingsGoal
            })
        }
        return docs
    }

    fun depositToAccount(accountNumber: String, amount: Double): Boolean {
        val db = FirebaseHelper.getFirestore()
        val docRef = db.collection("bank_accounts").document(accountNumber)
        var doc = false
        docRef.get().addOnSuccessListener {
            val currentBalance = it.getDouble("balance") ?: 0.0
            val newBalance = currentBalance + amount
            docRef.update("balance", newBalance)
            doc = true
        }
        return doc
    }

    fun withdrawFromAccount(accountNumber: String, amount: Double): Boolean {
        val db = FirebaseHelper.getFirestore()
        val docRef = db.collection("bank_accounts").document(accountNumber)
        var doc = false
        docRef.get().addOnSuccessListener {
            val currentBalance = it.getDouble("balance") ?: 0.0
            if (currentBalance >= amount) {
                val newBalance = currentBalance - amount
                docRef.update("balance", newBalance)
                doc = true
            }

        }
        return doc
    }

    // function to withdraw from a savings account
    fun withdrawFromSavings(savingsId: String, amount: Double): Boolean {
        val db = FirebaseHelper.getFirestore()
        val docRef = db.collection("savings_accounts").document(savingsId)
        var doc = false
        docRef.get().addOnSuccessListener {
            val currentBalance = it.getDouble("savedAmount") ?: 0.0
            if (currentBalance >= amount) {
                val newBalance = currentBalance - amount
                docRef.update("savedAmount", newBalance)
                doc = true
            }
        }
        return doc
    }

    fun depositToSavings(savingsId: String, amount: Double): Boolean {
        val db = FirebaseHelper.getFirestore()
        val docRef = db.collection("savings_accounts").document(savingsId)
        var doc = false
        docRef.get().addOnSuccessListener {
            val currentBalance = it.getDouble("savedAmount") ?: 0.0
            val newBalance = currentBalance + amount
            docRef.update("savedAmount", newBalance)
            doc = true
        }
        return doc
    }

    fun transferFundsToAccount(fromAccount: String, toAccount: String, amount: Double): Boolean {
        val db = FirebaseHelper.getFirestore()
        var result = false
        val fromDocRef = db.collection("bank_accounts").document(fromAccount)
        val toDocRef = db.collection("bank_accounts").document(toAccount)
        var fromDoc1: DocumentSnapshot? = null
        fromDocRef.get().addOnSuccessListener {
            fromDoc1 = it
        }
        val fromDoc = fromDoc1
        var toDoc1: DocumentSnapshot? = null
        toDocRef.get().addOnSuccessListener {
            toDoc1 = it
        }
        val toDoc = toDoc1
        if (fromDoc != null && toDoc != null) {
            val fromCurrentBalance = fromDoc.getDouble("balance") ?: 0.0
            val toCurrentBalance = toDoc.getDouble("balance") ?: 0.0
            if (fromCurrentBalance >= amount) {
                val newFromBalance = fromCurrentBalance - amount
                val newToBalance = toCurrentBalance + amount
                fromDocRef.update("balance", newFromBalance).addOnSuccessListener {
                    toDocRef.update("balance", newToBalance).addOnSuccessListener {
                        result = true
                    }
                }
            }
        }
        return result
    }

    fun deleteAccount(accountNumber: String): Boolean {
        val db = FirebaseHelper.getFirestore()
        val docRef = db.collection("bank_accounts").document(accountNumber)
        var doc = false
        docRef.get().addOnSuccessListener {
            docRef.delete().addOnSuccessListener {
                doc = true
            }
        }
        return doc
    }

    fun updatePin(accountNumber: String, oldPin: String, newPin: String): Boolean {
        val db = FirebaseHelper.getFirestore()
        val docRef = db.collection("bank_accounts").document(accountNumber)
        var doc = false
        docRef.get().addOnSuccessListener {
            val currentPin = it.getString("pin")
            if (currentPin == oldPin) {
                docRef.update("pin", newPin).addOnSuccessListener {
                    doc = true
                }
            }
        }
        return doc
    }
}