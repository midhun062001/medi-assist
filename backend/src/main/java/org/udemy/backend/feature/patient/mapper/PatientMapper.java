package org.udemy.backend.feature.patient.mapper;

import org.udemy.backend.common.embeddable.Address;
import org.udemy.backend.common.embeddable.ContactInfo;
import org.udemy.backend.feature.patient.dto.PatientRequestDTO;
import org.udemy.backend.feature.patient.dto.PatientResponseDTO;
import org.udemy.backend.feature.patient.dto.PatientUpdateDTO;
import org.udemy.backend.feature.patient.model.Patient;

/**
 * Mapper class for converting between Patient-related DTOs and Entity objects.
 * This helps isolate the transformation logic from controllers and services.
 *
 * @author Midhun
 * @version 1.0
 */

public class PatientMapper {

    /**
     * Converts a PatientRequestDTO to a Patient entity.
     * Used during patient creation.
     *
     * @param dto the PatientRequestDTO containing input data
     * @return a new Patient entity populated with the provided data
     */
    public static Patient toEntity(PatientRequestDTO dto) {
        Patient patient = new Patient();
        patient.setFirstName(dto.getFirstName().trim());
        patient.setLastName(dto.getLastName().trim());
        patient.setDob(dto.getDob());
        patient.setGender(dto.getGender());
        patient.setBloodGroup(dto.getBloodGroup());
        patient.setContact(dto.getContact());
        patient.setNationality(dto.getNationality());
        patient.setAadhaar(dto.getAadhaar());
        patient.setPassport(dto.getPassport());
        patient.setPassword(dto.getPassword());
        return patient;
    }

    /**
     * Converts a Patient entity to a PatientResponseDTO.
     * Used when returning patient data to clients (e.g., frontend).
     *
     * @param patient the Patient entity to convert
     * @return a DTO suitable for response objects
     */
    public static PatientResponseDTO toResponseDTO(Patient patient) {
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(patient.getId());
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setDob(patient.getDob());
        dto.setGender(patient.getGender());
        dto.setBloodGroup(patient.getBloodGroup());
        dto.setContact(patient.getContact());
        dto.setNationality(patient.getNationality());
        return dto;
    }

    /**
     * Applies partial updates (patch) to an existing Patient entity using a PatientUpdateDTO.
     * Only non-null fields from the patch will be applied.
     *
     * @param patch   the update DTO containing optional fields
     * @param patient the target Patient entity to modify
     */
    public static void attach(PatientUpdateDTO patch, Patient patient) {
        // Update basic fields if present in patch
        if (patch.getFirstName() != null) patient.setFirstName(patch.getFirstName());
        if (patch.getDob() != null) patient.setDob(patch.getDob());
        if (patch.getGender() != null) patient.setGender(patch.getGender());
        if (patch.getBloodGroup() != null) patient.setBloodGroup(patch.getBloodGroup());
        if (patch.getAadhaar() != null) patient.setAadhaar(patch.getAadhaar());
        if (patch.getPassport() != null) patient.setPassport(patch.getPassport());
        if (patch.getNationality() != null) patient.setNationality(patch.getNationality());
        if(patch.getPassword() != null) patient.setPassword(patch.getPassword());

        // update contact information
        if (patch.getContact() != null) {
            ContactInfo patchContact = patch.getContact();
            ContactInfo existingContact = patient.getContact();
            if (existingContact == null) {
                existingContact = new ContactInfo();
                patient.setContact(existingContact);
            }

            if (patchContact.getPhone() != null)
                existingContact.setPhone(patchContact.getPhone());
            if (patchContact.getEmail() != null)
                existingContact.setEmail(patchContact.getEmail());

            // update address if present
            if (patchContact.getAddress() != null) {
                Address patchAddress = patchContact.getAddress();
                Address existingAddress = existingContact.getAddress();
                if (existingAddress == null) {
                    existingAddress = new Address();
                    existingContact.setAddress(existingAddress);
                }

                if (patchAddress.getCity() != null)
                    existingAddress.setCity(patchAddress.getCity());
                if (patchAddress.getState() != null)
                    existingAddress.setState(patchAddress.getState());
                if (patchAddress.getZipCode() != null)
                    existingAddress.setZipCode(patchAddress.getZipCode());
            }
        }
    }
}
