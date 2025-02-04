package org.example.personalfinancetracker

import androidx.lifecycle.ViewModel
import org.example.personalfinancetracker.trackers.TransactionTracker
import org.example.personalfinancetracker.trackers.BankingTracker
import org.example.personalfinancetracker.trackers.SavingsTracker
import java.security.Key

class AppDependencies : ViewModel() {
    val tracker = TransactionTracker()
    val bankingTracker = BankingTracker()
    val dbConnector = FirebaseDBConnector()
    val savingsTracker = SavingsTracker()
    val encryptionHelper = EncryptionHelper()
    lateinit var key: Key // Initialize this in MainApp

    fun readResourceFile(fileName: String): String {
        // This obtains the input stream for the file located in src/main/resources
        val inputStream = object {}.javaClass.classLoader?.getResourceAsStream(fileName)
        return inputStream?.bufferedReader()?.readText() ?: "File not found"
    }
}