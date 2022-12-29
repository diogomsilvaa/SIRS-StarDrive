package pt.sirs.StarDrive.production.domain;

import java.util.ArrayList;
import java.util.Date;

import pt.sirs.StarDrive.production.ProductionService;

public class AssemblyLine {
    private float productionRate;
    private boolean onProduction;
    private Date startDate;
    private Date endDate;
    private int seqNum;
    private ArrayList<Assembler> assemblers;
    

    public AssemblyLine(ProductionService _prod){
        assemblers = new ArrayList<Assembler>();
        // ir buscar o sec num à base dados
        // guardar esta info na base de dados
    }

    public float getProductionRate() {
        return productionRate;
    }
    public void setProductionRate(float productionRate) {
        this.productionRate = productionRate;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public int getSeqNum() {
        return seqNum;
    }
    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }
    public boolean onProduction(){
        return this.onProduction;
    }
    public void setStatus(boolean status){
        this.onProduction = status;
    }

    public void addAssembler(Assembler assembler){
        assemblers.add(assembler);
    }

    public ArrayList<Assembler> getAssemblers() {
        return assemblers;
    }

    public int startAssembling(){
        ArrayList<Assembler> assemblers = getAssemblers();
        for(Assembler assembler : assemblers){
            assembler.assemble();
        }
        return seqNum;
    }

    @Override
    public String toString() {

        String text = "Status: " + (onProduction?"Producing":"Stopped") + "\n";
        text += "Assemblers: \n";
        ArrayList<Assembler> assemblers = getAssemblers();
        for(Assembler assembler : assemblers){
            text += " -> " + assembler.toString(); 
        }

        return text;
    }
}
