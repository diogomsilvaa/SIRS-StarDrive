package pt.sirs.StarDrive.prodution.domain;

abstract class Assembler {
    private int id;
    private int productionRate;

    public Assembler(int _id){
        setId(_id);
    }

    private void setId(int _id) {
        this.id = _id;
    }

    public int getId() {
        return id;
    }

    public void set_productionRate(int _productionRate) {
        this.productionRate = _productionRate;
    }
    
    public int getProductionRate() {
        return productionRate;
    }

    abstract void assemble();
}
