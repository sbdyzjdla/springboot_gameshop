package com.gameshop.domain.products.consoles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsolesRepository extends JpaRepository<Consoles, Long> {

    @Query("SELECT c FROM Consoles c ORDER BY c.id DESC")
    List<Consoles> findAllDesc();

    @Query("SELECT c FROM Consoles c WHERE c.manufact = '닌텐도' ORDER BY c.id DESC")
    List<Consoles> findAllNint();

    @Query("SELECT c FROM Consoles c WHERE c.manufact = '소니' ORDER BY c.id DESC")
    List<Consoles> findAllPs5();


}
