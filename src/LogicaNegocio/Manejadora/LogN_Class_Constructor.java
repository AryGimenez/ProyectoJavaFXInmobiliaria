/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Manejadora;

import LogicaNegocio.Manejadora.ODBC.LogN_Class_ManejTelefonoBloqueadoODBC;

/**
 *
 * @author Ary
 */
public class LogN_Class_Constructor {
    private LogN_Inter_ManejTelefonoBloqueado mObjManTelB;

    public LogN_Class_Constructor() {
        this.createODBC();
    }

    
    public void createODBC (){
        mObjManTelB = new LogN_Class_ManejTelefonoBloqueadoODBC();
    }
    
    
    
    public LogN_Inter_ManejTelefonoBloqueado getmObjManTelB() {
        return mObjManTelB;
    }
    
    
}
