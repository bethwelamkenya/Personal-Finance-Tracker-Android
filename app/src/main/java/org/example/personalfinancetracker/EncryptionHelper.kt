package org.example.personalfinancetracker

import java.io.File
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec
import android.util.Base64
import javax.crypto.SecretKey

class EncryptionHelper {
    // Encrypts a plain text using AES encryption
    fun encryptText(text: String, key: Key): String {
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encryptedBytes = cipher.doFinal(text.toByteArray())
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
    }

    // Decrypts an encrypted text using AES decryption
    fun decryptText(encryptedText: String, key: Key): String {
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.DECRYPT_MODE, key)
        val decodedBytes = Base64.decode(encryptedText, Base64.DEFAULT)
        val decryptedBytes = cipher.doFinal(decodedBytes)
        return String(decryptedBytes)
    }

    fun generateAESKey(): SecretKey {
        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(128) // 128-bit AES key
        return keyGen.generateKey()
    }

    fun getKeyFromString(keyString: String): SecretKey {
        val decodedKey = Base64.decode(keyString, Base64.DEFAULT)
        return SecretKeySpec(decodedKey, "AES")
    }

    fun saveKeyToFile(key: Key, filePath: String) {
        val encodedKey = Base64.encodeToString(key.encoded, Base64.DEFAULT)
        File(filePath).writeText(encodedKey)
    }

    fun loadKeyFromFile(filePath: String): Key? {
        val keyData = File(filePath).readText()
        return try {
            val decodedKey = Base64.decode(keyData, Base64.DEFAULT)
            SecretKeySpec(decodedKey, "AES")
        } catch (e: Exception) {
            println("Error loading key: ${e.message}")
            null
        }
    }
}
