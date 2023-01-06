package pt.sirs.app.StarDrive.production.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pt.sirs.app.StarDrive.auth.AuthService;
import pt.sirs.app.StarDrive.production.ProductionService;
import pt.sirs.app.StarDrive.production.domain.Assembler;
import pt.sirs.app.StarDrive.production.domain.AssemblyLine;
import pt.sirs.app.StarDrive.user.UserService;
import pt.sirs.app.StarDrive.user.domain.User;

@RestController
@RequestMapping("/production")
public class ProductionController {
    
    @Autowired
    UserService userService;

    @Autowired
    ProductionService productionService;
    
    @CrossOrigin
    @PostMapping("/createLine")
    AssemblyLine createAssemblyLine(@RequestBody Map<String, String> body){
        AuthService auth = new AuthService(body.get("token"));
        try {
            auth.verifyToken();
        } catch (TimeoutException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(408));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
        User user = userService.getUser(auth.getId());
        if(user.getRole() != User.Role.ENGINEER) throw new ResponseStatusException(HttpStatusCode.valueOf(403));
        AssemblyLine newLine = productionService.createAssemblyLine();
        return newLine;
    }

    @CrossOrigin
    @PostMapping("/addAssembler")
    AssemblyLine addAssembler(@RequestBody Map<String, String> body){
        AuthService auth = new AuthService(body.get("token"));
        try {
            auth.verifyToken();
        } catch (TimeoutException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(408));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
        User user = userService.getUser(auth.getId());
        if(user.getRole() != User.Role.ENGINEER) throw new ResponseStatusException(HttpStatusCode.valueOf(403));

        AssemblyLine line = productionService.addAssembler(body.get("assemblerId"), body.get("lineId"));
        return line;
    }

    @CrossOrigin
    @PostMapping("/createAssembler")
    Assembler createAssembler(@RequestBody Map<String, String> body){
        AuthService auth = new AuthService(body.get("token"));
        try {
            auth.verifyToken();
        } catch (TimeoutException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(408));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
        User user = userService.getUser(auth.getId());
        if(user.getRole() != User.Role.ENGINEER) throw new ResponseStatusException(HttpStatusCode.valueOf(403));

        Assembler newAssembler = productionService.createAssembler(body.get("type"));
        return newAssembler;
    }

    @CrossOrigin
    @PostMapping("/startLine")
    AssemblyLine startLine(@RequestBody Map<String, String> body){

        AuthService auth = new AuthService(body.get("token"));
        try {
            auth.verifyToken();
        } catch (TimeoutException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(408));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
        User user = userService.getUser(auth.getId());
        if(user.getRole() != User.Role.ENGINEER) throw new ResponseStatusException(HttpStatusCode.valueOf(403));

        AssemblyLine line = productionService.startAssembling(body.get("lineId"));
        return line;
    }

    @CrossOrigin
    @PutMapping("/updateLine")
    AssemblyLine updateLine(@RequestBody Map<String, String> body){

        AssemblyLine line = productionService.updateAssemblersInfo(body.get("lineId"));

        return line;
    }

    @CrossOrigin
    @GetMapping("/getLines")
    List<AssemblyLine> getAssemblyLines(){

        List<AssemblyLine> list = new ArrayList<AssemblyLine>();
        
        for(AssemblyLine line : productionService.getAssemblyLines()){
            if(line.isOnProduction()){
                
                list.add(productionService.updateAssemblersInfo(line.getId()));
            }
        }
        return list;
    }

    @CrossOrigin
    @PostMapping("/getAllLines")
    List<AssemblyLine> getAllAssemblyLines(@RequestBody Map<String, String> body){
        AuthService auth = new AuthService(body.get("token"));
        try {
            auth.verifyToken();
        } catch (TimeoutException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(408));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatusCode.valueOf(500));
        }
        User user = userService.getUser(auth.getId());
        if(user.getRole() != User.Role.ENGINEER) throw new ResponseStatusException(HttpStatusCode.valueOf(403));

        return productionService.getAssemblyLines();
    }

}