package pt.sirs.StarDrive.production.domain;

public class Painter extends Assembler{
    float paintLevels[] = {0, 0, 0};
    boolean paintLevelsAlert = false;
    int assembleSteps;


    public Painter(String _id, AssemblyLine _line){
        super(_id, _line);
        assembleSteps = (int) (3000 * STEP_MULTIPLIER);
        setPaintLevels(100, 100, 100);
        info("Painter created and added to assembly line " + getLine().getSeqNum());
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
    void assemble(){
        float startLevels[] = getPaintLevels();
        long startTime = System.currentTimeMillis();
        info("Paint job started in line " + getLine().getSeqNum());
        for(int step = 0; step < assembleSteps; step++){
            float paintCost = 3/assembleSteps;
            setPaintLevels(startLevels[0] - getRandom(0, paintCost), startLevels[1] - getRandom(0, paintCost), startLevels[2] - getRandom(0, paintCost));
            if(!checkPaintLevels()){
                info("PAINT REPLACEMENT ALERT: replace paints in line " + getLine().getSeqNum());
            } 
        }
        addTime(System.currentTimeMillis() - startTime);
        info("Paint job finished in line " + getLine().getSeqNum());
    }
}
