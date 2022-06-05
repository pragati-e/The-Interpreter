package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


public class CallCode extends ByteCode {

    private String address;

    public CallCode() {
        super("CallCode");
    }

    @Override
    public void init(ArrayList<String> args) {
        if (!args.isEmpty())
            this.address = args.get(0);
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        virtualmachine.pushRA();
        virtualmachine.setProgramCounter(Integer.parseInt(this.address));
    }

    @Override
    public String printStringOnConsole(VirtualMachine virtualmachine) {
        String id = this.getStringFromFile();

        String lineaftersplit[] = id.split("\\s");
        String secondhalf = lineaftersplit[1];

        String ssplit[] = secondhalf.split("<<");
        String functionname = ssplit[0];

        String s =secondhalf+"\t"+functionname+"("+")";

        return s;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }
}

