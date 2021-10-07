package com.dev.loja.controllers;

import com.dev.loja.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.dev.loja.models.Funcionario;
import com.dev.loja.repositories.FuncionarioRepository;
import java.util.Optional;
import javax.validation.Valid;

@Controller
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	@Autowired
	private CidadeRepository cidadeRepository;


	@GetMapping("funcionarios/cadastrar")
	public ModelAndView cadastrar(Funcionario funcionario) {
		ModelAndView mv = new ModelAndView("/administrativo/funcionarios/cadastro");
		mv.addObject("funcionario", funcionario);
		mv.addObject("listaCidades", cidadeRepository.findAll());
		return mv;
	}

	@GetMapping("/funcionarios/lista")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/funcionarios/lista");
		mv.addObject("listaFuncionarios", funcionarioRepository.findAll());
		return mv;
	}

	@PostMapping("funcionarios/salvar")
	public ModelAndView salvar(@Valid Funcionario funcionario, BindingResult result) {
		if(result.hasErrors()) {
			return cadastrar(funcionario);
		}
		funcionarioRepository.save(funcionario);
		return cadastrar(new Funcionario());
	}

	@GetMapping("funcionarios/editar/{id}")
	public ModelAndView editar(@PathVariable("id") int id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		return cadastrar(funcionario.get());
	}

	@GetMapping("funcionarios/remover/{id}")
	public ModelAndView remover(@PathVariable("id") int id) {
		funcionarioRepository.deleteById(id);
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

