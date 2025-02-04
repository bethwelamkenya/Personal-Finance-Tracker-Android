package org.example.personalfinancetracker

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FirebaseHelper {
//    fun initialize() {
//        // Replace with the actual path to your downloaded service account key JSON file
//        val inputStream = object {}.javaClass.classLoader?.getResourceAsStream("my-banking-project-62e2a-firebase-adminsdk-fbsvc-1d4be59cd5.json")
////        val serviceAccount = FileInputStream("/google-services.json")
//        val options = FirebaseOptions.builder()
//            .setCredentials(GoogleCredentials.fromStream(inputStream))
////            .setDatabaseUrl("https://my-banking-project-62e2a.firebaseio.com") // For Realtime Database or Firestore URL
//            .build()
//        FirebaseApp.initializeApp(options)
//    }

    fun getFirestore(): FirebaseFirestore {
        val db = Firebase.firestore
        return db
    }
}