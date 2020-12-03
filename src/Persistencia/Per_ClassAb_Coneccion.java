/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Ary
 */
public class Per_ClassAb_Coneccion {
      /*Atributos*/
    private String url;;

    
    private String usr ;

    private String pswd;

    private String Dirvr;

    private Connection con;

    public boolean mConectado = false;

    public boolean mEditable = true;
    
    private Properties mObjParam = new Properties();
                
   
    
    /*Constructor, carga puente JDBC-ODBC*/


    public Per_ClassAb_Coneccion(String URL, String User, String Pswd, String Driver){
        this.url = URL;
        this.Dirvr = Driver;
        this.usr = User;
        this.pswd = Pswd;
        mObjParam.put("charSet", "iso-8859-1");
       loadDriver();
    }

    /**
    * Carga el driver de la conexi�n a la base de datos
    */
    private void loadDriver(){
        try
        {
            //Instanc�a de una nueva clase para el puente
            //sun.jdbc.odbc.JdbcOdbcDriver
            //El puente sirve entre la aplicaci�n y el driver.
            Class.forName(Dirvr);
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Error al crear el puente " + Dirvr);
        }
    }

    public void setEditable (boolean xValor){
        mEditable = xValor;
    }

    public boolean getEditable(){
        return mEditable;
    }

    /**
    *Obtiene una conexi�n con el nombre del driver especificado
    *@param driverName Nombre del driver de la base de datos
    *@return
    */
    public Connection AbrirConection(){
         if (!mConectado){

             System.out.println("Estableciendo conexi�n con " + url);
            try
            {
                //Obtiene la conexi�n


                
                con = DriverManager.getConnection( url,mObjParam);
                
                
                mConectado = true;
            }
            catch(SQLException sqle)
            {
                System.out.println("No se pudo establecer la conexi�n");
                return null;
            }

            System.out.println("Conexi�n establecida con:t " + url);
         }


        //Regresa la conexi�n </span>
        return con;
    }
    
    
    public void altaObjParam (Properties mObjParam){
        this.mObjParam.putAll(mObjParam);
    }

    /* Cerrar la conexi�n.*/

    public boolean closeConecction() {
        if (mEditable == true && mConectado == true){
            try
            {
                con.close();
                mConectado = false;
                System.out.println("Se serro coneccion con  " + url);
            }
            catch(SQLException sqle)
            {
                System.out.println("No se cerro la conexi�n");
                return false;
            }

            System.out.println("Conexi�n cerrada con �xito ");
            return true;
        }
        return false;
    }
}
