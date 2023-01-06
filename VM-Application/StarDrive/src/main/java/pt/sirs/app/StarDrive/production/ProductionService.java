package pt.sirs.app.StarDrive.production;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Line;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.sirs.app.StarDrive.production.domain.*;
import pt.sirs.app.StarDrive.production.repo.LinesRepository;

@Service
public class ProductionService {


    @Autowired
    private LinesRepository linesRepository;

    private ArrayList<Assembler> assemblers;

    public static int assemblersNum = 0;
    public static int linesNum = 0;

    
    public ProductionService(){
        assemblers = new ArrayList<Assembler>();
    }

    public AssemblyLine createAssemblyLine(){
        AssemblyLine line = new AssemblyLine("L" + linesNum);
        linesNum++;
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
                throw new IllegalArgumentException("Invalid assembler type");
        }
        ProductionService.assemblersNum++;
        assemblers.add(newAssembler);
        return newAssembler;
    }

    public AssemblyLine addAssembler(String assemblerID, String lineID){
        Assembler assembler = null;
        for (Assembler a: assemblers) {
            System.out.println(a.getId());
            System.out.println(assemblerID);
            if(a.getId().equals(assemblerID)){
                System.out.println("Found assembler");
                assembler = a;
                break;
            }
        }

        AssemblyLine line = linesRepository.findById(lineID).get();
        System.out.println(line);

        if(assembler == null || line == null) throw new IllegalArgumentException("Assembler or line does not exist");

        line.addAssembler(assembler);
        assembler.setLineId(line.getId());
        linesRepository.save(line);

        return line;
    }

    public AssemblyLine startAssembling(String lineID){
        AssemblyLine line = linesRepository.findById(lineID).get();
        if(line == null) throw new IllegalArgumentException("Line does not exist");
        line.setOnProduction(true);
        line.setStartDate(LocalDateTime.now().toString());
        ArrayList<String> assemblersIDs = line.getAssemblersIDs();

        for(Assembler assembler : assemblers){
            if(assemblersIDs.contains(assembler.getId())){
                assembler.assemble();
            }
        }
        linesRepository.save(line);
        return line;
    }

    public AssemblyLine updateAssemblersInfo(String lineID){
        AssemblyLine line = linesRepository.findById(lineID).get();
        if(line == null) throw new IllegalArgumentException("Line does not exist");
        ArrayList<String> assemblersIDs = line.getAssemblersIDs();
        ArrayList<Assembler> assemblersInLine = new ArrayList<Assembler>();
        double productionRate = 0;
        for( Assembler assembler : assemblers){
            if(assemblersIDs.contains(assembler.getId())){
                assembler.updateInfo();
                assemblersInLine.add(assembler);
                productionRate += assembler.getProductionRate();
            }
        }
        line.setProductionRate((float) productionRate/assemblersIDs.size());
        line.setAssemblers(assemblersInLine);
        
        linesRepository.save(line);
        return line;
    }

    public List<AssemblyLine> getAssemblyLines(){
        return linesRepository.findAll();
    }

    public List<Assembler> getAssemblers(){
        return assemblers;
    }
}
