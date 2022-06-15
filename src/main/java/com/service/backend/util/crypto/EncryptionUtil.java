package com.service.backend.util.crypto;

/**
 * @author Severiano Atencio
 */
public interface EncryptionUtil {

    String encryptPassword(String password) throws Exception;

    boolean validatePassword(String password, String hash) throws Exception;

}
