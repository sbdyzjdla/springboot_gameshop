package com.gameshop.domain.qnas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QnasRepository extends JpaRepository<Qnas, Long> {

    @Query("SELECT q FROM Qnas q ORDER BY q.id DESC")
    List<Qnas> findAllDesc();
}
