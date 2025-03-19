package upeu.edu.pe.calculadora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upeu.edu.pe.calculadora.entity.CalculatorHistory;
import upeu.edu.pe.calculadora.entity.Numbers;

import java.util.List;

@Service
public interface CalculatorService {
    public Double calculate(Numbers numbers);
    public List<CalculatorHistory> getHistory();
}