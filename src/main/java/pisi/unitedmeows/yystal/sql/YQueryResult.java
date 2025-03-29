/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pisi.unitedmeows.yystal.clazz.HookClass;
import pisi.unitedmeows.yystal.exception.YExManager;
import pisi.unitedmeows.yystal.exception.impl.sql.YexSqlError;

public class YQueryResult
extends HookClass<ResultSet> {
    private PreparedStatement preparedStatement;

    public YQueryResult(PreparedStatement statement) {
        this.preparedStatement = statement;
    }

    public YQueryResult execute() {
        try {
            this.hooked = this.preparedStatement.executeQuery();
        }
        catch (SQLException e) {
            YExManager.pop(new YexSqlError("Couldn't execute query"));
            return null;
        }
        return this;
    }

    public boolean hasNext() {
        try {
            return ((ResultSet)this.hooked).next();
        }
        catch (SQLException e) {
            YExManager.pop(new YexSqlError("Couldn't go to next row"));
            return false;
        }
    }

    public <X> X get(String column) {
        try {
            return (X)((ResultSet)this.hooked).getObject(column);
        }
        catch (SQLException e) {
            YExManager.pop(new YexSqlError("Couldn't get the column object"));
            return null;
        }
    }

    public <X> X get(int index) {
        try {
            return (X)((ResultSet)this.hooked).getObject(index);
        }
        catch (SQLException e) {
            YExManager.pop(new YexSqlError("Couldn't get the column object"));
            return null;
        }
    }

    public List<List<Object>> asList() {
        try {
            int columnLength = ((ResultSet)this.hooked).getMetaData().getColumnCount();
            ArrayList<List<Object>> array = new ArrayList<List<Object>>();
            while (this.hasNext()) {
                ArrayList list = new ArrayList(columnLength);
                for (int i = 0; i < columnLength; ++i) {
                    list.add(this.get(i));
                }
                array.add(list);
            }
            return array;
        }
        catch (SQLException ex) {
            YExManager.pop(new YexSqlError("Couldn't get the table metadata"));
            return new ArrayList<List<Object>>(0);
        }
    }

    @Override
    protected ResultSet getHooked() {
        return (ResultSet)super.getHooked();
    }
}

