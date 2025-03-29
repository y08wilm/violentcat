package stelix.xfile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SxfBlock implements ISxfObject {
  private LinkedHashMap<String, Object> variables = new LinkedHashMap<>();
  
  private List<ISxfObject> unmanagedObjects = new ArrayList<>();
  
  public LinkedHashMap<String, Object> variables() {
    return this.variables;
  }
  
  public <X extends SxfStruct> X unmanagedAsStruct(int index) {
    return (X)this.unmanagedObjects.get(index);
  }
  
  public <X extends SxfBlock> X unmanagedAsBlock(int index) {
    return (X)this.unmanagedObjects.get(index);
  }
  
  public <X> X variable(String name) {
    return (X)this.variables.getOrDefault(name, null);
  }
  
  public <X extends SxfBlock> X variableAsBlock(String name) {
    return (X)variable(name);
  }
  
  public <X extends SxfStruct> X variableAsStruct(String name) {
    return (X)variable(name);
  }
  
  public List<ISxfObject> unmanagedObjects() {
    return this.unmanagedObjects;
  }
}
