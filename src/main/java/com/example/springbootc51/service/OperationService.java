package com.example.springbootc51.service;

import com.example.springbootc51.entity.Operation;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

    public static Double getResultOperation(Operation operation) {
        double result = 0.0;
        Double value1 = Double.parseDouble(operation.getValue1());
        Double value2 = Double.parseDouble(operation.getValue2());
        String action = operation.getAction();
        if (action.equals("sum")) {
            result = value1 + value2;
        } else if (action.equals("dif")) {
            result = value1 - value2;
        } else if (action.equals("mult")) {
            result = value1 * value2;
        } else if (action.equals("div")) {
            result = value1 / value2;
        }
        return result;
    }
}