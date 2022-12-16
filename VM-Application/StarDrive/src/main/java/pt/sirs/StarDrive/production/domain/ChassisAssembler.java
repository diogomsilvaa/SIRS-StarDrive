package pt.sirs.StarDrive.production.domain;

public class ChassisAssembler extends Assembler{

    boolean stabilityAlert = false;

    public ChassisAssembler(String _id, AssemblyLine _line){
        super(_id, _line);
        info("Chasis Assembler created and added to assembly line " + getLine().getSeqNum());

    }

    public boolean testStability() {
        float stability = getRandom(80, 100);
        if(stability < 82){
            setStabilityAlert(true);
            info("Stability problem in line " + getLine().getSeqNum());
            return false;
        }
        return true;
    }

    public void setStabilityAlert(boolean stabilityAlert) {
        this.stabilityAlert = stabilityAlert;
    }

    @Override
    void assemble() throws InterruptedException{
        long startTime = System.currentTimeMillis();
        info("Chassis placement started in line " + getLine().getSeqNum());
        Thread.sleep(Math.round(getRandom(500, 600)));
        addTime(System.currentTimeMillis() - startTime);
        if(!testStability()){
            info("Chassis placement not finished in line " + getLine().getSeqNum() + "due to stability problems.");
            return;
        } 
        info("Chassis placement finished in line " + getLine().getSeqNum());
    }
}
