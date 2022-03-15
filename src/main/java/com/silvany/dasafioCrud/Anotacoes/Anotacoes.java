package com.silvany.dasafioCrud.Anotacoes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-word") //path de requisição

public class Anotacoes {

    @GetMapping("")
    public String msgHelloWord() {
        return "Hello Word";
    }

}