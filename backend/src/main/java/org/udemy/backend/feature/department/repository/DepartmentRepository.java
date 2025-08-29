package org.udemy.backend.feature.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.udemy.backend.feature.department.model.Department;

import java.util.Optional;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    boolean existsByName(String name);
}
