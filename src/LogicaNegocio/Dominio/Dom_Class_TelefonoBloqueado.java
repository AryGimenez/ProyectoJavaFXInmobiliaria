/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Dominio;

import LogicaNegocio.CreadoXNom.LogN_ClassAb_CrePorNom;
import LogicaNegocio.MiExeption.InputException;

/**
 *
 * @author Ary
 */
public class Dom_Class_TelefonoBloqueado extends LogN_ClassAb_CrePorNom{

    public Dom_Class_TelefonoBloqueado(String mNom) {
        super(mNom, "Telefono");
    }
    
    public Dom_Class_TelefonoBloqueado (Dom_Class_TelefonoBloqueado mObjTelB){
        
        this(mObjTelB.mNom);
        this.setNum(mObjTelB.getNum());
    }
    
    @Override
    public boolean setNom(String mNom) {
        if (mNom == null)
            this.mNom = "";
        else{
            super.mNom = mNom.trim();
            super.mNom = mNom.replaceAll(" ", "");
        }

        return true;
    }

    @Override
    public String[] getIgualInva(LogN_ClassAb_CrePorNom xObjCoparado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public void validar() throws InputException {
        super.validar(); //To change body of generated methods, choose Tools | Templates.
        try {
            Integer.parseInt(mNom);
        }catch (java.lang.NumberFormatException ex){
            throw new InputException ("No ha ingresado un número de teléfono correcto");
        }
    }
    
    @Override
    public LogN_ClassAb_CrePorNom duplicar() {
        return new Dom_Class_TelefonoBloqueado(this);
    }
    
}
