package interpreter.bytecode;


import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


public class LoadCode extends ByteCode {

    private int offset;
    private int numArgs;

    public LoadCode() {
        super("LoadCode");
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        virtualmachine.loadStackOffset(this.offset);
    }

    @Override
    public String printStringOnConsole(VirtualMachine virtualmachine) {
        if (this.numArgs == 1)
            return this.getStringFromFile();
        else {
            String outputstring = this.getStringFromFile();
            return outputstring;
        }
    }

    @Override
    public void init(ArrayList<String> args) {
        if (!args.isEmpty()){
            this.numArgs = args.size();
            this.offset = Integer.parseInt(args.get(0));
            if (args.size() == 2)
                args.get(1);
        }
    }
}



