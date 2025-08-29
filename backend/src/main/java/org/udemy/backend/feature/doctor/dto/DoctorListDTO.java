package org.udemy.backend.feature.doctor.dto;

import lombok.Builder;
import lombok.Data;
import org.udemy.backend.feature.doctor.enums.Speciality;

@Data
@Builder
public class DoctorListDTO {
    private int id;
    private String name;
    private Speciality speciality;
    private String departmentName;
    private double fee;
}
