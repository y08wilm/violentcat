/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking.simple.server.pack;

import java.io.IOException;
import pisi.unitedmeows.yystal.clazz.HookClass;
import pisi.unitedmeows.yystal.networking.simple.server.pack.YSignal;
import pisi.unitedmeows.yystal.utils.MemoryReader;
import pisi.unitedmeows.yystal.utils.MemoryWriter;

public class YSignalBuilder
extends HookClass<MemoryWriter> {
    public YSignalBuilder() {
        this.hooked = new MemoryWriter();
    }

    public YSignalBuilder(MemoryWriter memoryWriter) {
        this.hooked = memoryWriter;
    }

    public static YSignalBuilder newSignal() {
        return YSignalBuilder.builder();
    }

    public static YSignalBuilder newSignal(MemoryReader memoryReader) {
        return YSignalBuilder.builder(memoryReader);
    }

    public static YSignalBuilder newSignal(MemoryWriter memoryWriter) {
        return YSignalBuilder.builder(memoryWriter);
    }

    public YSignalBuilder write(int data) {
        try {
            ((MemoryWriter)this.hooked).writeInt(data);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return this;
    }

    public YSignalBuilder write(String data) {
        try {
            ((MemoryWriter)this.hooked).writeUTF(data);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return this;
    }

    public YSignalBuilder write(float data) {
        try {
            ((MemoryWriter)this.hooked).writeFloat(data);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return this;
    }

    public YSignalBuilder write(byte data) {
        try {
            ((MemoryWriter)this.hooked).writeByte(data);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return this;
    }

    public YSignalBuilder write(char data) {
        try {
            ((MemoryWriter)this.hooked).writeChar(data);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return this;
    }

    public YSignalBuilder write(short data) {
        try {
            ((MemoryWriter)this.hooked).writeShort(data);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return this;
    }

    public YSignalBuilder write(boolean data) {
        try {
            ((MemoryWriter)this.hooked).writeBoolean(data);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return this;
    }

    public YSignalBuilder write(byte[] data) {
        try {
            ((MemoryWriter)this.hooked).write(data);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return this;
    }

    public YSignalBuilder write(double data) {
        try {
            ((MemoryWriter)this.hooked).writeDouble(data);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return this;
    }

    public static YSignalBuilder builder() {
        return new YSignalBuilder();
    }

    public static YSignalBuilder builder(MemoryReader memoryReader) {
        return new YSignalBuilder().write(memoryReader.getBytes());
    }

    public static YSignalBuilder builder(MemoryWriter memoryWriter) {
        return new YSignalBuilder().write(memoryWriter.getBytes());
    }

    public YSignal build() {
        return new YSignal(new MemoryReader(((MemoryWriter)this.getHooked()).getBytes()));
    }

    public byte[] buildBytes() {
        return ((MemoryWriter)this.getHooked()).getBytes();
    }
}

