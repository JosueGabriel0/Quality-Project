package upeu.edu.pe.calculadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upeu.edu.pe.calculadora.entity.CalculatorHistory;

@Repository
public interface CalculatorHistoryRepository extends JpaRepository<CalculatorHistory, Long> {}