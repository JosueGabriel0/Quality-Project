package upeu.edu.pe.calculadora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import upeu.edu.pe.calculadora.entity.CalculatorHistory;
import upeu.edu.pe.calculadora.service.CalculatorService;

import java.util.List;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/calculate")
    public double calculate(@RequestParam String operation, @RequestParam double num1, @RequestParam double num2) {
        return calculatorService.calculate(operation, num1, num2);
    }

    @GetMapping("/history")
    public List<CalculatorHistory> getHistory() {
        return calculatorService.getHistory();
    }
}