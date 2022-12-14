package pt.sirs.StarDrive.production.domain;

import java.time.Duration;
import java.util.Random;

abstract class Assembler {
    private String id;
    private float productionRate;
    private AssemblyLine line;
    private Duration timeRunning;
    Random rand = new Random();

    public Assembler(String _id, AssemblyLine _line){
        setId(_id);
        setLine(_line);
        
    }

    private void setId(String _id) {
        this.id = _id;
    }

    public String getId() {
        return id;
    }

    public void set_productionRate(float _productionRate) {
        this.productionRate = _productionRate;
    }
    
    public float getProductionRate() {
        return productionRate;
    }

    public void info(String message){
        this.line.info(message);
    }

    public void setLine(AssemblyLine line) {
        this.line = line;
    }

    public AssemblyLine getLine() {
        return line;
    }

    public Duration getTimeRunning() {
        return timeRunning;
    }

    public void addTime(long millis){
        timeRunning.plusMillis(millis);
    }

    public float getRandom(float min, float max) {
        return min + (max-min) * rand.nextFloat();
    }

    abstract void assemble() throws InterruptedException;
}
