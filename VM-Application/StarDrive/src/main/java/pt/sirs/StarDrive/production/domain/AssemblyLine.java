package pt.sirs.StarDrive.production.domain;

import java.util.Date;

public class AssemblyLine {
    private float productionRate;
    private boolean onProduction;
    private Date startDate;
    private Date endDate;
    private int seqNum;
    private Production prod;

    public AssemblyLine(Production _prod){
        setProd(_prod);
        // ir buscar o sec num à base dados
        // guardar esta info na base de dados
    }

    public int startAssembling(){
        return seqNum;
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

    public void setProd(Production prod) {
        this.prod = prod;
    }

    public Production getProd() {
        return prod;
    }

    public void info(String message){
        this.prod.info(message);
    }


}
