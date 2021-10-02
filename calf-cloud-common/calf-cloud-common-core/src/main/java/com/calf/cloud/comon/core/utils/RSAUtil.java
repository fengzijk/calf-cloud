/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月03日 01时49分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-03 01:49:29    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.comon.core.utils;

import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * -------------------------------------------------
 * <p>
 * RSA公钥/私钥/签名工具包
 * </p>
 * <p>
 * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman）
 * </p>
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/3 1:49
 * --------------------------------------------------
 */
public class RSAUtil {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "test";
    private static final String PRIVATE_KEY = "testprivate";


    private static String encryptBASE64(byte[] key) {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    private static byte[] decryptBASE64(String key) throws IOException {
        return new BASE64Decoder().decodeBuffer(key);
    }

    /**
     * 初始化密钥
     */
    public static Map<String, Object> initKey() {
        KeyPairGenerator keyPairGen;
        Map<String, Object> keyMap = new HashMap<>(2);
        try {
            keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(2048);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();// 公钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();// 私钥
            keyMap.put(PUBLIC_KEY, publicKey);
            keyMap.put(PRIVATE_KEY, privateKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyMap;
    }

    /**
     * 获取私钥
     */
    public static String getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 获取公钥
     */
    public static String getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 用公钥解密
     */
    public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
        byte[] keyBytes = decryptBASE64(key);// 对密钥解密
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 用私钥解密
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
        byte[] keyBytes = decryptBASE64(key);// 对密钥解密
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 用公钥加密
     */
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
        byte[] keyBytes = decryptBASE64(key);// 对公钥解密
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 用私钥加密
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
        byte[] keyBytes = decryptBASE64(key);// 对密钥解密
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data 加密数据
     * @param privateKey 私钥
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }

    public static String sign(String str, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);
        byte[] data = decryptBASE64(str);
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }

    /**
     * 校验数字签名
     *
     * @param data 加密数据
     * @param publicKey 公钥
     * @param sign 数字签名
     * @return 校验成功返回true 失败返回false
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        //解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);

        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        // 验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }

    /**
     * 校验数字签名
     *
     * @param str 加密数据
     * @param publicKey 公钥
     * @param sign 数字签名
     * @return 校验成功返回true 失败返回false
     */
    public static boolean verify(String str, String publicKey, String sign) throws Exception {
        //解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);
        byte[] data = decryptBASE64(str);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        // 验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }


    /**

     * @author: fengzijk
     * @email :guozhifengvip@gmail.com
     * @date: 2019/7/2 14:34
     */

    /**
     * <pre>私钥加密</pre>
     *
     * @param privateKey 私钥
     * @param str 需要加密的字符串
     * @return java.lang.String 返回加密后的字符串
     * @author : guozhifeng
     * @date : 2021/10/3 1:51
     */
    public static String encryptByPrivateKey(String str, String privateKey) throws Exception {
        byte[] data = str.getBytes();
        return encryptBASE64(encryptByPrivateKey(data, privateKey));
    }

    public static String decryptByPrivateKey(String str, String privateKey) throws Exception {
        return new String(decryptByPublicKey(decryptBASE64(str), privateKey));
    }

    /**
     * <pre>公钥加密</pre>
     *
     * @param str 需要加密的字符串
     * @return java.lang.String 返回加密后的字符串
     * @author : guozhifeng
     * @date : 2021/10/3 1:51
     */
    public static String encryptByPublicKey(String str, String publicKey) throws Exception {
        byte[] data = str.getBytes();
        return encryptBASE64(encryptByPublicKey(data, publicKey));
    }

    /**
     * <pre>用公钥解密</pre>
     *
     * @param publicKey 公钥
     * @param str 加密后的字符串
     * @return java.lang.String 返回解密后的字符串
     * @author : guozhifeng
     * @date : 2021/10/3 1:51
     */
    public static String decryptByPublicKey(String str, String publicKey) throws Exception {
        return new String(decryptByPublicKey(decryptBASE64(str), publicKey));
    }


}