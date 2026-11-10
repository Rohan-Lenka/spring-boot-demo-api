package com.rohan.demo_api.repository;

import com.rohan.demo_api.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // this annotation not really required, use this just for naming convention.
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
    // JpaRepository<Entity Name, Data type of the id of the Entity>
}
