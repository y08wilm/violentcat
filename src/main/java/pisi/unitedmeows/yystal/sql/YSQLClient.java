/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.sql;

import java.util.HashMap;
import java.util.Map;
import pisi.unitedmeows.yystal.exception.YExManager;
import pisi.unitedmeows.yystal.exception.impl.sql.YexMySQLDriverError;
import pisi.unitedmeows.yystal.networking.IPAddress;
import pisi.unitedmeows.yystal.sql.YDatabaseWorker;
import pisi.unitedmeows.yystal.utils.IDisposable;

public class YSQLClient
implements IDisposable {
    protected String username;
    protected String password;
    protected String host;
    protected int port;
    private Map<String, YDatabaseWorker> workers;

    public YSQLClient(String _username, String _password, String _host, int _port) {
        this.username = _username;
        this.password = _password;
        this.host = _host;
        this.port = _port;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (Exception ex) {
            YExManager.pop(new YexMySQLDriverError("MySQL Driver is not installed (error in YSQLClient constructor)"));
        }
        this.workers = new HashMap<String, YDatabaseWorker>();
    }

    public YSQLClient(String _username, String _password, IPAddress _host, int _port) {
        this(_username, _password, _host.getAddress(), _port);
    }

    public YSQLClient(String _username, String _password, IPAddress _host) {
        this(_username, _password, _host.getAddress());
    }

    public YSQLClient(String _username, String _password, String _host) {
        this(_username, _password, _host, 3306);
    }

    public YSQLClient(String _username, String _password) {
        this(_username, _password, "localhost");
    }

    public YDatabaseWorker worker(String database) {
        return this.workers.computeIfAbsent(database, name -> new YDatabaseWorker(this, (String)name));
    }

    @Override
    public void close() {
    }
}

