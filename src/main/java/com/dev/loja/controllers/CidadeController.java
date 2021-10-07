package com.dev.loja.controllers;

import com.dev.loja.models.Cidade;
import com.dev.loja.repositories.CidadeRepository;
import com.dev.loja.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;


    @GetMapping("cidades/cadastrar")
    public ModelAndView cadastrar(Cidade cidade) {
        ModelAndView mv = new ModelAndView("/administrativo/cidades/cadastro");
        mv.addObject("cidades", cidade);
        mv.addObject("listaEstados", estadoRepository.findAll());
        return mv;
    }

    @GetMapping("/cidades/lista")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/cidades/lista");
        mv.addObject("listaCidades", cidadeRepository.findAll());
        return mv;
    }

    @PostMapping("cidades/salvar")
    public ModelAndView salvar(Cidade cidade, BindingResult result) {
        if(result.hasErrors()) {
            return cadastrar(cidade);
        }
        cidadeRepository.save(cidade);
        return cadastrar(new Cidade());
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
    }

//	@GetMapping("/funcionarios/endereco/{cep}")
/*	public ModelAndView buscarCep(@PathVariable("cep") String cep) {
		Funcionario funcionario = new Funcionario();
		try {
			 Endereco endereco = cepService.buscaEnderecoPorCep(cep);
			  funcionario.setEndereco(endereco);
			  return cadastrar(funcionario);
		}catch(Exception e) {
			return cadastrar(funcionario);
		}
	}*/

}

