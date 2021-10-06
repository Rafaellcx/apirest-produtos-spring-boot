package com.produtos.apirest.resources;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Produtos")
@CrossOrigin(origins = "*")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping("/produtos")
    @ApiOperation(value = "Retorna uma lista de Produtos")
    public ResponseEntity<List<Produto>> listaProdutos(){

        List<Produto> produtosList = service.findAll();
        if (produtosList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Produto produto : produtosList) {
                long id = produto.getId();
                produto.add(linkTo(methodOn(ProdutoResource.class).listaProdutoUnico(id)).withSelfRel());
            }
            return new ResponseEntity<List<Produto>>(produtosList, HttpStatus.OK);
        }

//        List<Produto> produtosList = service.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(produtosList);
    }

    @GetMapping("/produto/{id}")
    @ApiOperation(value = "Retorna um produto único")
    public ResponseEntity<Produto> listaProdutoUnico(@PathVariable(value = "id") long id){
        Optional<Produto> produto = Optional.ofNullable(service.findById(id));
        if (!produto.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            produto.get().add(linkTo(methodOn(ProdutoResource.class).listaProdutos()).withRel("Lista de Produtos"));
            return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
        }
//        final Produto produto = service.findById(id);
//
//        return ResponseEntity.ok(produto);
    }

    @PostMapping("/produto")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Salva um produto")
    public Produto salvaProduto(@RequestBody Produto produto) {

        return service.save(produto);

//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/produto/{id}")
    @ApiOperation(value = "Atualiza um produto")
    public ResponseEntity<Produto> atualizaProduto(@PathVariable(value = "id") long id, @RequestBody Produto dto) {
        final Produto produto = service.update(id, dto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/produto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta um produto")
    public void deletaProduto(@PathVariable(value = "id") long id) {
        service.delete(id);
    }


}
