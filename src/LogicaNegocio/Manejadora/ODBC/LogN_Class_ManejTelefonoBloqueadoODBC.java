/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Manejadora.ODBC;

import LogicaNegocio.Dominio.Dom_Class_TelefonoBloqueado;
import LogicaNegocio.Manejadora.LogN_Inter_ManejTelefonoBloqueado;
import LogicaNegocio.MiExeption.InputException;
import Persistencia.Pre_Class_GestionCasa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ary
 */
public class LogN_Class_ManejTelefonoBloqueadoODBC extends Observable implements LogN_Inter_ManejTelefonoBloqueado  {
    private ArrayList <Dom_Class_TelefonoBloqueado> mColTelBloqueado = new ArrayList<>();
    private Pre_Class_GestionCasa mObjCon = new Pre_Class_GestionCasa();
    
    public LogN_Class_ManejTelefonoBloqueadoODBC() {
        try {
            this.cargar();
        } catch (InputException ex) {
            Logger.getLogger(LogN_Class_ManejTelefonoBloqueadoODBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
    
    
    private void cargar() throws InputException{
        ArrayList<Dom_Class_TelefonoBloqueado> xColTelB = new ArrayList<>();
        try {
            ResultSet xRs = mObjCon.AbrirConection().createStatement().executeQuery("SELECT * FROM TelBloqueado");
            while (xRs.next()){
                String xNom = xRs.getString("TelB_Nom");
                int xNum = xRs.getInt("TelB_Id");
                Dom_Class_TelefonoBloqueado xObjTelB = new Dom_Class_TelefonoBloqueado(xNom);
                xObjTelB.setNum(xNum);
                xColTelB.add(xObjTelB);
            }
        } catch (SQLException ex) {
            throw new InputException(ex.getLocalizedMessage());
        }finally{
            mObjCon.closeConecction();
        }
        mColTelBloqueado = xColTelB;
    }
    
    
    
    
    @Override
    public void altaTelBloqueado(Dom_Class_TelefonoBloqueado mObjTelBloqueado) throws InputException {
        try {
            if (mObjTelBloqueado == null)
                throw new InputException("El teléfono ingresado no es valido");
            
            mObjTelBloqueado.validar();
            
            if (mColTelBloqueado.contains(mObjTelBloqueado))
                throw new InputException ("El teléfono ingresado ya existe");
            
            PreparedStatement xPs = mObjCon.AbrirConection().prepareStatement
                    ("INSERT INTO TelBloqueado (TelB_Nom) VALUES (?)",
                            Statement.RETURN_GENERATED_KEYS);
            xPs.setString(1, mObjTelBloqueado.getNom());
            xPs.executeUpdate();
            ResultSet xRs = xPs.getGeneratedKeys();
            if (xRs.next())
                mObjTelBloqueado.setNum(xRs.getInt(1));
            
            mColTelBloqueado.add(mObjTelBloqueado);
                    
        } catch (SQLException ex) {
            throw new InputException(ex.getLocalizedMessage());
        }finally{
            mObjCon.closeConecction();
        }
       
    }

    @Override
    public void bajaTelBloqueado(Dom_Class_TelefonoBloqueado mObjTelBloqueado) throws InputException {
        try {
            if (mObjTelBloqueado == null)
                throw new InputException("El Teléfono ingresado no existe en el sistema");
            int xIndex = mColTelBloqueado.indexOf(mObjTelBloqueado);
            
            if (xIndex == -1)
                throw new InputException("El Teléfono ingresado no existe en el sistema");
            
            mObjTelBloqueado = mColTelBloqueado.get(xIndex);
            
            mObjCon.AbrirConection().createStatement().executeUpdate("DELETE FROM TelBloqueado WHERE TelB_Id = " + mObjTelBloqueado.getNum());
            
            mColTelBloqueado.remove(xIndex);
        } catch (SQLException ex) {
            throw new InputException(ex.getLocalizedMessage());
        }finally{
            mObjCon.closeConecction();
        }
    }

    @Override
    public ArrayList<Dom_Class_TelefonoBloqueado> listarTelBloqueado() throws InputException {
        return new ArrayList<Dom_Class_TelefonoBloqueado>(mColTelBloqueado);
    }

    @Override
    public Dom_Class_TelefonoBloqueado getTelBloqueado(String pTel) throws InputException {
        Dom_Class_TelefonoBloqueado xObjTelB = new Dom_Class_TelefonoBloqueado(pTel);
        int xIndex = mColTelBloqueado.indexOf(xObjTelB);
        if (xIndex == -1)
            return null;
        return mColTelBloqueado.get(xIndex);
    }

    
    @Override
    public void addObservadorTelBloq(Observer xObjObservador) {
        super.addObserver(xObjObservador);
    }

    
    @Override
    public void deleteObservadorTelBloq(Observer xObjObservador) {
        super.deleteObserver(xObjObservador);
    }
    
}
