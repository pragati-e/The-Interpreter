package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


public class BopCode extends ByteCode {

    private String operationalization;

    public BopCode() {
        super("BopCode");
    }

    @Override
    public void init(ArrayList<String> args) {
        if (!args.isEmpty())
            this.operationalization = args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        int num1, num2;
        num2 = virtualmachine.popFromRTStack();
        num1 = virtualmachine.popFromRTStack();

        int returnVal = 0;
        switch (this.operationalization) {
            case "+":
                returnVal = num1 + num2;
                break;
            case "-":
                returnVal = num1 - num2;
                break;
            case "/":
                returnVal = num1 / num2;
                break;
            case "*":
                returnVal = num1 * num2;
                break;
            case "==":
                returnVal = (num1 == num2) ? 1 : 0;
                break;
            case "!=":
                returnVal = (num1 != num2) ? 1 : 0;
                break;
            case "<=":
                returnVal = (num1 <= num2) ? 1 : 0;
                break;
            case ">":
                returnVal = (num1 > num2) ? 1 : 0;
                break;
            case ">=":
                returnVal = (num1 >= num2) ? 1 : 0;
                break;
            case "<":
                returnVal = (num1 < num2) ? 1 : 0;
                break;
            case "|":
                if (num1 == 0 && num2 == 0) {
                    returnVal = 0;
                } else {
                    returnVal = 1;
                }
                break;
            case "&":
                if (num1 == 1 && num2 == 1) {
                    returnVal = 1;
                } else {
                    returnVal = 0;
                }
                break;
        }
        virtualmachine.pushToRTStack(returnVal);
    }

    @Override
    public String printStringOnConsole(VirtualMachine virtualmachine) {
        return this.getStringFromFile();
    }
}
