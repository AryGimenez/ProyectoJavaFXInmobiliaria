/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import org.sqlite.SQLiteConfig;

/**
 *
 * @author Ary
 */
public class Pre_Class_GestionCasa extends Per_ClassAb_Coneccion{

    public Pre_Class_GestionCasa() {
         
//       super("jdbc:sqlite:/home/ary/Dropbox/Programas/Gestion Rural/Codigo Fuente/GestionRural/GestionRural.db", "", "", "org.sqlite.JDBC");
       super("jdbc:sqlite:GestionCasa.db", "", "", "org.sqlite.JDBC");
//       super("jdbc:odbc:GestionRural", "", "", "sun.jdbc.odbc.JdbcOdbcDriver");
//        super("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=GestionRural.mdb", "", "", "sun.jdbc.odbc.JdbcOdbcDriver");

       SQLiteConfig config = new SQLiteConfig();
       config.enforceForeignKeys(true);
       
       this.altaObjParam(config.toProperties());
       
    }
    
}
