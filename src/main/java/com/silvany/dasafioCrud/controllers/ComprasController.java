package com.silvany.dasafioCrud.controllers;

import java.util.List;

import com.silvany.dasafioCrud.DTO.ComprasDTO;
import com.silvany.dasafioCrud.DTO.RankingComprasDTO;
import com.silvany.dasafioCrud.entities.Compras;
import com.silvany.dasafioCrud.service.ComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController // VAI TER ENDPOINTS
@RequestMapping("/compras")
public class ComprasController {
    @Autowired
    private ComprasService comprasService;

    // codigo abaixo foi comentado para test e depois, descomentado
    @GetMapping
    public ResponseEntity<List<ComprasDTO>> findAll() {
        List<ComprasDTO> lista = comprasService.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/ranking-cliente-por-compras")
    public ResponseEntity<List<RankingComprasDTO>> findRanking() {
        List<RankingComprasDTO> lista = comprasService.findRanking();
        return ResponseEntity.ok(lista);
    }

    // metódo POST
    @PostMapping
    @ResponseBody
    public ResponseEntity<ComprasDTO> saveCompras(@RequestBody ComprasDTO comprasDTO) {
        Compras entidade = comprasService.fromDTO(comprasDTO);
        comprasService.saveCliente(entidade);
        return new ResponseEntity<ComprasDTO>(comprasDTO, HttpStatus.CREATED);

    }

    // metódo PUT
    @PutMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<ComprasDTO> updateCompras(@PathVariable Integer id, @RequestBody ComprasDTO comprasDTO) {
        Compras entidade = comprasService.fromDTO(comprasDTO);
        if (comprasService.updateCompras(id, entidade) != null) {
            return new ResponseEntity<ComprasDTO>(comprasDTO, HttpStatus.CREATED);

        }
        ;
        return ResponseEntity.notFound().build();

    }

    // metódo DELETE
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCompras(@PathVariable Integer id) {
        comprasService.deleteCompras(id);
        return ResponseEntity.noContent().build();
    }

}
