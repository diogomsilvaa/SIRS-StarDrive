package pt.sirs.StarDrive.production.domain;

public class BatteryAssembler extends Assembler{
    public BatteryAssembler(String _id, AssemblyLine _line){
        super(_id, _line);
        info("Batteries Assembler created and added to assembly line " + getLine().getSeqNum());

    }

    @Override
    void assemble() throws InterruptedException{
        long startTime = System.currentTimeMillis();
        info("Battery insertion started in line " + getLine().getSeqNum());
        Thread.sleep(200);
        info("Battery insertion in line " + getLine().getSeqNum());
        addTime(System.currentTimeMillis() - startTime);
    }
}
