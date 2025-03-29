/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL20
 */
package pisi.unitedmeows.yystal.ui.utils;

import org.lwjgl.opengl.GL20;

public class YShader {
    private int vertexShader;
    private int fragmentShader;
    private int program;

    public boolean load(String vsSource, String fsSource) {
        this.vertexShader = GL20.glCreateShader((int)35633);
        GL20.glShaderSource((int)this.vertexShader, (CharSequence)vsSource);
        GL20.glCompileShader((int)this.vertexShader);
        int success = GL20.glGetShaderi((int)this.vertexShader, (int)35713);
        if (success == 0) {
            return false;
        }
        this.fragmentShader = GL20.glCreateShader((int)35632);
        GL20.glShaderSource((int)this.fragmentShader, (CharSequence)fsSource);
        GL20.glCompileShader((int)this.fragmentShader);
        success = GL20.glGetShaderi((int)this.fragmentShader, (int)35713);
        if (success == 0) {
            return false;
        }
        this.program = GL20.glCreateProgram();
        GL20.glAttachShader((int)this.program, (int)this.vertexShader);
        GL20.glAttachShader((int)this.program, (int)this.fragmentShader);
        GL20.glLinkProgram((int)this.program);
        success = GL20.glGetProgrami((int)this.program, (int)35714);
        if (success == 0) {
            return false;
        }
        GL20.glValidateProgram((int)this.program);
        success = GL20.glGetProgrami((int)this.program, (int)35715);
        return success != 0;
    }

    public void destroy() {
        GL20.glDetachShader((int)this.program, (int)this.vertexShader);
        GL20.glDetachShader((int)this.program, (int)this.fragmentShader);
        GL20.glDeleteShader((int)this.vertexShader);
        GL20.glDeleteShader((int)this.fragmentShader);
        GL20.glDeleteShader((int)this.program);
    }

    public void bind() {
        GL20.glUseProgram((int)this.program);
    }
}

