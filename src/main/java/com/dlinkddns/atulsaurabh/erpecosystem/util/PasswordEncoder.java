package com.dlinkddns.atulsaurabh.erpecosystem.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder  {


    public static String encode(String password)
    {
        try {
            String secreteKey = "ILoveMyIndia";
            Mac sha256_HMAC=Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(secreteKey.getBytes(),"HmacSHA256");
            sha256_HMAC.init(keySpec);
            String hmac = Base64.encodeBase64String(sha256_HMAC.doFinal(password.getBytes()));
            return hmac;
        }
        catch (NoSuchAlgorithmException no)
        {
            return null;
        }
        catch (InvalidKeyException in)
        {
            return null;
        }

    }

}
