package pt.sirs.app.StarDrive.production.domain;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lines")
public class AssemblyLine {

    @Id
    private String id;

    private float productionRate;
    private boolean onProduction;
    private String startDate = null;
    private String endDate = null;

    @Transient
    private static int seqNum = 0;

    @Transient
    private ArrayList<Assembler> assemblers;
    

    public AssemblyLine(){
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
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
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
