package pt.sirs.app.StarDrive.production.domain;

import java.time.LocalDateTime;

public class Painter extends Assembler{
    float paintLevels[] = {0, 0, 0};
    boolean paintLevelsAlert = false;


    public Painter(int _id){
        super(_id);
        setPaintLevels(100, 100, 100);
        info("Painter created.");
    }

    public void setPaintLevels(float red, float green, float blue) {
        this.paintLevels[0] = red;
        this.paintLevels[1] = green;
        this.paintLevels[2] = blue;
    }

    public float[] getPaintLevels() {
        return paintLevels;
    }

    public void setPaintLevelsAlert(boolean paintLevelsAlert) {
        this.paintLevelsAlert = paintLevelsAlert;
    }
    
    public boolean checkPaintLevels(){
        for(int i = 0; i < getPaintLevels().length; i++){
            if(getPaintLevels()[i] < 5){
                setPaintLevelsAlert(true);
                return false;
            }
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
        float startLevels[] = getPaintLevels();
        float paintCost = 0.1f;
        setPaintLevels(startLevels[0] - getRandom(0, paintCost), startLevels[1] - getRandom(0, paintCost), startLevels[2] - getRandom(0, paintCost));
        updateTimeRunning();
        setProductionRate(getRandom(7, 12));
        if(!checkPaintLevels()){
            info("PAINT REPLACEMENT ALERT: replace paints in line " + getLine().getSeqNum());
        } 
    }
}
