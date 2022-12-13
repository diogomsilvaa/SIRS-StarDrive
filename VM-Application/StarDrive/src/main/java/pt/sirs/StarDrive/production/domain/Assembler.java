package pt.sirs.StarDrive.production.domain;


abstract class Assembler {
    private String id;
    private int productionRate;
    private AssemblyLine line;

    public Assembler(String _id, AssemblyLine _line){
        setId(_id);
        setLine(_line);
        
    }

    private void setId(String _id) {
        this.id = _id;
    }

    public String getId() {
        return id;
    }

    public void set_productionRate(int _productionRate) {
        this.productionRate = _productionRate;
    }
    
    public int getProductionRate() {
        return productionRate;
    }

    public void info(String message){
        this.line.info(message);
    }

    public void setLine(AssemblyLine line) {
        this.line = line;
    }

    public AssemblyLine getLine() {
        return line;
    }

    abstract void assemble();
}
