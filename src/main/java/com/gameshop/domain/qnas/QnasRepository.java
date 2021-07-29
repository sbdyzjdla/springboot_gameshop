package com.gameshop.domain.qnas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnasRepository extends JpaRepository<Qnas, Long> {

//    @Query("SELECT q FROM Qnas q ORDER BY q.id DESC")
//    List<Qnas> findAllDesc();
}
