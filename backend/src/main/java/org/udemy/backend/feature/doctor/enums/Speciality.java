package org.udemy.backend.feature.doctor.enums;

public enum Speciality {
    GENERAL_MEDICINE("General Medicine"),
    CARDIOLOGY("Cardiology"),
    DERMATOLOGY("Dermatology"),
    NEUROLOGY("Neurology"),
    PEDIATRICS("Pediatrics"),
    ORTHOPEDICS("Orthopedics"),
    GYNECOLOGY("Gynecology"),
    ENT("ENT (Ear, Nose & Throat)");

    private final String displayName;

    Speciality(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
