package pt.sirs.StarDrive.production.domain;

import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.*;

public class Production {
    ArrayList<Assembler> assemblers;
    ArrayList<AssemblyLine> lines;
    Logger logger;

    public Production(){
        assemblers = new ArrayList<Assembler>();
        logger = Logger.getLogger(Assembler.class.getName());
        logger.setLevel(Level.INFO);
    }

    public AssemblyLine createAssemblyLine(){
        AssemblyLine line = new AssemblyLine(this);
        lines.add(line);
        return line;
    }

    public void addAssembler(String assemblerType, AssemblyLine line){
        Assembler newAssembler;
        switch (assemblerType) {
            case "painter":
                newAssembler = new Painter(UUID.randomUUID().toString(), line);
                break;
            case "eletronic":
                newAssembler = new EletronicsAssembler(UUID.randomUUID().toString(), line);
                break;
            case "batteries":
                newAssembler = new BatteryAssembler(UUID.randomUUID().toString(), line);
                break;
            case "chasis":
                newAssembler = new ChassisAssembler(UUID.randomUUID().toString(), line);
                break;
        
            default:
                return;
        }
        assemblers.add(newAssembler);
    }

    public void info(String message){
        this.logger.log(Level.INFO, message);
    }

}
