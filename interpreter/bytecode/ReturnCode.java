package interpreter.bytecode;


import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


public class ReturnCode extends ByteCode {

    private int numArgs;

    public ReturnCode() {
        super("ReturnCode");
    }

    @Override
    public void init(ArrayList<String> args) {
        if (!args.isEmpty())
            this.numArgs = args.size();

    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        virtualmachine.popFrame();
        int address = virtualmachine.popRA();
        virtualmachine.setProgramCounter(address);
    }

    @Override
    public String printStringOnConsole(VirtualMachine virtualmachine) {

        if (this.numArgs== 0)
            return this.getStringFromFile();
        else{
            String stringtoprint= this.getStringFromFile();
            return stringtoprint;
        }
    }
}
