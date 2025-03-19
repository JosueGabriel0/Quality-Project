package upeu.edu.pe.calculadora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upeu.edu.pe.calculadora.entity.CalculatorHistory;
import upeu.edu.pe.calculadora.entity.Numbers;
import upeu.edu.pe.calculadora.service.CalculatorService;

import java.util.List;

@RestController
@RequestMapping("/calculadora")
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    @PostMapping("/calcular")
    public ResponseEntity<Double> calculate(@RequestBody Numbers numbers) {
        return ResponseEntity.status(HttpStatus.OK).body(calculatorService.calculate(numbers));
    }

    @GetMapping("/historial")
    public ResponseEntity<List<CalculatorHistory>> getHistory() {
        return ResponseEntity.status(HttpStatus.OK).body(calculatorService.getHistory());
    }
}