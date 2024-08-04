package com.cb.repository;

import com.cb.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Roles findByName(String name);
    @Query("SELECT r FROM Roles r WHERE r.id_roles != 1 ")
    List<Roles> findAllRoles();

    @Query("SELECT r FROM Roles r WHERE r.id_roles = 3 ")
    List<Roles> findrol(Integer rol);
}