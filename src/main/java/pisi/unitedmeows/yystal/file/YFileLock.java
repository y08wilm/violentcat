/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import pisi.unitedmeows.yystal.exception.YExManager;
import pisi.unitedmeows.yystal.exception.impl.YexIO;
import pisi.unitedmeows.yystal.file.YFile;

public class YFileLock {
    private File file;
    private FileLock lock;

    public YFileLock(File _file) {
        this.file = _file;
    }

    public YFileLock(YFile yFile) {
        this(yFile.file());
    }

    public boolean lock() {
        FileChannel channel;
        try {
            channel = new RandomAccessFile(this.file, "rw").getChannel();
        }
        catch (IOException ex) {
            YExManager.pop(new YexIO("Couldn't get the file channel " + this.file.getAbsolutePath(), new Object[0]));
            return false;
        }
        try {
            this.lock = channel.lock();
            return true;
        }
        catch (IOException e) {
            YExManager.pop(new YexIO("Couldn't lock the file " + this.file.getAbsolutePath(), new Object[0]));
            return false;
        }
    }

    public boolean free() {
        try {
            this.lock.close();
            return true;
        }
        catch (Exception ex) {
            YExManager.pop(new YexIO("Couldn't free the file " + this.file.getAbsolutePath(), new Object[0]));
            return false;
        }
    }
}

