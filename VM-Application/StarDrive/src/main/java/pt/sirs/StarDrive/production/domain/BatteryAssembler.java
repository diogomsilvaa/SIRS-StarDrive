package pt.sirs.StarDrive.production.domain;

public class BatteryAssembler extends Assembler{
    public BatteryAssembler(String _id, AssemblyLine _line){
        super(_id, _line);
        info("Batteries Assembler created and added to assembly line " + getLine().getSeqNum());

    }

    @Override
    void assemble() {
        
    }
}
