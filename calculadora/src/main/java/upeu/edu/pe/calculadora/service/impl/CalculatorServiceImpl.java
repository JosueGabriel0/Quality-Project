package upeu.edu.pe.calculadora.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upeu.edu.pe.calculadora.entity.CalculatorHistory;
import upeu.edu.pe.calculadora.repository.CalculatorHistoryRepository;
import upeu.edu.pe.calculadora.service.CalculatorService;

import java.util.List;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Autowired
    private CalculatorHistoryRepository calculatorHistoryRepository;

    public double calculate(String operation, double num1, double num2) {
        double result = switch (operation) {
            case "add" -> num1 + num2;
            case "subtract" -> num1 - num2;
            case "multiply" -> num1 * num2;
            case "divide" -> num2 != 0 ? num1 / num2 : 0;
            default -> throw new IllegalArgumentException("Invalid operation");
        };
        calculatorHistoryRepository.save(new CalculatorHistory(operation + " " + num1 + " " + num2, result));
        return result;
    }

    public List<CalculatorHistory> getHistory() {
        return calculatorHistoryRepository.findAll();
    }
}
