/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.op.sb.connection.dbconnection;

import com.op.sb.connection.ResourceConnection;
import java.sql.Connection;
import java.sql.SQLException;

public interface DBResourceConnection extends ResourceConnection {

    public Connection getConnection() throws ClassNotFoundException, SQLException;
    
}
