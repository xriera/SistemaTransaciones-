/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.sistematransaciones.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 *
 * @author xavier & vinicio
 */
public class Resources {
    String dsName = "java:jboss/datasources/parqueaderoDS";

    @Produces
    private Connection createConnection() throws SQLException, NamingException {
        InitialContext ic = new InitialContext();
        DataSource ds = (DataSource)ic.lookup(dsName);
        return ds.getConnection();
    }
    
    private void closeConnection(@Disposes Connection conn) throws SQLException {
        conn.close();
    }
    @Produces
    @PersistenceContext
    private EntityManager em;
}
