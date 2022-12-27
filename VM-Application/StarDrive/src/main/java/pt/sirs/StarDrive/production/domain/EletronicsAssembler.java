package pt.sirs.StarDrive.production.domain;

public class EletronicsAssembler extends Assembler{

    boolean voltageAlert = false;
    int assembleSteps;

    public EletronicsAssembler(String _id, AssemblyLine _line){
        super(_id, _line);
        assembleSteps = (int) (12000 * STEP_MULTIPLIER);
        info("Eletronics Assembler created and added to assembly line " + getLine().getSeqNum());

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
