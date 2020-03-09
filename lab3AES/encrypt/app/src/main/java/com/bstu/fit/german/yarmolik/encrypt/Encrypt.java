package com.bstu.fit.german.yarmolik.encrypt;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {
    protected String encrypt(String clearText,byte[] key) {
        byte[] encryptedText = null;
        try {
            byte[] keyData = key;
            SecretKey ks = new SecretKeySpec(keyData, "AES");
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, ks);
            encryptedText = c.doFinal(clearText.getBytes("UTF-8"));
            return Base64.encodeToString(encryptedText, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        }
    }
}
