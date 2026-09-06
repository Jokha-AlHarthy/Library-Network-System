package com.mini.project.repositories;

import com.mini.project.entities.Author;
import com.mini.project.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    @Query("SELECT s FROM Staff s WHERE s.isActive=true")
    List<Staff> getAllStaffs();

    @Query("SELECT s FROM Staff s WHERE s.isActive=true AND s.id=:staff")
    Staff getById(@Param("staff") Long id);
}
