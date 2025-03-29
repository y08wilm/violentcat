/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import pisi.unitedmeows.yystal.clazz.HookClass;
import pisi.unitedmeows.yystal.clazz.prop;
import pisi.unitedmeows.yystal.exception.YExManager;
import pisi.unitedmeows.yystal.exception.impl.sql.YexSqlError;
import pisi.unitedmeows.yystal.hook.YString;
import pisi.unitedmeows.yystal.sql.YQueryResult;
import pisi.unitedmeows.yystal.sql.YSQLClient;

public class YDatabaseWorker
extends HookClass<YSQLClient> {
    public final prop<String> database;
    private Connection connection;
    private boolean connected;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected YDatabaseWorker(YSQLClient owner, String _database) {
        this.hooked = owner;
        this.database = new prop<String>(_database);
        try {
            YDatabaseWorker yDatabaseWorker = this;
            synchronized (yDatabaseWorker) {
                if (this.connection != null && !this.connection.isClosed()) {
                    return;
                }
                this.connection = DriverManager.getConnection("jdbc:mysql://" + owner.host + ":" + owner.port + "/" + this.database.get() + "?characterEncoding=latin1&useConfigs=maxPerformance", owner.username, owner.password);
                this.connected = true;
            }
        }
        catch (Exception e) {
            this.connected = false;
            YExManager.pop(new YexSqlError("YDatabaseWorker couldn't connect to database (" + this.database.get() + ")"));
        }
    }

    public boolean executeNonQuery(String sql) {
        String newSql = sql.startsWith("@") ? sql : new YString(sql).toString();
        try {
            return this.connection.prepareStatement(newSql).execute();
        }
        catch (SQLException ex) {
            YExManager.pop(new YexSqlError(ex.getMessage()));
            return false;
        }
    }

    public YQueryResult createQuery(String query) {
        String newQuery = query.startsWith("@") ? query : new YString(query).toString();
        try {
            return new YQueryResult(this.connection.prepareStatement(newQuery));
        }
        catch (SQLException e) {
            return null;
        }
    }
}

