package pt.sirs.app.StarDrive.production.domain;

public class ChassisAssembler extends Assembler{

    boolean stabilityAlert = false;
    int assembleSteps;

    public ChassisAssembler(int _id,AssemblyLine _line){
        super(_id, _line);
        assembleSteps = (int) (12000 * STEP_MULTIPLIER);
        info("Chasis Assembler created and added to assembly line " + getLine().getSeqNum());

    }

    public boolean testStability() {
        float stability = getRandom(80, 100);
        if(stability < 99.8){
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
    void assemble(){
        long startTime = System.currentTimeMillis();
        info("Chassis placement started in line " + getLine().getSeqNum());
        for(int step = 0; step < assembleSteps; step++){
            if(step % assembleSteps != 0) continue;
            if(!testStability()){
                info("Chassis placement not finished in line " + getLine().getSeqNum() + "due to stability problems.");
            } 
        }
        addTime(System.currentTimeMillis() - startTime);
        info("Chassis placement finished in line " + getLine().getSeqNum());
    }
}
