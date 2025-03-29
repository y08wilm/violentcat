/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.fusesource.jansi.Ansi
 *  org.fusesource.jansi.Ansi$Color
 *  org.fusesource.jansi.AnsiConsole
 */
package pisi.unitedmeows.yystal.logger.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Locale;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import pisi.unitedmeows.yystal.file.YFile;
import pisi.unitedmeows.yystal.logger.ILogger;

public class YLogger
implements ILogger {
    private Time time = Time.DAY_MONTH_YEAR_FULL;
    private final Class<?> clazz;
    private String name;
    private boolean prefix;
    private boolean save;
    private int bufferSize;
    private YFile file;
    private String[] buffer;
    private boolean colored = false;
    private int bufferIndex;
    private static final Ansi.Color FATAL_COLOR = Ansi.Color.RED;

    public YLogger(Class<?> _clazz) {
        this.clazz = _clazz;
        this.name = "[" + this.clazz.getSimpleName().toUpperCase(Locale.ROOT) + "]";
    }

    public YLogger(Class<?> _clazz, String _name) {
        this.clazz = _clazz;
        this.name = "[" + _name + "]";
    }

    private void post(String text) {
        if (this.save) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[").append(this.generateTime()).append("] ");
            if (this.prefix) {
                stringBuilder.append(this.name).append(" ");
            }
            stringBuilder.append(text);
            this.buffer[this.bufferIndex++] = stringBuilder.toString();
            if (this.bufferIndex >= this.bufferSize) {
                this.flush();
            }
        }
    }

    private void flush() {
        this.file.write(this.buffer);
        this.bufferIndex = 0;
    }

    private void internalPrint(String text, String prefix_, Ansi.Color textColor, boolean special) {
        String value = String.format("[%s] ", prefix_);
        if (this.colored) {
            System.out.print(Ansi.ansi().eraseScreen().fg(Ansi.Color.CYAN).bold().a("[").a(this.generateTime()).a("] "));
            if (this.prefix) {
                System.out.print(this.name);
                System.out.print(" ");
            }
            if (special) {
                System.out.print(Ansi.ansi().bg(FATAL_COLOR).fg(textColor).a(value));
                System.out.println(Ansi.ansi().bg(FATAL_COLOR).fg(textColor).a(text).reset());
            } else {
                System.out.print(Ansi.ansi().fg(textColor).a(value));
                System.out.println(Ansi.ansi().fg(textColor).a(text).reset());
            }
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[").append(this.generateTime()).append("] ");
            if (this.prefix) {
                stringBuilder.append(this.name).append(" ");
            }
            stringBuilder.append(value);
            stringBuilder.append(text);
            System.out.println(stringBuilder.toString());
        }
        this.post(value + text);
    }

    @Override
    public void info(String text) {
        this.internalPrint(text, "INFO", Ansi.Color.GREEN, false);
    }

    @Override
    public void warn(String text) {
        this.internalPrint(text, "WARN", Ansi.Color.YELLOW, false);
    }

    @Override
    public void print(String text) {
    }

    @Override
    public void fatal(String text) {
        this.internalPrint(text, "FATAL", Ansi.Color.BLACK, true);
    }

    @Override
    public void debug(String text) {
        this.internalPrint(text, "DEBUG", Ansi.Color.MAGENTA, false);
    }

    @Override
    public void log(Enum<?> type, String text) {
    }

    public Time time() {
        return this.time;
    }

    public YLogger time(Time _time) {
        this.time = _time;
        return this;
    }

    private String generateTime() {
        switch (this.time) {
            case MILLISECONDS: {
                return String.valueOf(System.currentTimeMillis());
            }
            case HOUR_MINUTES: {
                LocalDateTime localDateTime = LocalDateTime.now();
                return localDateTime.getHour() + ":" + localDateTime.getMinute();
            }
            case HOUR_MINUTES_SECONDS: {
                LocalDateTime localDateTime = LocalDateTime.now();
                return localDateTime.getHour() + ":" + localDateTime.getMinute() + ":" + localDateTime.getSecond();
            }
            case HOUR: {
                return String.valueOf(LocalDateTime.now().getHour());
            }
            case YEAR_MONTH_DAY_FULL: {
                LocalDateTime localDateTime = LocalDateTime.now();
                return localDateTime.getYear() + "-" + localDateTime.getMonthValue() + "-" + localDateTime.getDayOfMonth() + " " + localDateTime.getHour() + ":" + localDateTime.getMinute() + ":" + localDateTime.getSecond();
            }
            case DAY_MONTH_YEAR_FULL: {
                LocalDateTime localDateTime = LocalDateTime.now();
                return localDateTime.getDayOfMonth() + "-" + localDateTime.getMonthValue() + "-" + localDateTime.getYear() + " " + localDateTime.getHour() + ":" + localDateTime.getMinute() + ":" + localDateTime.getSecond();
            }
        }
        return "";
    }

    public YLogger colored(boolean state) {
        this.colored = state;
        if (state) {
            boolean installed = false;
            try {
                Field installedField = AnsiConsole.class.getDeclaredField("installed");
                installedField.setAccessible(true);
                installed = (Integer)installedField.get(null) > 0;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (!installed) {
                System.setProperty("jansi.passthrough", "true");
                System.setProperty("org.jline.terminal.dumb", "true");
                AnsiConsole.systemInstall();
            }
        }
        return this;
    }

    public YLogger outputToFile(File file, int buffer) {
        return this.outputToFile(new YFile(file), buffer);
    }

    public YLogger outputToFile(File file) {
        return this.outputToFile(new YFile(file), 1);
    }

    public YLogger outputToFile(YFile file) {
        return this.outputToFile(file, 1);
    }

    public YLogger outputToFile(YFile file, int bufferSize) {
        this.file = file;
        if (file.file().exists()) {
            file.delete();
        }
        this.save = true;
        this.bufferSize = bufferSize;
        if (this.buffer == null) {
            this.buffer = new String[bufferSize];
        }
        return this;
    }

    public YLogger stopOutput() {
        this.flush();
        this.save = false;
        return this;
    }

    public YLogger bufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
        if (this.buffer != null) {
            this.flush();
        }
        this.buffer = new String[bufferSize];
        return this;
    }

    public YLogger prefix(String value) {
        this.prefix = true;
        this.name = "[" + value + "]";
        return this;
    }

    @Override
    public void warn(String text, Object ... args) {
        this.warn(String.format(text, args));
    }

    @Override
    public void info(String text, Object ... args) {
        this.info(String.format(text, args));
    }

    @Override
    public void print(String text, Object ... args) {
        this.print(String.format(text, args));
    }

    @Override
    public void fatal(String text, Object ... args) {
        this.fatal(String.format(text, args));
    }

    @Override
    public void debug(String text, Object ... args) {
        this.debug(String.format(text, args));
    }

    public static enum Time {
        MILLISECONDS,
        HOUR_MINUTES,
        HOUR_MINUTES_SECONDS,
        HOUR,
        DAY_MONTH_YEAR_FULL,
        YEAR_MONTH_DAY_FULL,
        NO_TIME;

    }
}

