package com.dev.loja.controllers;
import com.dev.loja.models.Compra;
import com.dev.loja.models.ItensCompra;
import com.dev.loja.models.Produto;
import com.dev.loja.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CarrinhoController {
    private List<ItensCompra> itensCompra = new ArrayList<>();
    private Compra compra = new Compra();

    @Autowired
    private ProdutoRepository produtoRepository;
    private void calcularTotal(){
        this.compra.setValorTotal(0.0);
        for(ItensCompra it: itensCompra)
            this.compra.setValorTotal(this.compra.getValorTotal() + it.getValorTotal());
    }

    @GetMapping("/carrinho")
    public ModelAndView chamarCarrinho(){
        ModelAndView mv = new ModelAndView("index/carrinho");
        calcularTotal();
        mv.addObject("compra", this.compra);
        mv.addObject("listaProdutos", itensCompra);
        return mv;
    }
    @GetMapping("/adicionarCarrinho/{id}")
        public ModelAndView chamarCarrinho(@PathVariable int id){
            ModelAndView mv = new ModelAndView("index/carrinho");
            Optional<Produto> prod =  produtoRepository.findById(id);
            Produto produto = prod.get();
            int controle =0;
            for(ItensCompra it: itensCompra){
                if(it.getProduto().getId() == produto.getId()){
                    it.setQuantidade(it.getQuantidade() + 1);
                    it.setValorTotal(it.getValorTotal() + it.getQuantidade() * it.getValorUnitario());
                    controle = 1;
                    break;
                }
            }
            if(controle == 0) {
                ItensCompra item = new ItensCompra();
                item.setProduto(produto);
                item.setValorUnitario(produto.getValorVenda());
                item.setQuantidade(item.getQuantidade() + 1);
                item.setValorTotal(item.getValorTotal() + item.getQuantidade() * item.getValorUnitario());
                itensCompra.add(item);
            }
            mv.addObject("listaProdutos", itensCompra);
            return mv;
        }

    @GetMapping("/alterarQuantidade/{id}/{acao}")
    public ModelAndView alterarQuantidade(@PathVariable int id, @PathVariable int acao){
        for(ItensCompra it: itensCompra){
            if(it.getProduto().getId() == id){
                if(acao == 1) {
                    it.setQuantidade(it.getQuantidade() + 1);
                    it.setValorTotal(0.0);
                    it.setValorTotal(it.getValorTotal() + it.getQuantidade() * it.getValorUnitario());
                }else if(acao == 0){
                    it.setQuantidade(it.getQuantidade() - 1);
                    it.setValorTotal(0.0);
                    it.setValorTotal(it.getValorTotal() + it.getQuantidade() * it.getValorUnitario());
                }
                break;
            }
        }

        ModelAndView mv = new ModelAndView("index/carrinho");
        mv.addObject("listaProdutos", itensCompra);
        return mv;
    }
    @GetMapping("/removerProduto/{id}")
    public ModelAndView removerProduto(@PathVariable int id){
        for(ItensCompra it: itensCompra){
            if(it.getProduto().getId() == id){
                itensCompra.remove(it);
                break;
            }
        }

        ModelAndView mv = new ModelAndView("index/carrinho");
        mv.addObject("listaProdutos", itensCompra);
        return mv;
    }
    }



