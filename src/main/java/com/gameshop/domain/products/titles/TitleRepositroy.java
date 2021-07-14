package com.gameshop.domain.products.titles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRepositroy extends JpaRepository<Titles, Long> {

    @Query("SELECT t FROM Titles t WHERE t.console = '닌텐도스위치' ORDER BY t.id DESC")
    List<Titles> findAllNS();

    @Query("SELECT t FROM Titles t WHERE t.console = 'PS5' ORDER BY t.id DESC")
    List<Titles> findAllPS();

}
