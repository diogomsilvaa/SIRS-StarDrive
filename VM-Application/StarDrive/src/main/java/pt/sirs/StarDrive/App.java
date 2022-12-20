package pt.sirs.StarDrive;

import pt.sirs.StarDrive.production.domain.AssemblyLine;
import pt.sirs.StarDrive.production.domain.Production;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Production prod = new Production();
        AssemblyLine line = prod.createAssemblyLine();
        prod.addAssembler("eletronic", line);
        prod.addAssembler("batteries", line);
        prod.addAssembler("chasis", line);
        prod.addAssembler("painter", line);
        line.startAssembling();
    }
}
