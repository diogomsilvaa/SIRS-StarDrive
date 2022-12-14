package pt.sirs.StarDrive.production.domain;

public class ChassisAssembler extends Assembler{
    public ChassisAssembler(String _id, AssemblyLine _line){
        super(_id, _line);
        info("Chasis Assembler created and added to assembly line " + getLine().getSeqNum());

    }

    @Override
    void assemble() throws InterruptedException{
        long startTime = System.currentTimeMillis();
        info("Chassis placement started in line " + getLine().getSeqNum());
        Thread.sleep(200);
        info("Chassis placement finished in line " + getLine().getSeqNum());
        addTime(System.currentTimeMillis() - startTime);
    }
}
