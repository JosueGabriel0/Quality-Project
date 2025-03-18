package upeu.edu.pe.calculadora.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CalculatorHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String operation;
    private double result;

    public CalculatorHistory() {}

    public CalculatorHistory(String operation, double result) {
        this.operation = operation;
        this.result = result;
    }

    public Long getId() { return id; }
    public String getOperation() { return operation; }
    public double getResult() { return result; }
}