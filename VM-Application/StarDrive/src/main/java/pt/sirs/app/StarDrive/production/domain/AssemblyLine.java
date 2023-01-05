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

    private ArrayList<String> assemblersIDs;

    @Transient
    private ArrayList<Assembler> assemblers;
    

    public AssemblyLine(){
        assemblersIDs = new ArrayList<String>();
        setId("L" + seqNum);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        seqNum++;
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
    
    public void setOnProduction(boolean onProduction) {
        this.onProduction = onProduction;
    }

    public boolean isOnProduction() {
        return onProduction;
    }

    public void addAssembler(Assembler assembler){
        assemblersIDs.add(assembler.getId());
    }

    public void setAssemblers(ArrayList<Assembler> assemblers) {
        this.assemblers = assemblers;
    }

    public ArrayList<Assembler> getAssemblers() {
        return assemblers;
    }

    public ArrayList<String> getAssemblersIDs() {
        return assemblersIDs;
    }

}
