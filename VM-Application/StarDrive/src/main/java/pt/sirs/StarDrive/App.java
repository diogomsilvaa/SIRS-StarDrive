package pt.sirs.StarDrive;

import pt.sirs.StarDrive.production.domain.AssemblyLine;
import pt.sirs.StarDrive.production.ProductionService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ProductionService prod = new ProductionService();
        AssemblyLine line = prod.createAssemblyLine();
        prod.addAssembler("eletronic", line);
        prod.addAssembler("batteries", line);
        prod.addAssembler("chasis", line);
        prod.addAssembler("painter", line);
        line.startAssembling();
    }
}
