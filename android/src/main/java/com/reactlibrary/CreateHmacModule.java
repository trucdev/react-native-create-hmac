package com.reactlibrary;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class CreateHmacModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public CreateHmacModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "CreateHmac";
    }

    @ReactMethod
    public void createHmacSHA1(String key, String message, Promise promise) {
        try {
            final String hashingAlgorithm = "HmacSHA1"; //or "HmacSHA1", "HmacSHA512"
            byte[] bytes = hmac(hashingAlgorithm, key.getBytes(), message.getBytes());
            final String messageDigest = bytesToHex(bytes);
            promise.resolve(messageDigest);

        } catch (Exception e) {
            e.printStackTrace();
            promise.reject(e.getMessage());
        }


    }

    public static byte[] hmac(String algorithm, byte[] key, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(message);
    }

    public static String bytesToHex(byte[] bytes) {
        final char[] hexArray = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0, v; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}
