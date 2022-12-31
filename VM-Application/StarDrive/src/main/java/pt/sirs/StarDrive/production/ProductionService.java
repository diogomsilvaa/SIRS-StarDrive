package pt.sirs.StarDrive.production;

import java.util.ArrayList;
import java.util.UUID;
import org.bson.Document;
import com.mongodb.client.MongoCollection;

import pt.sirs.StarDrive.Database;
import pt.sirs.StarDrive.production.domain.*;

public class ProductionService {

    private ArrayList<Assembler> assemblers;
    private ArrayList<AssemblyLine> lines;
    private MongoCollection<Document> repository;

    
    public ProductionService(){
        assemblers = new ArrayList<Assembler>();
        lines = new ArrayList<AssemblyLine>();
        repository = Database.productionCollection;
    }

    public AssemblyLine createAssemblyLine(){
        AssemblyLine line = new AssemblyLine(this);
        lines.add(line);
        return line;
    }

    public void addAssembler(String assemblerType, AssemblyLine line){
        Assembler newAssembler;
        switch (assemblerType) {
            case "eletronic":
                newAssembler = new EletronicsAssembler(UUID.randomUUID().toString(), line);
                break;
            case "batteries":
                newAssembler = new BatteryAssembler(UUID.randomUUID().toString(), line);
                break;
            case "chasis":
                newAssembler = new ChassisAssembler(UUID.randomUUID().toString(), line);
                break;
            case "painter":
                newAssembler = new Painter(UUID.randomUUID().toString(), line);
                break;
            default:
                return;
        }
        line.addAssembler(newAssembler);
        assemblers.add(newAssembler);
    }
}
