package upeu.edu.pe.calculadora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upeu.edu.pe.calculadora.entity.CalculatorHistory;

import java.util.List;

@Service
public interface CalculatorService {
    public double calculate(String operation, double num1, double num2);
    public List<CalculatorHistory> getHistory();
}