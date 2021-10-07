package com.dev.loja.controllers;

import com.dev.loja.models.Cidade;
import com.dev.loja.models.EntradaItens;
import com.dev.loja.models.EntradaProduto;
import com.dev.loja.models.Produto;
import com.dev.loja.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EntradaProdutoController {
    List <EntradaItens> listaEntrada = new ArrayList<>();

    @Autowired
    private EntradaProdutoRepository entradaProdutoRepository;

    @Autowired
    private EntradaItensRepository entradaItensRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    @GetMapping("entrada/cadastrar")
    public ModelAndView cadastrar(EntradaProduto entrada, EntradaItens entradaItens) {
        ModelAndView mv = new ModelAndView("/administrativo/entrada/cadastro");
        mv.addObject("entrada", entrada);
        mv.addObject("listaEntradaItens", this.listaEntrada);
        mv.addObject("entradaItens", entradaItens);
        mv.addObject("listaFuncionarios", funcionarioRepository.findAll());
        mv.addObject("listaProdutos", produtoRepository.findAll());
        return mv;
    }

  /*  @GetMapping("/cidades/lista")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/cidades/lista");
        mv.addObject("listaCidades", cidadeRepository.findAll());
        return mv;
    }



    @GetMapping("cidades/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        return cadastrar(cidade.get());
    }

    @GetMapping("cidades/remover/{id}")
    public ModelAndView remover(@PathVariable("id") int id) {
        cidadeRepository.deleteById(id);
        return  listar();
    }*/
    @PostMapping("entrada/salvar")
    public ModelAndView salvar(String acao, EntradaProduto entrada, EntradaItens entradaItens) {
        if(acao.equals("itens")){
            this.listaEntrada.add(entradaItens);
        }else if(acao.equals("salvar")){
            entradaProdutoRepository.save(entrada);
            for(EntradaItens it: listaEntrada){
                it.setEntrada(entrada);
                entradaItensRepository.save(it);
                Optional<Produto> prod = produtoRepository.findById(it.getProduto().getId());
                Produto produto = prod.get();
                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + it.getQuantidade());
                produtoRepository.save(produto);
                this.listaEntrada = new ArrayList<>();

            }
            return cadastrar(new EntradaProduto(), new EntradaItens());
        }
        return cadastrar(entrada, new EntradaItens());
    }



}

