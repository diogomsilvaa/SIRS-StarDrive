package pt.sirs.StarDrive.production.domain;

public class Painter extends Assembler{
    float paintLevels[];

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

    @Override
    void assemble() throws InterruptedException{
        float startLevels[] = getPaintLevels();
        long startTime = System.currentTimeMillis();
        info("Paint job started in line " + getLine().getSeqNum());
        Thread.sleep(Math.round(getRandom(300, 400)));
        setPaintLevels(startLevels[0] - getRandom(0, 3), startLevels[1] - getRandom(0, 3), startLevels[2] - getRandom(0, 3));
        info("Paint job finished in line " + getLine().getSeqNum());
        addTime(System.currentTimeMillis() - startTime);
    }
}
