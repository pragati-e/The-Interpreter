package interpreter.virtualmachine;

import java.util.Stack;
import interpreter.bytecode.ByteCode;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;
    private boolean        dumpflag;
    private ByteCode       code;

    public VirtualMachine(Program program) {
        this.program = program;
        this.isRunning = true;
        this.dumpflag = true;
    }


    public void executeProgram() {
        this.programCounter = 0;
        this.runTimeStack = new RunTimeStack();
        this.returnAddress = new Stack<Integer>();
        code = program.byteCodeAtAddress(this.programCounter);
        isRunning = true;
        while (isRunning==true) {
            this.programCounter++;
            code.execute(this);

            if (this.dumpflag==true) {
                String stringoutput = code.printStringOnConsole(this);
                if (stringoutput != null)
                    System.out.println(stringoutput);

                this.runTimeStack.dump();
                System.out.println();

            }
            if (this.isRunning==true) {
                code = program.byteCodeAtAddress(programCounter);
            }

            if (this.programCounter >= this.program.getSize()) {
                isRunning = false;
                break;
            }
        }
    }
    public void dumpOn() {
        dumpflag = true;
    }


    public void dumpOff() {
        dumpflag = false;
    }

    public void newFrameAt(int offset) {
        runTimeStack.newFrameAt(offset);
    }

    public int peekFromRTStack() {
        return runTimeStack.peek();
    }


    public int popFromRTStack() {
        int i = runTimeStack.pop();
        return i;
    }


    public int pushToRTStack(int val) {
        runTimeStack.push(val);
        return runTimeStack.peek();
    }


    public void printIntegerValue() {
        int value = 0;
        value = runTimeStack.peek();
        System.out.println(value);
    }


    public void popFrame() {
        runTimeStack.popFrame();
    }


    public int storeOffset(int offset) {
        return runTimeStack.store(offset);
    }


    public int loadStackOffset(int offset) {
        return runTimeStack.load(offset);
    }


    public void setProgramCounter(int pc) {
        this.isRunning = true;
        this.programCounter = pc;
    }


    public void stop() {
        isRunning = false;
    }


    public void pushRA() {
        this.returnAddress.push(this.programCounter);
    }

    public int popRA() {
        int i = this.returnAddress.pop();
        return i;
    }
}
