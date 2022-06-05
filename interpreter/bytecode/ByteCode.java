package interpreter.bytecode;

import interpreter.virtualmachine.*;
import java.util.ArrayList;


public abstract class ByteCode {

    private String codeName;
    private String nameFromFile;

    public ByteCode(String bcName) {
        this.codeName = bcName;
    }

    public abstract void init(ArrayList<String> args);
    public abstract void execute(VirtualMachine virtualmachine);
    public abstract String printStringOnConsole(VirtualMachine virtualmachine);

    public void setByteCode(String in) {
        nameFromFile = in;
    }

    public String getStringFromFile() {
        return nameFromFile;
    }

    public String getCodeName() {
        return this.codeName;
    }
}