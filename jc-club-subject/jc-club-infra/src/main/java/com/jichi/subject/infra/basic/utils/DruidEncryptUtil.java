package com.jichi.subject.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * 数据库加密Util
 */
public class DruidEncryptUtil {
    private static String publicKey;
    private static String privateKey;

    static {
        try {
            String[] keyPair = ConfigTools.genKeyPair(512);
            publicKey = keyPair[0];
            System.out.println("publicKey:" + publicKey);
            privateKey = keyPair[1];
            System.out.println("privateKey:" + privateKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }

    }

    public static String encrypt(String plainText) throws Exception {
        String encrypt = ConfigTools.encrypt(publicKey, plainText);
        System.out.println("encrypt:" + encrypt);
        return encrypt;
    }

    public static String decrypt(String encryptText) throws Exception {
        String decrypt = ConfigTools.decrypt(privateKey, encryptText);
        System.out.println("decrypt:" + decrypt);
        return decrypt;
    }

    public static void main(String[] args) throws Exception {
        String encrypt = encrypt("ywb123456");
        System.out.println("encrypt: " + encrypt);
    }
}
