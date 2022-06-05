package interpreter.bytecode;

import interpreter.virtualmachine.*;
import java.util.*;

public class WriteCode extends ByteCode {

    public WriteCode() {
        super("WriteCode");
    }

    @Override
    public void init(ArrayList<String> args) {
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        virtualmachine.printIntegerValue();
    }

    @Override
    public String printStringOnConsole(VirtualMachine virtualmachine) {
        return this.getStringFromFile();
    }
}

