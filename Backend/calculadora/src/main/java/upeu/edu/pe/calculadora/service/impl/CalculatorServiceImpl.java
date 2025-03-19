package upeu.edu.pe.calculadora.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upeu.edu.pe.calculadora.entity.CalculatorHistory;
import upeu.edu.pe.calculadora.entity.Numbers;
import upeu.edu.pe.calculadora.repository.CalculatorHistoryRepository;
import upeu.edu.pe.calculadora.service.CalculatorService;

import java.util.List;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Autowired
    private CalculatorHistoryRepository calculatorHistoryRepository;

    public Double calculate(Numbers numbers) {
        double result = switch (numbers.getOperacion()) {
            case "suma" ->  numbers.getNum1() + numbers.getNum2();
            case "resta" -> numbers.getNum1() - numbers.getNum2();
            case "multiplicacion" -> numbers.getNum1() * numbers.getNum2();
            case "division" -> numbers.getNum2() != 0 ? numbers.getNum1() / numbers.getNum2() : 0;
            case "potencia" -> Math.pow(numbers.getNum1(), numbers.getNum2());
            case "raizCuadrada" -> Math.sqrt(numbers.getNum1());
            case "modulo" -> numbers.getNum1() % numbers.getNum2();
            case "cambioDeSigno" -> -numbers.getNum1();
            default -> throw new IllegalArgumentException("Operacion Invalida");
        };
        CalculatorHistory calculatorHistory = new CalculatorHistory();
        String operacionHistorial = numbers.getNum1() + " " + numbers.getOperacion() + " " + numbers.getNum2();
        calculatorHistory.setOperation(operacionHistorial);
        calculatorHistory.setResult(result);
        calculatorHistoryRepository.save(calculatorHistory);
        return result;
    }

    public List<CalculatorHistory> getHistory() {
        return calculatorHistoryRepository.findAll();
    }
}
