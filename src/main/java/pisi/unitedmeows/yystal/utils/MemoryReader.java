/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import pisi.unitedmeows.yystal.utils.ImageUtils;

public class MemoryReader
extends DataInputStream {
    private ByteArrayInputStream inputStream;

    public MemoryReader(InputStream stream) {
        super(stream);
    }

    public MemoryReader(byte[] data) {
        super(null);
        this.inputStream = new ByteArrayInputStream(data);
        this.in = this.inputStream;
    }

    public String readString() {
        try {
            return super.readUTF();
        }
        catch (IOException e) {
            return null;
        }
    }

    @Override
    public int read() {
        try {
            return super.read();
        }
        catch (Exception ex) {
            return -1;
        }
    }

    public BufferedImage readImage() {
        try {
            int length = this.readInt();
            byte[] bytes = new byte[length];
            this.read(bytes);
            return ImageUtils.toBufferedImage(bytes);
        }
        catch (IOException e) {
            return null;
        }
    }

    public byte[] getBytes() {
        byte[] array = new byte[this.inputStream.available()];
        try {
            this.inputStream.read(array);
            return array;
        }
        catch (IOException e) {
            return null;
        }
    }
}

