package pt.sirs.StarDrive.production.domain;

import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.*;

public class Production {
    private ArrayList<Assembler> assemblers;
    private ArrayList<AssemblyLine> lines;
    private Logger logger;

    public Production(){
        assemblers = new ArrayList<Assembler>();
        lines = new ArrayList<AssemblyLine>();
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

    public void info(String message){
        this.logger.log(Level.INFO, message);
    }

}
