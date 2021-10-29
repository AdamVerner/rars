package rars.riscv.instructions;

import rars.ProgramStatement;
import rars.riscv.hardware.RegisterFile;
import rars.riscv.BasicInstruction;
import rars.riscv.BasicInstructionFormat;

public class ADDUQB extends BasicInstruction {
    
    public ADDUQB() {
        super("adduqb t1,t2,t3", "Special addition: set t1 to (t2 special plus t3)", 
                BasicInstructionFormat.R_FORMAT,
                "0000000 ttttt sssss 000 fffff 0001011");
    }

    public void simulate(ProgramStatement statement) {
        int[] operands = statement.getOperands();
        RegisterFile.updateRegister(operands[0], compute(RegisterFile.getValue(operands[1]),RegisterFile.getValue(operands[2])));
    }

    public long compute(long value, long value2) {
        return (
                  (((value & 0xFF000000 ) + (value2 & 0xFF000000)) & 0xFF000000)
                | (((value & 0x00FF0000 ) + (value2 & 0x00FF0000)) & 0x00FF0000)
                | (((value & 0x0000FF00 ) + (value2 & 0x0000FF00)) & 0x0000FF00)
                | (((value & 0x000000FF ) + (value2 & 0x000000FF)) & 0x000000FF)
        );
    }

}

