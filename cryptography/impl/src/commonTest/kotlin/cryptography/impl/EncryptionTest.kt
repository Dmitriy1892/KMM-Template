package cryptography.impl

import diglol.crypto.AesGcm
import diglol.crypto.Hmac
import diglol.crypto.Pbkdf2
import diglol.crypto.random.nextBytes
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EncryptionTest {

    @Test
    fun `test AES-GCM-NoPadding encryption-decryption`() = runTest {
        val decryptedText = "Sample decrypted text"
        val password = "pass123456"
        val salt = nextBytes(8)
        val key = Pbkdf2(Hmac.Type.SHA256, 20000, 16).deriveKey(password.encodeToByteArray(), salt)

        val aesGcm = AesGcm(key)
        val encrypted = aesGcm.encrypt(decryptedText.encodeToByteArray())

        println("ENCRYPTED STRING: ${encrypted.decodeToString()}")

        val key2 = Pbkdf2(Hmac.Type.SHA256, 20000, 16).deriveKey(password.encodeToByteArray(), salt)

        println("KEY - 1: ${key.decodeToString()}")
        println("KEY - 2: ${key2.decodeToString()}")

        val aesGcm2 = AesGcm(key2)

        val decrypted = aesGcm2.decrypt(encrypted).decodeToString()

        println("INITIAL: $decryptedText")
        println("OUTPUT: $decrypted")

        assertEquals(decryptedText, decrypted)
    }
}