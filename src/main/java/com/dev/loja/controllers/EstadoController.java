package com.dev.loja.controllers;

import com.dev.loja.models.Estado;
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
public class EstadoController{

    @Autowired
    private EstadoRepository estadoRepository;


    @GetMapping("estados/cadastrar")
    public ModelAndView cadastrar(Estado estado) {
        ModelAndView mv = new ModelAndView("/administrativo/estados/cadastro");
        mv.addObject("estado", estado);
        return mv;
    }

    @GetMapping("/estados/lista")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/estados/lista");
        mv.addObject("listaEstados", estadoRepository.findAll());
        return mv;
    }

    @PostMapping("estados/salvar")
    public ModelAndView salvar(Estado estado, BindingResult result) {
        if(result.hasErrors()) {
            return cadastrar(estado);
        }
        estadoRepository.save(estado);
        return cadastrar(new Estado());
    }

    @GetMapping("estados/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        return cadastrar(estado.get());
    }

    @GetMapping("estados/remover/{id}")
    public ModelAndView remover(@PathVariable("id") int id) {
        estadoRepository.deleteById(id);
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

