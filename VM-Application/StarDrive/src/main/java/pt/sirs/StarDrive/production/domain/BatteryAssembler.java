package pt.sirs.StarDrive.production.domain;

public class BatteryAssembler extends Assembler{

    float batteryTemp;
    boolean tempAlert;

    public BatteryAssembler(String _id, AssemblyLine _line){
        super(_id, _line);
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
        if(getBatteryTemp() > 95){
            setTempAlert(true);
            return false;
        }
        return true;
    }
    @Override
    void assemble() throws InterruptedException{
        long startTime = System.currentTimeMillis();
        info("Battery insertion started in line " + getLine().getSeqNum());
        Thread.sleep(Math.round(getRandom(200, 300)));
        setBatteryTemp(getRandom(50, 100));
        addTime(System.currentTimeMillis() - startTime);
        if(!checkBateries()){
            info("Battery insertion not finished in line " + getLine().getSeqNum() + "due to high temperatures level.");
        } 
        info("Battery insertion finished in line " + getLine().getSeqNum());
    }
}
