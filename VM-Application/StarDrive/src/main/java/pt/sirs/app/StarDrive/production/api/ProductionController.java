package pt.sirs.app.StarDrive.production.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pt.sirs.app.StarDrive.production.ProductionService;
import pt.sirs.app.StarDrive.production.domain.Assembler;
import pt.sirs.app.StarDrive.production.domain.AssemblyLine;

@RestController
@RequestMapping("/production")
public class ProductionController {
    
    @Autowired
    ProductionService productionService;

    @PostMapping("/createLine")
    AssemblyLine createAssemblyLine(){
        AssemblyLine newLine = productionService.createAssemblyLine();
        return newLine;
    }

    @PostMapping("/createAssembler")
    Assembler createAssember(){
        Assembler newAssembler = productionService.createAssembler("eletronic");
        return newAssembler;
    }
}
