package com.dev.loja.controllers;

import com.dev.loja.models.Papel;
import com.dev.loja.repositories.PapelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class PapelController {

    @Autowired
    private PapelRepository papelRepository;


    @GetMapping("papeis/cadastrar")
    public ModelAndView cadastrar(Papel papel) {
        ModelAndView mv = new ModelAndView("/administrativo/papeis/cadastro");
        mv.addObject("papel", papel);
        return mv;
    }

    @GetMapping("/papeis/lista")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/papeis/lista");
        mv.addObject("listaPapeis", papelRepository.findAll());
        return mv;
    }

    @PostMapping("papeis/salvar")
    public ModelAndView salvar(Papel papel, BindingResult result) {
        if(result.hasErrors()) {
            return cadastrar(papel);
        }
        papelRepository.save(papel);
        return cadastrar(new Papel());
    }

    @GetMapping("papeis/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        Optional<Papel> papel = papelRepository.findById(id);
        return cadastrar(papel.get());
    }

    @GetMapping("papeis/remover/{id}")
    public ModelAndView remover(@PathVariable("id") int id) {
        papelRepository.deleteById(id);
        return  listar();
    }
}

