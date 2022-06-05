package interpreter.bytecode;


import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;
import java.util.Scanner;


public class ReadCode extends ByteCode {

    public ReadCode() {
        super("ReadCode");
    }

    @Override
    public void init(ArrayList<String> args) {
    }

    @Override
    public void execute(VirtualMachine virtualmachine) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter an integer : ");
        int number = sc.nextInt();
        virtualmachine.pushToRTStack(number);
        sc.close();
    }

    @Override
    public String printStringOnConsole(VirtualMachine virtualmachine) {
        return this.getStringFromFile();
    }
}


