package com.bstu.fit.german.yarmolik.decrypt;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt {
    protected String decrypt (String encryptedText,byte[] key) {
        byte[] clearText = null;
        try {
            byte[] keyData = key;
            SecretKey ks = new SecretKeySpec(keyData, "AES");
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, ks);
            clearText = c.doFinal(Base64.decode(encryptedText, Base64.DEFAULT));
            return new String(clearText, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }
}
