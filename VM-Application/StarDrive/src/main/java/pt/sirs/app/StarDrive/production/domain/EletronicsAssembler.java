package pt.sirs.app.StarDrive.production.domain;

import org.springframework.data.annotation.Transient;

public class EletronicsAssembler extends Assembler{

    boolean voltageAlert = false;

    @Transient
    int assembleSteps;

    public EletronicsAssembler(int _id){
        super(_id);
        assembleSteps = (int) (12000 * STEP_MULTIPLIER);
        info("Eletronics Assembler created");

    }

    public boolean testVoltage() {
        float voltage = getRandom(60, 120);
        if(voltage < 60.01 || voltage > 19.99){
            setVoltageAlert(true);
            info("Voltage problem in line " + getLine().getSeqNum());
            return false;
        }
        return true;
    }

   public void setVoltageAlert(boolean voltageAlert) {
       this.voltageAlert = voltageAlert;
   }


    @Override
    void assemble(){
        long startTime = System.currentTimeMillis();
        info("Electronics assembling started in line " + getLine().getSeqNum());
        for(int step = 0; step < assembleSteps; step++){
            if(step % assembleSteps != 0) continue;
            if(!testVoltage()){
                info("Electronics assembling not finished in line " + getLine().getSeqNum() + " due to voltage problems.");
            } 
        }
        addTime(System.currentTimeMillis() - startTime);
        info("Electronics assembling finished in line " + getLine().getSeqNum());
    }
}
