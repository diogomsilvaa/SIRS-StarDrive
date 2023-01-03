package pt.sirs.app.StarDrive.production.domain;

import java.util.logging.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.util.Random;

@Document(collection = "assemblers")
public abstract class Assembler {
    
    @Id
    private String id;


    private float productionRate;
    private AssemblyLine line;
    private Duration timeRunning;

    @Transient
    static final double STEP_MULTIPLIER = 100000;

    @Transient
    private Logger logger;

    @Transient
    private Random rand = new Random();

    public Assembler(int id){
        setId("A" + id);
        logger = Logger.getLogger(Assembler.class.getName());
        logger.setLevel(Level.INFO);
        timeRunning = Duration.ZERO;
        productionRate = 0;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
