package interpreter.bytecode;


import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


public class HaltCode extends ByteCode {

    public HaltCode() {
        super("HaltCode");
    }

    @Override
    public void init(ArrayList<String> args) {}

    @Override
    public void execute(VirtualMachine virtualmachine) {
        virtualmachine.stop();
    }


    @Override
    public String printStringOnConsole(VirtualMachine virtualmachine) {
        return this.getStringFromFile();
    }
}


