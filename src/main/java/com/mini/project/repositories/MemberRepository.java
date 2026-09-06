package com.mini.project.repositories;

import com.mini.project.entities.Author;
import com.mini.project.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m FROM Member m WHERE m.isActive=true")
    List<Member> getAllMembers();

    @Query("SELECT m FROM Member m WHERE m.isActive=true AND m.id=:member")
    Member getById(@Param("member") Long id);
}
