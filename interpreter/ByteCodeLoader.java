
package interpreter;

import interpreter.virtualmachine.Program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import interpreter.bytecode.ByteCode;
import interpreter.virtualmachine.Program;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    private Program program;
    private static LinkedHashMap<Integer,String[]> arguments;

    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN loadCodes.
     */

    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }

    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts. Can also use the split function in the String class.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */

    public Program loadCodes() {
        String line;
        String className;
        arguments = new  LinkedHashMap<Integer,String[]>();
        Class<ByteCode> classBlueprint;
        Constructor<ByteCode> byteCodeConstructor;
        program = new Program();
        ByteCode byteCode;
        ArrayList<String[]> args = new ArrayList<String[]>();

        try {
            int whileloopcounter=0;
            while (this.byteSource.ready()) {
                args = new ArrayList<String[]>();
                line = this.byteSource.readLine();
                String[] items = line.split("\\s+");
                args.add(items);
                arguments.put(whileloopcounter, items);
                className = interpreter.CodeTable.getClassName(items[0]);
                classBlueprint = (Class<ByteCode>) Class.forName("interpreter.bytecode." + className);
                byteCodeConstructor = classBlueprint.getDeclaredConstructor();
                byteCode = (ByteCode) byteCodeConstructor.newInstance();

                ArrayList<String> exargs = new ArrayList<String>();
                for(int i = 0;i<items.length;i++) {
                    if(i>0)
                        exargs.add(items[i]);
                }
                byteCode.init(exargs);
                byteCode.setByteCode(line); //Entire string from file
                program.add(byteCode);
                whileloopcounter++;
            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.exit(-1);
        }
        program.resolveAddress();
        return program;
    }
}
