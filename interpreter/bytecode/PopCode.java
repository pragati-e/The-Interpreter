package interpreter.bytecode;


import interpreter.virtualmachine.*;
import java.util.*;


public class PopCode extends ByteCode {

    private int popnumber;

    public PopCode() {
        super("PopCode");
    }

    @Override
    public void init(ArrayList<String> args) {
        if (!args.isEmpty())
            this.popnumber = Integer.parseInt(args.get(0));
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        for (int i = 0; i < this.popnumber; i++){
            virtualmachine.popFromRTStack();
        }
    }

    @Override
    public String printStringOnConsole(VirtualMachine virtualmachine) {
        return this.getStringFromFile();
    }
}
