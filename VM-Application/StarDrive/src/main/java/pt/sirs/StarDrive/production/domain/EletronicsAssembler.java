package pt.sirs.StarDrive.production.domain;

public class EletronicsAssembler extends Assembler{

    boolean voltageAlert = false;

    public EletronicsAssembler(String _id, AssemblyLine _line){
        super(_id, _line);
        info("Eletronics Assembler created and added to assembly line " + getLine().getSeqNum());

    }

    public boolean testVoltage() {
        float voltage = getRandom(60, 120);
        if(voltage < 65 || voltage > 115){
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
    void assemble() throws InterruptedException{
        long startTime = System.currentTimeMillis();
        info("Electronics assembling started in line " + getLine().getSeqNum());
        Thread.sleep(Math.round(getRandom(350, 450)));
        addTime(System.currentTimeMillis() - startTime);
        if(!testVoltage()){
            info("Electronics assembling not finished in line " + getLine().getSeqNum() + "due to voltage problems.");
            return;
        } 
        info("Electronics assembling finished in line " + getLine().getSeqNum());
    }
}
