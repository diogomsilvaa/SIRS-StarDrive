package pt.sirs.app.StarDrive.production.domain;

import java.time.LocalDateTime;

public class ChassisAssembler extends Assembler{

    private boolean stabilityAlert = false;
    private double stability = 0;

    public ChassisAssembler(int _id){
        super(_id);
        info("Chasis Assembler created");
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

    public boolean getStabilityAlert() {
        return stabilityAlert;
    }

    public void setStability(double stability) {
        this.stability = stability;
    }

    public double getStability() {
        return stability;
    }

    @Override
    public void assemble() {
        setOnProduction(true);
        setStartTime(LocalDateTime.now());
        updateTimeRunning();
    }

    @Override
    public void updateInfo(){
        if(!isOnProduction()) return;
        setStability(getRandom(70, 80));
        updateTimeRunning();
        setProductionRate(getRandom(7, 12));
        if(!testStability()){
            info("Chassis placement not finished in line " + getLine().getSeqNum() + " due to stability problems.");
        } 
    }
}
