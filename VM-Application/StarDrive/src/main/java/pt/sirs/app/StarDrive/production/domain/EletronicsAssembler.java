package pt.sirs.app.StarDrive.production.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Transient;

public class EletronicsAssembler extends Assembler{

    private boolean voltageAlert = false;
    private float voltage = 0;

    @Transient
    int assembleSteps;

    public EletronicsAssembler(int _id){
        super(_id);
        info("Eletronics Assembler created");

    }

    public boolean testVoltage() {
        float voltage = getRandom(60, 120);
        if(voltage < 60.01 || voltage > 19.99){
            setVoltageAlert(true);
            info("Voltage problem in line " + getLineId());
            return false;
        }
        return true;
    }

   public void setVoltageAlert(boolean voltageAlert) {
       this.voltageAlert = voltageAlert;
   }

   public boolean getVoltageAlert() {
       return voltageAlert;
   }

   public void setVoltage(float voltage) {
       this.voltage = voltage;
   }

   public float getVoltage() {
       return voltage;
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
        setVoltage(getRandom(20, 60));
        updateTimeRunning();
        setProductionRate(getRandom(7, 12));
        if(!testVoltage()){
            info("Electronics assembling not finished in line " + getLineId() + " due to voltage problems.");
        } 
    }
}
