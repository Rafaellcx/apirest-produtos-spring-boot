package com.produtos.apirest.service;

import com.produtos.apirest.exception.ProdutoNotFoundException;
import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(final long id) {
        final Optional<Produto> produto = Optional.ofNullable(produtoRepository.findById(id));

        if (produto.isPresent()) {
            return produto.get();
        } else {
            throw new ProdutoNotFoundException();
        }
    }

    public Produto save(final Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto update(long id, Produto dto) {
        final Optional<Produto> produtoEntity = Optional.ofNullable(produtoRepository.findById(id));
        final Produto produto;

        if (produtoEntity.isPresent()) {
            produto = produtoEntity.get();
        } else {
            throw new ProdutoNotFoundException();
        }

        produto.setNome(dto.getNome());
        produto.setQuantidade(dto.getQuantidade());
        produto.setValor(dto.getValor());

        return produtoRepository.save(produto);
    }

    public void delete(long id) {
        final Produto produto = findById(id);

        produtoRepository.delete(produto);
    }
}
