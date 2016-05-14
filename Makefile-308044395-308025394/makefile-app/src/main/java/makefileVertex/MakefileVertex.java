package makefileVertex;

public class MakefileVertex {
    private String _name;
    private FileOrAssignment _type;


    public MakefileVertex(String name, FileOrAssignment type) {
        _name = name;
        _type = type;
    }

    public String GetName() {
        return _name;
    }

    public void SetName(String _name) {
        this._name = _name;
    }

    public FileOrAssignment GetType() {
        return _type;
    }

    public void SetType(FileOrAssignment _type) {
        this._type = _type;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (!(o instanceof MakefileVertex)) {
            return false;
        }
        MakefileVertex other = (MakefileVertex)o;
        return this._name.equals(other._name);
    }
}
