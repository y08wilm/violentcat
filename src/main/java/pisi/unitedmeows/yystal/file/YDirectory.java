/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import pisi.unitedmeows.yystal.file.YFile;

public class YDirectory {
    private File file;

    public YDirectory(File _file) {
        this.file = _file;
    }

    public YDirectory(String _path) {
        this(new File(_path));
    }

    public YDirectory(String parent, String fileName) {
        this(new File(parent, fileName));
    }

    public YDirectory parent() {
        return new YDirectory(this.file.getParentFile());
    }

    public List<YFile> listFiles() {
        ArrayList<YFile> files = new ArrayList<YFile>();
        for (File listFile : this.file.listFiles(filter -> filter.isFile())) {
            files.add(new YFile(listFile));
        }
        return files;
    }

    public List<Object> list() {
        ArrayList<Object> list = new ArrayList<Object>();
        for (File obj : this.file.listFiles(filter -> filter.isDirectory())) {
            list.add(new YDirectory(obj));
        }
        return list;
    }

    public List<YDirectory> listDirectories() {
        ArrayList<YDirectory> directories = new ArrayList<YDirectory>();
        for (File listDirectory : this.file.listFiles(filter -> filter.isDirectory())) {
            directories.add(new YDirectory(listDirectory));
        }
        return directories;
    }

    public File raw() {
        return this.file;
    }
}

