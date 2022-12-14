package pt.sirs.StarDrive.production.domain;

public class EletronicsAssembler extends Assembler{

    public EletronicsAssembler(String _id, AssemblyLine _line){
        super(_id, _line);
        info("Eletronics Assembler created and added to assembly line " + getLine().getSeqNum());

    }
    
    @Override
    void assemble() throws InterruptedException{
        long startTime = System.currentTimeMillis();
        info("Electronics assembling started in line " + getLine().getSeqNum());
        Thread.sleep(300);
        info("Electronics assembling finished in line " + getLine().getSeqNum());
        addTime(System.currentTimeMillis() - startTime);
    }
}
