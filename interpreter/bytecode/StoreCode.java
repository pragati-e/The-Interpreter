package interpreter.bytecode;


import interpreter.virtualmachine.*;
import java.util.*;


public class StoreCode extends ByteCode {

    private int offset;
    private String message;
    private int numberofargs;

    public StoreCode() {
        super("StoreCode");
    }

    @Override
    public void init(ArrayList<String> args) {
        if (!args.isEmpty())
        {
            this.offset = Integer.parseInt(args.get(0));
            if (args.size() == 2)
                this.message = args.get(1);
            this.numberofargs = args.size();
        }
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        virtualmachine.storeOffset(this.offset);
    }

    @Override
    public String printStringOnConsole(VirtualMachine virtualmachine) {
        if (this.numberofargs == 1) {
            return this.getStringFromFile();
        }
        else {
            var topOfStack = virtualmachine.peekFromRTStack();
            String formattedString = this.getStringFromFile();
            return formattedString;
        }
    }
}

