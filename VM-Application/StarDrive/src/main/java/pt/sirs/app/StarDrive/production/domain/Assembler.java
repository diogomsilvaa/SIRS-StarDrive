package pt.sirs.app.StarDrive.production.domain;

import java.util.logging.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

public abstract class Assembler {
    
    @Id
    private String id;


    private float productionRate;
    private Duration timeRunning;
    private LocalDateTime startTime;
    private boolean onProduction;
    private String lineId;


    @Transient
    private Logger logger = Logger.getLogger(Assembler.class.getName());

    @Transient
    private Random rand = new Random();

    public Assembler(int id){
        setId("A" + id);
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

    public void setProductionRate(float _productionRate) {
        this.productionRate = _productionRate;
    }
    
    public float getProductionRate() {
        return productionRate;
    }

    public void info(String message){
        this.logger.log(Level.INFO, message);
    }

    public Duration getTimeRunning() {
        return timeRunning;
    }

    public void addTime(long millis){
        timeRunning.plusMillis(millis);
    }

    public void updateTimeRunning(){
        timeRunning = Duration.between(startTime, LocalDateTime.now());
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public float getRandom(float min, float max) {
        return min + (max-min) * rand.nextFloat();
    }

    public void setOnProduction(boolean onProduction) {
        this.onProduction = onProduction;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineId() {
        return lineId;
    }

    public boolean isOnProduction() {
        return onProduction;
    }

    public abstract void assemble();

    public abstract void updateInfo();
}
