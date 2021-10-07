package com.dev.loja.controllers;

import com.dev.loja.models.Permissao;
import com.dev.loja.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class PermissaoController {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PapelRepository papelRepository;

    @GetMapping("permissoes/cadastrar")
    public ModelAndView cadastrar(Permissao permissao) {
        ModelAndView mv = new ModelAndView("/administrativo/permissoes/cadastro");
        mv.addObject("listaFuncionarios", funcionarioRepository.findAll());
        mv.addObject("listaPapeis", papelRepository.findAll());
        return mv;
    }

    @GetMapping("/permissoes/lista")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/permissoes/lista");
        mv.addObject("listaPermissoes", permissaoRepository.findAll());
        return mv;
    }

    @PostMapping("permissoes/salvar")
    public ModelAndView salvar(Permissao permissao, BindingResult result) {
        if(result.hasErrors()) {
            return cadastrar(permissao);
        }
        permissaoRepository.save(permissao);
        return cadastrar(new Permissao());
    }

    @GetMapping("permissao/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        Optional<Permissao> permissao = permissaoRepository.findById(id);
        return cadastrar(permissao.get());
    }

    @GetMapping("permissoes/remover/{id}")
    public ModelAndView remover(@PathVariable("id") int id) {
        permissaoRepository.deleteById(id);
        return  listar();
    }


}

