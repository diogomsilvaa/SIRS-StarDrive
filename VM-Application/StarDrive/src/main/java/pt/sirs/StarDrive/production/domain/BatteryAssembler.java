package pt.sirs.StarDrive.production.domain;

public class BatteryAssembler extends Assembler{

    float batteryTemp;
    boolean tempAlert;
    int assembleSteps;

    public BatteryAssembler(String _id, AssemblyLine _line){
        super(_id, _line);
        assembleSteps = (int) (10000 * STEP_MULTIPLIER);
        info("Batteries Assembler created and added to assembly line " + getLine().getSeqNum());

    }

    public void setBatteryTemp(float batteryTemp) {
        this.batteryTemp = batteryTemp;
    }

    public float getBatteryTemp() {
        return batteryTemp;
    }

    public void setTempAlert(Boolean tempAlert) {
        this.tempAlert = tempAlert;
    }

    public boolean checkBateries(){
        if(getBatteryTemp() > 99.99){
            setTempAlert(true);
            return false;
        }
        return true;
    }
    @Override
    void assemble() {
        long startTime = System.currentTimeMillis();
        info("Battery insertion started in line " + getLine().getSeqNum());
        for(int step = 0; step < assembleSteps; step++){
            setBatteryTemp(getRandom(50 + (40*step)/(assembleSteps), 50 + (50*step)/(assembleSteps)));
            if(!checkBateries()){
                info("HIGH TEMPERATURE ALERT: battery insertion in line " + getLine().getSeqNum());
            } 
        }
        addTime(System.currentTimeMillis() - startTime);
        info("Battery insertion finished in line " + getLine().getSeqNum());
    }
}
