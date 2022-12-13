package pt.sirs.StarDrive.production.domain;

public class Painter extends Assembler{
    public Painter(String _id, AssemblyLine _line){
        super(_id, _line);
        info("Painter created and added to assembly line " + getLine().getSeqNum());
    }

    @Override
    void assemble() {
        
    }
}
