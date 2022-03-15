package com.silvany.dasafioCrud.service;

import java.util.List;
import java.util.stream.Collectors;

import com.silvany.dasafioCrud.DTO.ComprasDTO;
import com.silvany.dasafioCrud.DTO.RankingComprasDTO;
import com.silvany.dasafioCrud.entities.Cliente;
import com.silvany.dasafioCrud.entities.Compras;
import com.silvany.dasafioCrud.repositories.ClienteRepository;
import com.silvany.dasafioCrud.repositories.ComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ComprasService {
    @Autowired
    private ComprasRepository comprasRepository;

    // para listar tanto as informações do cliente quanto das compras.Evita o fluxo
    // da várias consultas no sql
    @Autowired
    private ClienteRepository clienteRepository; // add

    public List<ComprasDTO> findAll() {
        clienteRepository.findAll(); // add
        List<Compras> resList = comprasRepository.findAll();
        return resList.stream().map(c -> new ComprasDTO(c)).collect(Collectors.toList());
    }

    public List<RankingComprasDTO> findRanking() {
        List<RankingComprasDTO> res = comprasRepository.findRanking();
        return res;
    }

    // método POST
    public Compras saveCliente(Compras compras) {
        return comprasRepository.save(compras);
    }

    // método PUT
    public Compras updateCompras(Integer id, Compras newCompras) {
        return comprasRepository.findById(id).map(c -> {
            c.setTotalCompra(newCompras.getTotalCompra());
            c.setDataCompra(newCompras.getDataCompra());
            Compras atualizado = comprasRepository.save(c);
            return atualizado;
        }).orElse(null);
    }

    // método DELETE
    public void deleteCompras(Integer id) {
        try {

            if (comprasRepository.findById(id) != null)
                comprasRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {// quando tentar deletar um cliente que tenha uma compra
            throw new DataIntegrityViolationException("Você não pode deletar um cliente que fez compras");
        }

    }

    public Compras fromDTO(ComprasDTO comprasDTO) {
        Compras entidade = new Compras(0, comprasDTO.getTotalCompra(), comprasDTO.getDataCompra(),
                new Cliente(comprasDTO.getCliente().getId(), null, null));
        return entidade;
    }




}