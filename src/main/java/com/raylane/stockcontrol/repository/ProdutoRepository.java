package com.raylane.stockcontrol.repository;

import com.raylane.stockcontrol.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE p.quantidade <= p.estoqueMinimo")
    List<Produto> buscarProdutosComEstoqueBaixo();
}
