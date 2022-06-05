package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


public class ArgsCode extends ByteCode {

    private int counterarguments;

    public ArgsCode() {
        super("ArgsCode");
    }

    @Override
    public void init(ArrayList<String> args) {
        if (args.size() != 0)
        {
            String argument = args.get(0);
            this.counterarguments = Integer.parseInt(argument);
        }
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        virtualmachine.newFrameAt(this.counterarguments);
    }

    @Override
    public String printStringOnConsole(VirtualMachine virtualmachine) {
        return this.getStringFromFile();
    }
}
