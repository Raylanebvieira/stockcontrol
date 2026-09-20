package com.raylane.stockcontrol.repository;

import com.raylane.stockcontrol.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
