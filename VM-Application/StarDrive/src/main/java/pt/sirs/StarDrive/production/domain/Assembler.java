package pt.sirs.StarDrive.production.domain;

import java.util.logging.*;
import java.time.Duration;
import java.util.Random;

public abstract class Assembler {
    private String id;
    private float productionRate;
    private AssemblyLine line;
    private Duration timeRunning;
    static final double STEP_MULTIPLIER = 100000;
    private Logger logger;
    Random rand = new Random();

    public Assembler(String _id, AssemblyLine _line){
        setId(_id);
        setLine(_line);
        logger = Logger.getLogger(Assembler.class.getName());
        logger.setLevel(Level.INFO);
        timeRunning = Duration.ZERO;
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
        this.logger.log(Level.INFO, message);
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

    abstract void assemble();
}
