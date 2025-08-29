package org.udemy.backend.common.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.udemy.backend.common.util.AESEncryptionUtil;

/**
 * JPA AttributeConverter that transparently encrypts and decrypts String fields
 * using AES encryption for secure storage in the database.
 *
 * This converter is typically applied to sensitive fields such as Aadhaar numbers,
 * passport numbers, etc., to ensure data privacy and compliance.
 *
 * Usage:
 * Add the annotation {@code @Convert(converter = EncryptedStringConverter.class)}
 * on any entity field to enable automatic encryption/decryption.
 *
 * Example:
 * <pre>
 * {@code
 * @Convert(converter = EncryptedStringConverter.class)
 * private String aadhaar;
 * }
 * </pre>
 *
 * @author Midhun
 * @version 1.0
 */
@Converter
public class EncryptedStringConverter implements AttributeConverter<String, String> {
    /**
     * Converts a plain-text string to an encrypted string before storing in the database.
     *
     * @param plainValue the original unencrypted string
     * @return the encrypted string to store in the database, or null if input is null
     */
    @Override
    public String convertToDatabaseColumn(String plainValue) {
        if (plainValue == null) return null;
        return AESEncryptionUtil.encrypt(plainValue);
    }

    /**
     * Converts an encrypted string from the database back to its decrypted plain-text form.
     *
     * @param encryptedValue the encrypted string from the database
     * @return the decrypted plain-text string, or null if input is null
     */
    @Override
    public String convertToEntityAttribute(String encryptedValue) {
        if (encryptedValue == null) return null;
        return AESEncryptionUtil.decrypt(encryptedValue);
    }
}
