package pt.sirs.StarDrive.production.domain;

public class Painter extends Assembler{
    float paintLevels[];
    boolean paintLevelsAlert = false;

    public Painter(String _id, AssemblyLine _line){
        super(_id, _line);
        info("Painter created and added to assembly line " + getLine().getSeqNum());
        setPaintLevels(100, 100, 100);
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
    void assemble() throws InterruptedException{
        float startLevels[] = getPaintLevels();
        long startTime = System.currentTimeMillis();
        info("Paint job started in line " + getLine().getSeqNum());
        Thread.sleep(Math.round(getRandom(300, 400)));
        setPaintLevels(startLevels[0] - getRandom(0, 3), startLevels[1] - getRandom(0, 3), startLevels[2] - getRandom(0, 3));
        addTime(System.currentTimeMillis() - startTime);
        if(!checkPaintLevels()){
            info("Warning: replace paints in line " + getLine().getSeqNum());
        } 
        info("Paint job finished in line " + getLine().getSeqNum());
    }
}
