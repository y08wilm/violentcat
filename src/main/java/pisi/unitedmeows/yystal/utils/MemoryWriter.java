/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import pisi.unitedmeows.yystal.utils.ImageUtils;

public class MemoryWriter
extends DataOutputStream {
    private ByteArrayOutputStream outputStream;

    public MemoryWriter(OutputStream stream) {
        super(stream);
        this.out = stream;
    }

    public MemoryWriter() {
        super(null);
        this.outputStream = new ByteArrayOutputStream();
        this.out = this.outputStream;
    }

    public byte[] getBytes() {
        return this.outputStream.toByteArray();
    }

    public void writeImage(BufferedImage image, ImageUtils.ImageType type) {
        byte[] data = ImageUtils.toByteArray(image, type);
        try {
            this.write(data.length);
            this.write(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

