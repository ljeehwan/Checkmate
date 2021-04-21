package com.ssafy.checkmate.common.security;

import com.ssafy.checkmate.common.exception.EncoderNoSuchAlgorithmException;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Sha256 {

    public String encryption(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            return String.format("%040x", new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new EncoderNoSuchAlgorithmException("SHA256EncoderNoSuchAlgorithmException", e);
        }
    }
}
