package pt.sirs.StarDrive.prodution.domain;

import java.util.Date;

public class AssemblyLine {
    private float productionRate;
    private boolean working;
    private Date startDate;
    private Date endDate;
    private int seqNum;

    public AssemblyLine(){
        // ir buscar o sec num à base dados
        // guardar esta info na base de dados
    }

    public int startAssembling(){
        return seqNum;
    }
}
