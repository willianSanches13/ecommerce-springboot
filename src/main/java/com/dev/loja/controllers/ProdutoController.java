package com.dev.loja.controllers;

import com.dev.loja.models.Produto;
import com.dev.loja.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class ProdutoController {
    private static String  CAMINHOIMAGENS = "/home/willian/Documents/imagens/";

    @Autowired
    private ProdutoRepository produtoRepository;

   @GetMapping("produtos/mostrarImagem/{imagem}")
   @ResponseBody
    public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException{
       System.out.println("entrou");
        File imagemArquivo = new File(CAMINHOIMAGENS+imagem);
                return  Files.readAllBytes(imagemArquivo.toPath());
    }


    @GetMapping("produtos/cadastrar")
    public ModelAndView cadastrar(Produto produto) {
        ModelAndView mv = new ModelAndView("/administrativo/produtos/cadastro");
        mv.addObject("produto", produto);
        return mv;
    }

    @GetMapping("/produtos/lista")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/produtos/lista");
        mv.addObject("listaProdutos", produtoRepository.findAll());
        return mv;
    }

    @PostMapping("produtos/salvar")
    public ModelAndView salvar(Produto produto, BindingResult result, @RequestParam("file") MultipartFile arquivo) {

        produtoRepository.saveAndFlush(produto);
      try{
            if(!arquivo.isEmpty()){
                byte[] bytes = arquivo.getBytes();
                Path caminho = Paths.get(CAMINHOIMAGENS+arquivo.getOriginalFilename());
                Files.write(caminho, bytes);
                produto.setNomeImagem(arquivo.getOriginalFilename());
                produtoRepository.saveAndFlush(produto);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return cadastrar(new Produto());
    }

    @GetMapping("produtos/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return cadastrar(produto.get());
    }

    @GetMapping("produtos/remover/{id}")
    public ModelAndView remover(@PathVariable("id") int id) {
        produtoRepository.deleteById(id);
        return  listar();
    }

}

