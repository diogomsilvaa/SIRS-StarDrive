package pt.sirs.app.StarDrive.production;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.sirs.app.StarDrive.production.domain.*;
import pt.sirs.app.StarDrive.production.repo.AssemblersRepository;
import pt.sirs.app.StarDrive.production.repo.LinesRepository;

@Service
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
        AssemblyLine line = new AssemblyLine();
        lines.add(line);
        linesRepository.save(line);
        return line;
    }

    public Assembler createAssembler(String assemblerType){
        Assembler newAssembler;
        switch (assemblerType) {
            case "eletronic":
                newAssembler = new EletronicsAssembler(ProductionService.assemblersNum);
                break;
            case "batteries":
                newAssembler = new BatteryAssembler(ProductionService.assemblersNum);
                break;
            case "chasis":
                newAssembler = new ChassisAssembler(ProductionService.assemblersNum);
                break;
            case "painter":
                newAssembler = new Painter(ProductionService.assemblersNum);
                break;
            default:
                return null;
        }
        ProductionService.assemblersNum++;
        assemblersRepository.save(newAssembler);
        assemblers.add(newAssembler);
        return newAssembler;
    }

    public void addAssembler(String assemblerID, AssemblyLine line){
        Assembler assembler = null;
        for (Assembler a : assemblers){
            if (a.getId() == assemblerID){
                line.addAssembler(a);
                assembler = a;
                break;
            }
        }
        if (assembler == null) return;
        line.addAssembler(assembler);
    }
}
