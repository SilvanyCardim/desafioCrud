package com.silvany.dasafioCrud.repositories;

import com.silvany.dasafioCrud.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//repository sem banco de dados
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}