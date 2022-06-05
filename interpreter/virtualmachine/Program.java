package interpreter.virtualmachine;

import interpreter.bytecode.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Program {

    private ArrayList<ByteCode> program;
    private Map<String, List<ByteCode>> labelObject;
    private Map<String, Integer> labelAddress;
    private int addressnumber;

    public Program() {
        program = new ArrayList<>();
        labelObject = new HashMap<>();
        labelAddress = new HashMap<>();

        addressnumber = 0;
    }

    protected ByteCode getCode(int programCounter) {

        return this.program.get(programCounter);
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter
     * HINT: make note what type of data-structure ByteCodes are stored in.
     */
    public void resolveAddress() {
        for (String label : labelAddress.keySet()) {
            int symbolicAddress = labelAddress.get(label);

            List<ByteCode> branchByteCodes = labelObject.get(label);

            if (branchByteCodes != null) {
                for (Iterator<ByteCode> it = branchByteCodes.iterator(); it.hasNext();) {
                    ByteCode bc = (ByteCode) it.next();

                    String byteCodeName = bc.getCodeName();

                    if (byteCodeName.equals("GotoCode")) {
                        GotoCode gotoCode = (GotoCode) bc;
                        gotoCode.setAddress(Integer.toString(symbolicAddress));
                    } else if (byteCodeName.equals("FalseBranchCode")) {
                        FalseBranchCode falseBranchCode = (FalseBranchCode) bc;
                        falseBranchCode.setAddress(Integer.toString(symbolicAddress));
                    } else if (byteCodeName.equals("CallCode")) {
                        CallCode callCode = (CallCode) bc;
                        callCode.setAddress(Integer.toString(symbolicAddress));
                    }
                }
            }
        }
    }

    public void add(ByteCode bytecode) {
        String byteCodeName = bytecode.getCodeName();

        if (byteCodeName.equals("GotoCode") || byteCodeName.equals("FalseBranchCode")|| byteCodeName.equals("CallCode")) {
            String label = null;
            if (byteCodeName.equals("GotoCode")) {
                GotoCode gotoByteCode = (GotoCode) bytecode; // Case the ByteCode
                label = gotoByteCode.getAddress();
            } else if (byteCodeName.equalsIgnoreCase("FalseBranchCode")) {
                FalseBranchCode falseBranchCode = (FalseBranchCode) bytecode; // cast bytecode
                label = falseBranchCode.getAddress();
            } else if (byteCodeName.equals("CallCode")) {
                CallCode callCode = (CallCode) bytecode; // Cast bytecode
                label = callCode.getAddress();
            }
            if (labelObject.containsKey(label)) {
                List<ByteCode> byteCodeLabelList = labelObject.get(label);
                byteCodeLabelList.add(bytecode);
            } else {
                List<ByteCode> byteCodeLabelList = new ArrayList<>();
                byteCodeLabelList.add(bytecode);

                labelObject.put(label, byteCodeLabelList);
            }
        } else if (byteCodeName.equals("LabelCode")) {
            String label = null;
            LabelCode labelCode = (LabelCode) bytecode;
            label = labelCode.getLabel();
            if (!labelAddress.containsKey(label))

                labelAddress.put(label, addressnumber);

        }

        program.add(bytecode);
        addressnumber++;

    }

    public void printProgram() {
        for (int i=0; i<this.program.size();i++) {
            System.out.println(this.program.get(i));
        }
    }


    public ByteCode byteCodeAtAddress(int addr) {
        ByteCode bc = null;
        if (addr >= 0 && addr < program.size())
            bc = program.get(addr);
        return bc;

    }

    public int getSize() {
        return program.size();
    }
}
