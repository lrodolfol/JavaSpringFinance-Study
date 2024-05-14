package com.tinosnegocios.financas.repositories;

import com.tinosnegocios.financas.entities.BalanceInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceInPutRepository extends JpaRepository<BalanceInput, Long> {
}
