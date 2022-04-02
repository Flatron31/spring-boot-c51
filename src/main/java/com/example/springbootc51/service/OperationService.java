package com.example.springbootc51.service;

import com.example.springbootc51.entity.Operation;

public class OperationService {

    public static Double getResultOperation(Operation operation) {
        double result = 0.0;
        Double value1 = Double.parseDouble(operation.getValue1());
        Double value2 = Double.parseDouble(operation.getValue2());
        String operation1 = operation.getOperation();
        switch (operation1) {
            case "sum":
                result = value1 + value2;
                break;
            case "dif":
                result = value1 - value2;
                break;
            case "mult":
                result = value1 * value2;
                break;
            case "div":
                result = value1 / value2;
                break;
        }
        return result;
    }
}
