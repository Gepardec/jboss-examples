package test;

public class CalcRequest {
    private final int operand1;
    private final int operand2;
    private final Operation operation;

    public CalcRequest(int operand1, int operand2, Operation operation) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
    }

    public int getOperand1() {
        return operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    public Operation getOperation() {
        return operation;
    }
}
