package com.example.session9.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.h2.tools.SimpleResultSet;

public class MyTableFunctions {
    public static ResultSet getNames() throws SQLException {
        SimpleResultSet rs = new SimpleResultSet();
        rs.addColumn("NAME", Types.VARCHAR, 255, 0);
        rs.addRow("Alice");
        rs.addRow("Bob");
        return rs;
    }
}


