package pt.sirs.StarDrive.production.domain;

public class EletronicsAssembler extends Assembler{
    public EletronicsAssembler(String _id, AssemblyLine _line){
        super(_id, _line);
        info("Eletronics Assembler created and added to assembly line " + getLine().getSeqNum());

    }

    @Override
    void assemble() {
        
    }
}
