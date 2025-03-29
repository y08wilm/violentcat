/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.utils;

public class Vertex3f {
    public float x;
    public float y;
    public float z;

    public Vertex3f(float _x, float _y, float _z) {
        this.x = _x;
        this.y = _y;
        this.z = _z;
    }

    public float x() {
        return this.x;
    }

    public float y() {
        return this.y;
    }

    public float z() {
        return this.z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }
}

