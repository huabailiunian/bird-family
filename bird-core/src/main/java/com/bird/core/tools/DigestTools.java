package com.bird.core.tools;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;

/**
 * @author youly
 * 2019/7/10 19:54
 */
public final class DigestTools {

    private static final BouncyCastleProvider PROVIDER = new BouncyCastleProvider();


    public static String sm3Hex(final String data) {
        return Hex.encodeHexString(sm3(data));
    }

    public static byte[] sm3(final String data) {
        return sm3(StringUtils.getBytesUtf8(data));
    }

    public static byte[] sm3(final byte[] data) {
        return getSm3Digest().digest(data);
    }

    public static String sha256Hex(final String data) {
        return Hex.encodeHexString(sha256(data));
    }

    public static byte[] sha256(final String data) {
        return sha256(StringUtils.getBytesUtf8(data));
    }

    public static byte[] sha256(final byte[] data) {
        return getSha256Digest().digest(data);
    }

    public static String md5Hex(final String data) {
        return Hex.encodeHexString(md5(data));
    }

    public static byte[] md5(final String data) {
        return md5(StringUtils.getBytesUtf8(data));
    }

    public static byte[] md5(final byte[] data) {
        return getMd5Digest().digest(data);
    }

    private static MessageDigest getSm3Digest() {
        return getDigest("SM3");
    }

    private static MessageDigest getMd5Digest() {
        return getDigest("MD5");
    }

    private static MessageDigest getSha256Digest() {
        return getDigest("SHA256");
    }

    private static MessageDigest getDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm, PROVIDER);
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Provider provider() {
        return PROVIDER;
    }

    public static String providerName() {
        return BouncyCastleProvider.PROVIDER_NAME;
    }

    public static void registerProvider() {
        Security.addProvider(PROVIDER);
    }
}
