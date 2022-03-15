package com.silvany.dasafioCrud.controllers;

import java.util.List;

import com.silvany.dasafioCrud.DTO.ClienteDTO;
import com.silvany.dasafioCrud.entities.Cliente;
import com.silvany.dasafioCrud.service.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    // codigo abaixo foi comentado para test e depois, descomentado
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> lista = clienteService.findAll();
        return ResponseEntity.ok(lista);
    }

    // metódo post
    @PostMapping
    @ResponseBody
    public ResponseEntity<ClienteDTO> saveCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente entidade = clienteService.fromDTO(clienteDTO);
        clienteService.saveCliente(entidade);
        return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.CREATED);

    }

    // metódo put
    @PutMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Integer id, @RequestBody ClienteDTO clienteDTO) {
        Cliente entidade = clienteService.fromDTO(clienteDTO);
        if (clienteService.updateCliente(id, entidade) != null) {
            return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.CREATED);

        }
        ;
        return ResponseEntity.notFound().build();

    }

    // metódo DELETE
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}

