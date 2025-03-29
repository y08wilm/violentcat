/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.exception.impl.YexIO;

public class YFile {
    private String path;
    private File file;

    public YFile(String _path) {
        this(new File(_path));
    }

    public YFile(File _file) {
        this.file = _file;
        this.path = this.file.getAbsolutePath();
    }

    public YFile(String _directory, String _fileName) {
        this(new File(_directory, _fileName));
    }

    public YFile create() {
        try {
            this.file.createNewFile();
        }
        catch (IOException e) {
            YYStal.pop(new YexIO("Couldn't create a new file PATH: " + this.path, new Object[0]));
        }
        return this;
    }

    public YFile writeAll(String input) {
        try {
            FileWriter myWriter = new FileWriter(this.file);
            myWriter.write(input);
            myWriter.close();
        }
        catch (IOException ex) {
            YYStal.pop(new YexIO("Error while YFile:writeAll (couldn't write to file)", new Object[0]));
        }
        return this;
    }

    public YFile writeAll(List<String> input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < input.size(); ++i) {
            stringBuilder.append(input.get(i));
            stringBuilder.append(System.lineSeparator());
        }
        this.writeAll(stringBuilder.toString());
        return this;
    }

    public YFile writeAll(String[] input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < input.length; ++i) {
            stringBuilder.append(input[i]);
            stringBuilder.append(System.lineSeparator());
        }
        this.writeAll(stringBuilder.toString());
        return this;
    }

    public YFile write(List<String> input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < input.size(); ++i) {
            stringBuilder.append(input.get(i));
            stringBuilder.append(System.lineSeparator());
        }
        this.write(stringBuilder.toString());
        return this;
    }

    public YFile write(String[] input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < input.length; ++i) {
            stringBuilder.append(input[i]);
            stringBuilder.append(System.lineSeparator());
        }
        this.write(stringBuilder.toString());
        return this;
    }

    public YFile write(String input) {
        try {
            FileWriter myWriter = new FileWriter(this.file, true);
            myWriter.write(input);
            myWriter.close();
        }
        catch (IOException ex) {
            YYStal.pop(new YexIO("Error while YFile:write (couldn't write to file)", new Object[0]));
        }
        return this;
    }

    public List<String> readAllLines() {
        ArrayList<String> readContent = new ArrayList<String>();
        try {
            String str;
            BufferedReader in = new BufferedReader(new InputStreamReader((InputStream)new FileInputStream(this.file), "UTF8"));
            while ((str = in.readLine()) != null) {
                readContent.add(str);
            }
            in.close();
        }
        catch (Exception e) {
            YYStal.pop(new YexIO("Error while YFile:readAllText (couldn't read the file)", new Object[0]));
        }
        return readContent;
    }

    public String readAllText() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String str;
            BufferedReader in = new BufferedReader(new InputStreamReader((InputStream)new FileInputStream(this.file), "UTF8"));
            while ((str = in.readLine()) != null) {
                stringBuilder.append(str).append(System.lineSeparator());
            }
            in.close();
        }
        catch (Exception e) {
            YYStal.pop(new YexIO("Error while YFile:readAllText (couldn't read the file)", new Object[0]));
        }
        return stringBuilder.toString();
    }

    public boolean rename(String newName) {
        File newFile = new File(this.file.getParentFile().getAbsoluteFile(), newName);
        if (this.file.renameTo(this.file)) {
            this.file = newFile;
            return true;
        }
        YYStal.pop(new YexIO("Error while YFile:reanme (couldn't rename file to " + newName + ")", new Object[0]));
        return false;
    }

    public File file() {
        return this.file;
    }

    public boolean delete() {
        return this.file.delete();
    }
}

