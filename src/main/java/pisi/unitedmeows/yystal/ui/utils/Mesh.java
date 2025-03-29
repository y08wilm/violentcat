/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL15
 *  org.lwjgl.opengl.GL20
 *  org.lwjgl.opengl.GL30
 *  org.lwjgl.system.MemoryUtil
 */
package pisi.unitedmeows.yystal.ui.utils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;
import pisi.unitedmeows.yystal.ui.utils.Vertex3f;

public class Mesh {
    private Vertex3f[] vertices;
    private int[] indices;
    private int vao;
    private int pbo;
    private int ibo;

    public Mesh(Vertex3f[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
    }

    public void create() {
        this.vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray((int)this.vao);
        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat((int)(this.vertices.length * 3));
        float[] positionData = new float[this.vertices.length * 3];
        for (int i = 0; i < this.vertices.length; ++i) {
            positionData[i * 3] = this.vertices[i].x();
            positionData[i * 3 + 1] = this.vertices[i].y();
            positionData[i * 3 + 2] = this.vertices[i].z();
        }
        positionBuffer.put(positionData).flip();
        this.pbo = GL15.glGenBuffers();
        GL15.glBindBuffer((int)34962, (int)this.pbo);
        GL15.glBufferData((int)34962, (FloatBuffer)positionBuffer, (int)35044);
        GL20.glVertexAttribPointer((int)0, (int)3, (int)5126, (boolean)false, (int)0, (long)0L);
        GL15.glBindBuffer((int)34962, (int)0);
        IntBuffer indicesBuffer = MemoryUtil.memAllocInt((int)this.indices.length);
        indicesBuffer.put(this.indices).flip();
        this.ibo = GL15.glGenBuffers();
        GL15.glBindBuffer((int)34963, (int)this.ibo);
        GL15.glBufferData((int)34963, (IntBuffer)indicesBuffer, (int)35044);
        GL15.glBindBuffer((int)34963, (int)0);
    }

    public Vertex3f[] getVertices() {
        return this.vertices;
    }

    public int[] getIndices() {
        return this.indices;
    }

    public int getVAO() {
        return this.vao;
    }

    public int getPBO() {
        return this.pbo;
    }

    public int getIBO() {
        return this.ibo;
    }
}

