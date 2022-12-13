package pt.sirs.StarDrive.production.domain;

public class ChassisAssembler extends Assembler{
    public ChassisAssembler(String _id, AssemblyLine _line){
        super(_id, _line);
        info("Chasis Assembler created and added to assembly line " + getLine().getSeqNum());

    }

    @Override
    void assemble() {
        
    }
}
