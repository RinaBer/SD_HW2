package managerVertex;

public class ManagerVertex {
    private int priority;
    private int disk;
    private int memory;
    private int cpu;
    private String name;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    //TODO: implement
    public boolean hasEnoughResources() {
        return true;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getDisk() {
        return disk;
    }

    public void setDisk(int disk) {
        this.disk = disk;
    }

    public int getCpu() {
        return cpu;
    }

    public String getName() {
        return name;
    }


//    private String _name;
//    private FileOrAssignment _type;
//
//
//    public ManagerVertex(String name, FileOrAssignment type) {
//        _name = name;
//        _type = type;
//    }
//
//    public String GetName() {
//        return _name;
//    }
//
//    public void SetName(String _name) {
//        this._name = _name;
//    }
//
//    public FileOrAssignment GetType() {
//        return _type;
//    }
//
//    public void SetType(FileOrAssignment _type) {
//        this._type = _type;
//    }
//
//    @Override
//    public boolean equals(Object o){
//        if (this == o){
//            return true;
//        }
//        if (!(o instanceof ManagerVertex)) {
//            return false;
//        }
//        ManagerVertex other = (ManagerVertex)o;
//        return this._name.equals(other._name);
//    }
}
