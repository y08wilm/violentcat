/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.element;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import pisi.unitedmeows.yystal.clazz.prop;
import pisi.unitedmeows.yystal.ui.element.IBackground;
import pisi.unitedmeows.yystal.ui.element.YElement;
import pisi.unitedmeows.yystal.ui.element.simple.YBackgroundColor;
import pisi.unitedmeows.yystal.ui.utils.Vertex2f;
import pisi.unitedmeows.yystal.ui.utils.YOrigin;

public class YContainer
extends YElement {
    protected YOrigin origin;
    private final List<YElement> elements;
    private YContainer instance;
    public prop<IBackground> background = new prop<IBackground>((IBackground)new YBackgroundColor(Color.WHITE)){

        @Override
        public void set(IBackground newValue) {
            super.set(newValue);
            if (newValue instanceof YElement) {
                ((YElement)YContainer.this.background.get()).container(YContainer.this.instance);
                ((YElement)YContainer.this.background.get()).setup();
            }
        }
    };

    public YContainer(Vertex2f _location, Vertex2f _size, YOrigin _origin) {
        this.location = _location;
        this.size = _size;
        this.origin = _origin;
        this.elements = new ArrayList<YElement>();
        this.instance = this;
    }

    public YContainer(Vertex2f _location, Vertex2f _size) {
        this(_location, _size, YOrigin.TOP_LEFT);
    }

    @Override
    public void draw() {
        this.background.get().draw();
        for (YElement element : this.elements) {
            if (!element.isShown()) continue;
            element.draw();
        }
    }

    @Override
    public boolean isMouseOver(float mouseX, float mouseY) {
        return false;
    }

    @Override
    public void setup() {
        if (this.background.get() instanceof YElement) {
            ((YElement)this.background.get()).container(this);
        }
        super.setup();
    }

    public YOrigin origin() {
        return this.origin;
    }

    public void addElement(YElement element) {
        element.container(this);
        element.setup();
        this.elements.add(element);
        this.elements.sort(Comparator.comparingInt(o -> o.zLevel.get()));
        element.show();
    }

    @Override
    public Vertex2f location() {
        return this.location;
    }

    public List<YElement> elements() {
        return this.elements;
    }

    public void setLocation(Vertex2f location) {
        this.location = location;
    }

    public void setOrigin(YOrigin origin) {
        this.origin = origin;
    }
}

