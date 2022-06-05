package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


public class DumpCode extends ByteCode {

    private String value;

    public DumpCode() {
        super("DumpCode");
    }

    @Override
    public void init(ArrayList<String> args) {
        if (!args.isEmpty())
            this.value = args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        if (this.value.equals("ON"))
            virtualmachine.dumpOn();
        else
            virtualmachine.dumpOff();
    }

    @Override
    public String printStringOnConsole(VirtualMachine virtualmachine) {
        return null;
    }
}
