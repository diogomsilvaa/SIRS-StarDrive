package pt.sirs.app.StarDrive.production.domain;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import pt.sirs.app.StarDrive.production.ProductionService;

@Document(collection = "lines")
public class AssemblyLine {
    private float productionRate;
    private boolean onProduction;
    private Date startDate;
    private Date endDate;
    private static int seqNum = 0;
    private String id;
    private ArrayList<Assembler> assemblers;
    

    public AssemblyLine(ProductionService _prod){
        assemblers = new ArrayList<Assembler>();
        setId("L" + seqNum);
        seqNum++;
        // ir buscar o sec num à base dados
        // guardar esta info na base de dados
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ArrayList<String> getAssemblersIDs() {
        ArrayList<String> assemblersIDs = new ArrayList<>();
        assemblers.forEach(e -> assemblersIDs.add(e.getId()));;
        return assemblersIDs;
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
