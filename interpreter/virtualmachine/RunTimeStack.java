package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.Stack;

class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer>     framePointer;
    private int runTimeStackPtr;

    public RunTimeStack() {
        runTimeStack = new ArrayList<Integer>();
        framePointer = new Stack<Integer>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        runTimeStackPtr = 0;
    }

    /**
     * Used for dumping the current state of the runTimeStack. * It will print portions of the stack based on respective * frame markers.
     * Example [1,2,3] [4,5,6] [7,8]
     * Frame pointers would be 0,3,6
     */
    public void dump() {
        int numFrame = framePointer.size();
        if (numFrame == 0)
            printDumpHelper(0, runTimeStackPtr - 1);
        else {
            int startIndex = 0;
            for (int i = 0; i < numFrame; i++)
            {
                int nextFrame = framePointer.get(i);
                int rStackPtr = nextFrame;
                printDumpHelper(startIndex, rStackPtr);
                startIndex = rStackPtr + 1;
            }
            printDumpHelper(startIndex, runTimeStackPtr - 1);
        }
    }

    private void printDumpHelper(int start, int stop) {
        System.out.print('[');
        for (int i = start; i <= stop; i++)
        {
            System.out.print(runTimeStack.get(i));
            if (i != (stop))
            {
                System.out.print(',');
            }
        }
        System.out.print(']');
    }

    /**
     * returns the top copy of the runtime stack, but does not remove
     * @return copy of the top of the stack
     */
    public int peek(){
        return (int) runTimeStack.get(runTimeStackPtr - 1);
    }

    /**
     * push the value i to the top of the stack.
     * @param i value to be pushed.
     * @return value pushed
     */
    public int push(int i) {
        runTimeStack.add(runTimeStackPtr, i);
        runTimeStackPtr++;
        return i;
    }

    /**
     * removes  to the top of the run time stack
     * @return value popped
     */
    public int pop() {
        int popVal = (int) runTimeStack.get(runTimeStackPtr - 1);
        runTimeStack.set(runTimeStackPtr - 1, 0);
        runTimeStackPtr--;
        return popVal;
    }
    /**
     * Takes the top item of the run time stack and stores
     * it into a offset starting from the current frame
     * @param offset number of slots above current frame marker
     * @return the item just stored
     */
    public int store(int offset) {
        int frame = 0;
        if (framePointer.size() != 0) {
            int frObj = framePointer.peek();
            frame = frObj;
        }else{
            frame = 0;
        }
        int topvalue = this.pop();
        runTimeStack.set(frame + offset, topvalue);
        return topvalue;
    }

    /**
     * Takes a value from the run time stack that is at offset
     * from the current frame marker and pushes it onto the top of * the stack.
     * @param offset number of slots above current frame marker
     * @return item just loaded into the offset
     */
    public int load(int offset) {
        int frame = 0;
        if (framePointer.size() == 0) {
            frame = 0;
        }else{
            int frObj = framePointer.peek();
            frame = frObj + 1;
        }
        int returnvalue = (int) runTimeStack.get(frame + offset);
        this.push(returnvalue);
        return returnvalue;
    }

    /**
     * create a new frame pointer at the index offset slots down * from the top of the runtime stack.
     * @param offset slots down from the top of the runtime stack */
    public void newFrameAt(int offset) {
        int rStackPtr = (runTimeStackPtr - 1) - offset;
        framePointer.add(rStackPtr);
    }

    /**
     * pop  the current frame off the runtime stack. Also removes frame pointer value from the FramePointer Stack.
     */
    public void popFrame() {
        int poppingvalue = this.pop();
        int frObj = framePointer.pop();
        int rStackPtr = frObj;

        this.runTimeStackPtr = (rStackPtr + 1);
        this.push(poppingvalue);
    }

    public Integer push(Integer i) {
        runTimeStack.add(i);
        runTimeStackPtr++;
        return i;
    }

}
