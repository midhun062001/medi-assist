package org.udemy.backend.feature.doctor.enums;

public enum Designation {
    TRAINEE("Trainee"),
    CONSULTANT("Consultant"),
    SENIOR_CONSULTANT("Senior"),
    RESIDENT("Resident"),
    ASSOCIATE("Associate"),
    SPECIALIST("Specialist"),
    HEAD_OF_DEPARTMENT("Head of Department"),
    PRESIDENT("President"),;

    private final String displayName;

    Designation(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
