package Items;

public abstract class Item implements BurnAble, HangAble, LayAble, StayAble{
    private Type type;
    private String name;
    private double x;
    private double y;
    private double z;
    private String location;
    private final String[] NamesOfItems = new String[]{"дрова", "спички", "одежда", "стол", "камин"};

    Item(String name, double x, double y, double z, Type type, String location)
    {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
        this.location = location;

    }
    public void setType(Type type){
        for(String name: NamesOfItems){
            if (this.getName().equals(name) && (name.equals("спички") || name.equals("дрова"))){
                this.type = Type.FLAMMABLE;
            } else if (this.getName().equals(name)) {
                this.type = Type.COMMON;
            }
        }
    }
    public void setLocation(String location){

    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Type getType() {
        return type;
    }
}
