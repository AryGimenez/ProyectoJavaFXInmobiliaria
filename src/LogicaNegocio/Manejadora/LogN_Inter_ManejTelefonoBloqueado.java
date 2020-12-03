/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Manejadora;

import LogicaNegocio.Dominio.Dom_Class_TelefonoBloqueado;
import LogicaNegocio.MiExeption.InputException;
import java.util.ArrayList;
import java.util.Observer;

/**
 *
 * @author Ary
 */
public interface LogN_Inter_ManejTelefonoBloqueado {

    public void altaTelBloqueado(Dom_Class_TelefonoBloqueado mObjTelBloqueado)throws InputException;
    
    public void bajaTelBloqueado(Dom_Class_TelefonoBloqueado mObjTelBloqueado)throws InputException;
    
    public ArrayList <Dom_Class_TelefonoBloqueado> listarTelBloqueado () throws InputException;
    
    public Dom_Class_TelefonoBloqueado getTelBloqueado (String pTel) throws InputException;
    
    public void addObservadorTelBloq(Observer xObjObservador);
    
    public void deleteObservadorTelBloq(Observer xObjObservador);
    
    
}
