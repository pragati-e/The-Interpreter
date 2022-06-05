package interpreter.bytecode;


import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


public class LitCode extends ByteCode {

    private int value;
    private int numberofarguments;

    public LitCode() {
        super("LitCode");
    }

    @Override
    public void init(ArrayList<String> args) {
        if (args.size() == 1) {
            this.numberofarguments = 1;
            this.value = Integer.parseInt(args.get(0));
        } else {
            this.numberofarguments = 2;
            this.value = Integer.parseInt(args.get(0));
            args.get(1);
        }
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        virtualmachine.pushToRTStack(this.value);

    }

    @Override
    public String printStringOnConsole(VirtualMachine virtualmachine) {
        if (this.numberofarguments == 1)
            return this.getStringFromFile();
        else {
            String outputstring = this.getStringFromFile();
            return outputstring;
        }
    }
}

