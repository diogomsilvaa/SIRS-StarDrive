package pt.sirs.app.StarDrive.production.domain;

import java.time.LocalDateTime;

public class BatteryAssembler extends Assembler{

    private float batteryTemp;
    private boolean tempAlert;

    public BatteryAssembler(int _id){
        super(_id);
        info("Batteries Assembler created");

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

    public boolean getTempAlert() {
        return tempAlert;
    }

    public boolean checkBateries(){
        if(getBatteryTemp() > 99.99){
            setTempAlert(true);
            return false;
        }
        return true;
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
        setBatteryTemp(getRandom(50, 100));
        updateTimeRunning();
        setProductionRate(getRandom(7, 12));
        if(!checkBateries()){
            info("HIGH TEMPERATURE ALERT: battery insertion in line " + getLineId() + " not finished due to high temperature.");
        } 
    }
}
