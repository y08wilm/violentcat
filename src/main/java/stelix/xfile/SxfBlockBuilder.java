package stelix.xfile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SxfBlockBuilder extends SxfBuildElement {
  protected boolean spaceAfter;
  
  protected SxfBuildElement owner;
  
  private List<Object> elements;
  
  protected SxfBlockBuilder(SxfBuildElement _owner) {
    this.elements = new ArrayList();
    this.owner = _owner;
    if (this.owner != null)
      this.style = this.owner.style; 
  }
  
  public static SxfBlockBuilder create() {
    return create(null);
  }
  
  public static SxfBlockBuilder create(SxfBuildElement _owner) {
    return new SxfBlockBuilder(_owner);
  }
  
  public SxfElementPack<SxfBlockBuilder, SxfBlockBuilder> createBlock() {
    return add(new SxfBlockBuilder(this));
  }
  
  public <X extends SxfBuildElement> SxfElementPack<X, SxfBlockBuilder> add(SxfBuildElement element) {
    SxfElementPack<X, SxfBlockBuilder> elementPack = new SxfElementPack<>((X)element, this);
    this.elements.add(elementPack);
    element.setOwner(this);
    return elementPack;
  }
  
  public SxfElementPack<Object, SxfBlockBuilder> add(String name, Object element) {
    SxfElementPack<Object, SxfBlockBuilder> elementPack = new SxfElementPack<>(element, this);
    this.elements.add(new Variable(name, elementPack));
    if (element instanceof SxfBuildElement)
      ((SxfBuildElement)element).setOwner(this); 
    return elementPack;
  }
  
  public <X extends SxfBuildElement> SxfElementPack<X, SxfBlockBuilder> variable(String name, X element) {
    SxfElementPack<?, SxfBlockBuilder> elementPack = new SxfElementPack<>(element, this);
    this.elements.add(new Variable(name, elementPack));
    element.setOwner(this);
    return (SxfElementPack)elementPack;
  }
  
  public <X> SxfElementPack<X, SxfBlockBuilder> variable(String name, X element) {
    SxfElementPack<?, SxfBlockBuilder> elementPack = new SxfElementPack<>(element, this);
    this.elements.add(new Variable(name, elementPack));
    if (element instanceof SxfBuildElement)
      ((SxfBuildElement)element).setOwner(this); 
    return (SxfElementPack)elementPack;
  }
  
  public void writeObject(int spaceCount, StringBuilder stringBuilder) {
    stringBuilder.append("{");
    Iterator<Object> elementIterator = this.elements.iterator();
    spaceCount++;
    boolean lastWasBlock = false;
    if (elementIterator.hasNext())
      while (true) {
        Object element = elementIterator.next();
        boolean hasNext = elementIterator.hasNext();
        if (this.style == WriteStyle.NORMAL) {
          if (this.spaceAfter && lastWasBlock)
            stringBuilder.append(System.lineSeparator()); 
          stringBuilder.append(System.lineSeparator());
          Commons.putSpace(stringBuilder, spaceCount);
        } else {
          stringBuilder.append(" ");
        } 
        if (element instanceof Variable) {
          Variable variable = (Variable)element;
          SxfElementPack<?, ?> sxfPack = (SxfElementPack<?, ?>)variable.value();
          Object value = sxfPack.down();
          if (value instanceof SxfBuildElement) {
            if (value instanceof SxfBlockBuilder) {
              if (this.style == WriteStyle.NORMAL) {
                lastWasBlock = true;
                if (sxfPack.comment() != null && !sxfPack.comment().isEmpty()) {
                  stringBuilder.append("$; ").append(sxfPack.comment()).append(" ;$").append(System.lineSeparator());
                  Commons.putSpace(stringBuilder, spaceCount);
                } 
              } 
              stringBuilder.append(Commons.clearName(variable.name())).append(" => ");
              ((SxfBlockBuilder)value).writeObject(spaceCount, stringBuilder);
            } else if (value instanceof SxfStructBuilder) {
              if (this.style == WriteStyle.NORMAL && 
                sxfPack.comment() != null && !sxfPack.comment().isEmpty()) {
                stringBuilder.append("$; ").append(sxfPack.comment()).append(" ;$").append(System.lineSeparator());
                Commons.putSpace(stringBuilder, spaceCount);
              } 
              stringBuilder.append(Commons.clearName(((Variable)element).name())).append(": ");
              ((SxfStructBuilder)value).writeObject(spaceCount, stringBuilder);
            } 
          } else {
            stringBuilder.append(Commons.clearName(((Variable)element).name())).append(": ");
            stringBuilder.append(Commons.writeVar(value));
            if (sxfPack.comment() != null && !sxfPack.comment().isEmpty())
              stringBuilder.append(" $; ").append(sxfPack.comment()).append(" ;$"); 
          } 
        } else {
          SxfElementPack<?, ?> elementPack = (SxfElementPack<?, ?>)element;
          SxfBuildElement sxfElement = (SxfBuildElement)elementPack.down();
          if (elementPack.comment() != null && !elementPack.comment().isEmpty() && this.style == WriteStyle.NORMAL) {
            stringBuilder.append("$; ").append(elementPack.comment()).append(" ;$");
            stringBuilder.append(System.lineSeparator());
            Commons.putSpace(stringBuilder, spaceCount);
          } 
          if (sxfElement instanceof SxfBlockBuilder) {
            SxfBlockBuilder downBlock = (SxfBlockBuilder)sxfElement;
            downBlock.writeObject(spaceCount, stringBuilder);
          } 
        } 
        if (hasNext)
          if (this.style == WriteStyle.INLINE) {
            stringBuilder.append(", ");
          } else {
            stringBuilder.append(",");
          }  
        if (!hasNext) {
          if (this.style == WriteStyle.NORMAL) {
            stringBuilder.append(System.lineSeparator());
            Commons.putSpace(stringBuilder, spaceCount - 1);
          } else {
            stringBuilder.append(" ");
          } 
          stringBuilder.append("}");
          break;
        } 
      }  
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    writeObject(0, stringBuilder);
    return stringBuilder.toString();
  }
  
  public SxfBlockBuilder setStyle(WriteStyle _style) {
    return (SxfBlockBuilder)super.setStyle(_style);
  }
  
  public SxfBlockBuilder setSpaceAfter(boolean _spaceAfter) {
    this.spaceAfter = _spaceAfter;
    return this;
  }
  
  protected <X extends SxfBuildElement> X build() {
    if (this.owner == null)
      return (X)this; 
    return (X)this.owner;
  }
  
  public SxfStructBuilder buildStruct() {
    return build();
  }
  
  public SxfBlockBuilder buildBlock() {
    return build();
  }
}
