package pt.sirs.app.StarDrive.production;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;


import pt.sirs.app.StarDrive.production.domain.*;
import pt.sirs.app.StarDrive.production.repository.AssemblersRepository;
import pt.sirs.app.StarDrive.production.repository.LinesRepository;

public class ProductionService {

    @Autowired
    private AssemblersRepository assemblersRepository;

    @Autowired
    private LinesRepository linesRepository;

    private ArrayList<Assembler> assemblers;
    private ArrayList<AssemblyLine> lines;

    public static int assemblersNum = 0;

    
    public ProductionService(){
        assemblers = new ArrayList<Assembler>();
        lines = new ArrayList<AssemblyLine>();
    }

    public AssemblyLine createAssemblyLine(){
        AssemblyLine line = new AssemblyLine(this);
        lines.add(line);
        linesRepository.save(line);
        return line;
    }

    public void addAssembler(String assemblerType, AssemblyLine line){
        Assembler newAssembler;
        switch (assemblerType) {
            case "eletronic":
                newAssembler = new EletronicsAssembler(ProductionService.assemblersNum, line);
                break;
            case "batteries":
                newAssembler = new BatteryAssembler(ProductionService.assemblersNum, line);
                break;
            case "chasis":
                newAssembler = new ChassisAssembler(ProductionService.assemblersNum, line);
                break;
            case "painter":
                newAssembler = new Painter(ProductionService.assemblersNum, line);
                break;
            default:
                return;
        }
        ProductionService.assemblersNum++;
        line.addAssembler(newAssembler);
        assemblersRepository.save(newAssembler);
        linesRepository.findById(line.getId()).se
        assemblers.add(newAssembler);
    }
}
