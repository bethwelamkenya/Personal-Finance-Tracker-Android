package org.example.personalfinancetracker.models

data class SavingsGoal(
    var id: String? = "",
    val accountNumber: String = "",
    val goalName: String = "",
    val targetAmount: Double = 0.0,
    val savedAmount: Double = 0.0
) {
    constructor(
        accountNumber: String,
        goalName: String,
        targetAmount: Double
    ) : this(
        null,
        accountNumber = accountNumber,
        goalName = goalName,
        targetAmount = targetAmount,
        savedAmount = 0.0
    )


    // Convert BankAccount to a Firestore-friendly format
    fun toMap(): Map<String, Any> {
        return mapOf(
            "account_number" to accountNumber,
            "target_amount" to targetAmount,
            "saved_amount" to savedAmount,
            "goal_name" to goalName
        )
    }
}

