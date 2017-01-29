package util

import java.security.MessageDigest
import java.util
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import Constants._
import _root_.util.Properties._

import org.apache.commons.codec.binary.Base64


object Encryption {

  def encrypt(value: String) = encryption(KEY, value)

  private def encryption(key: String, values: String): String = {
    val cipher: Cipher = Cipher.getInstance(value("AES1"))
    cipher.init(Cipher.ENCRYPT_MODE, keyToSpec(key))
    Base64.encodeBase64String(cipher.doFinal(values.getBytes("UTF-8")))
  }

  def decrypt(encryptedValue: String): String = decryption(KEY, encryptedValue)

  private def decryption(key: String, encryptedValue: String): String = {
    val cipher: Cipher = Cipher.getInstance(value("AES2"))
    cipher.init(Cipher.DECRYPT_MODE, keyToSpec(key))
    new String(cipher.doFinal(Base64.decodeBase64(encryptedValue)))
  }

  def keyToSpec(key: String): SecretKeySpec = {
    var keyBytes: Array[Byte] = (SALT + key).getBytes("UTF-8")
    val sha: MessageDigest = MessageDigest.getInstance("SHA-1")
    keyBytes = sha.digest(keyBytes)
    keyBytes = util.Arrays.copyOf(keyBytes, 16)
    new SecretKeySpec(keyBytes, "AES")
  }
}