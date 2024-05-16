package com.tinosnegocios.financas.repositories;

import com.tinosnegocios.financas.entities.BalanceFlow;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BalanceFlowRepository extends JpaRepository<BalanceFlow, Long> {
    @Modifying
    @Transactional
    @Query("""
            DELETE FROM BalanceFlow tbf
            WHERE tbf.balanceInput.id = :inputId
            """)
    void deleteByInputId(Long inputId);
}
