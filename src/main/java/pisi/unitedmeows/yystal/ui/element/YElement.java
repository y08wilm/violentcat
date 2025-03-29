/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.element;

import java.util.ArrayList;
import java.util.List;
import pisi.unitedmeows.yystal.clazz.event;
import pisi.unitedmeows.yystal.clazz.prop;
import pisi.unitedmeows.yystal.ui.YWindow;
import pisi.unitedmeows.yystal.ui.animation.Transition;
import pisi.unitedmeows.yystal.ui.element.IBackground;
import pisi.unitedmeows.yystal.ui.element.YContainer;
import pisi.unitedmeows.yystal.ui.events.MouseEvent;
import pisi.unitedmeows.yystal.ui.utils.Vertex2f;
import pisi.unitedmeows.yystal.ui.utils.YOrigin;

public abstract class YElement
implements IBackground {
    protected Vertex2f location = new Vertex2f(0.0f, 0.0f);
    private YOrigin origin = YOrigin.TOP_LEFT;
    protected Vertex2f size = new Vertex2f(0.0f, 0.0f);
    protected boolean show = true;
    public event<MouseEvent> mouseEvent = new event();
    public prop<Integer> zLevel = new prop<Integer>(1);
    private boolean focused;
    List<Transition> transitions = new ArrayList<Transition>(0);
    protected YContainer container;
    public final prop<YWindow> currentWindow = new prop<YWindow>(null){

        @Override
        public YWindow get() {
            if (this.value == null) {
                YElement owner = YElement.this.instance();
                while (owner.container != null) {
                    owner = owner.container;
                }
                if (owner instanceof YWindow) {
                    this.value = (YWindow)owner;
                }
            }
            return (YWindow)this.value;
        }

        @Override
        @Deprecated
        public void set(YWindow newValue) {
        }
    };
    public final prop<Boolean> isMouseOver = new prop<Boolean>(){

        @Override
        public Boolean get() {
            YWindow window = YElement.this.currentWindow.get();
            if (window != null) {
                return YElement.this.isMouseOver(window.mouseX(), window.mouseY());
            }
            return false;
        }

        @Override
        @Deprecated
        public void set(Boolean newValue) {
        }
    };

    public void setup() {
    }

    public void tick() {
    }

    @Deprecated
    public void _mouseClick(int mouseButton) {
    }

    @Deprecated
    public void _mouseRelease(int mouseButton) {
    }

    @Deprecated
    public void _keyPress(int keyCode) {
    }

    @Override
    public abstract void draw();

    public boolean isMouseOver(float mouseX, float mouseY) {
        return mouseX >= this.renderX() && mouseX <= this.renderX() + this.size.x() && mouseY >= this.renderY() && mouseY <= this.renderY() + this.size.y();
    }

    public Vertex2f location() {
        return this.location;
    }

    public float renderX() {
        return this.container.renderX() + this.location.x;
    }

    public float renderY() {
        return this.container.renderY() + this.location.y;
    }

    public YElement container(YContainer _container) {
        this.container = _container;
        return this;
    }

    public void registerTransition(Transition transition) {
        this.transitions.add(transition);
    }

    public void unregisterTransition(Transition transition) {
        this.transitions.remove(transition);
    }

    public YContainer container() {
        return this.container;
    }

    public YElement instance() {
        return this;
    }

    public Vertex2f size() {
        return this.size;
    }

    public YElement size(float _x, float _y) {
        this.size.setX(_x);
        this.size.setY(_y);
        return this;
    }

    public YElement size(Vertex2f _size) {
        this.size = _size;
        return this;
    }

    public void show() {
        this.show = true;
    }

    public void hide() {
        this.show = false;
    }

    public boolean isShown() {
        return this.show;
    }

    public void dock(YElement otherElement) {
        this.size = otherElement.size;
        this.location = otherElement.location;
    }

    public boolean focused() {
        return this.focused;
    }

    public YElement focus() {
        this.focused = true;
        return this;
    }

    public YElement location(Vertex2f _location) {
        this.location = _location;
        return this;
    }

    public YElement origin(YOrigin _origin) {
        this.origin = _origin;
        return this;
    }

    public List<Transition> transitions() {
        return this.transitions;
    }
}

