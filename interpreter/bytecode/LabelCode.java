package interpreter.bytecode;


import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


public class LabelCode extends ByteCode {

    private String label;

    public LabelCode() {
        super("LabelCode");
    }

    @Override
    public void init(ArrayList<String> args) {
        if (!args.isEmpty())
            this.label = args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {}

    @Override
    public String printStringOnConsole(VirtualMachine virtualmachine) {
        return this.getStringFromFile();
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label){
        this.label = label;
    }
}
