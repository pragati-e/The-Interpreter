package interpreter.bytecode;


import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


public class FalseBranchCode extends ByteCode {

    private String address;

    public FalseBranchCode() {
        super("FalseBranchCode");
    }

    @Override
    public void init(ArrayList<String> args) {
        if (!args.isEmpty())
            this.address = args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        int division = virtualmachine.popFromRTStack();
        if (division == 0)
            virtualmachine.setProgramCounter(Integer.parseInt(this.address));
    }

    @Override
    public String printStringOnConsole(VirtualMachine virtualmachine) {
        return this.getStringFromFile();
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String addr){
        this.address = addr;
    }
}

