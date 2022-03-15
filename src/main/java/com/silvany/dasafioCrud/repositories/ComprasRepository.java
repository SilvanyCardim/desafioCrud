package com.silvany.dasafioCrud.repositories;

import com.silvany.dasafioCrud.DTO.RankingComprasDTO;
import com.silvany.dasafioCrud.entities.Compras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComprasRepository extends JpaRepository<Compras, Integer> {
    @Query("SELECT new com.silvany.dasafioCrud.DTO.RankingComprasDTO(compras.cliente, SUM(compras.totalCompra)) "
            +  "FROM Compras AS compras GROUP BY compras.cliente ")
    List<RankingComprasDTO> findRanking();


}
