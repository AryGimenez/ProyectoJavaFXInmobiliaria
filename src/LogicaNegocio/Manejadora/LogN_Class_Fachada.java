/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Manejadora;

import LogicaNegocio.Dominio.Dom_Class_TelefonoBloqueado;
import LogicaNegocio.LogN_Class_AnunciosSinPros;
import LogicaNegocio.MiExeption.InputException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ary
 */
public class LogN_Class_Fachada implements LogN_Inter_Fachada{

    private static  LogN_Class_Fachada instancia;
    private LogN_Inter_ManejTelefonoBloqueado mObjManejTelB;

    private LogN_Class_Fachada() {
        LogN_Class_Constructor xObjCons = new LogN_Class_Constructor();
        mObjManejTelB = xObjCons.getmObjManTelB();
    }
    
    
    public static LogN_Class_Fachada getInstancia() {
        if (instancia == null) 
            CrearInstancia();

        return instancia;
    }

    private synchronized static void CrearInstancia() {
            instancia = new LogN_Class_Fachada();
            
    }
    
    
    @Override
    public ArrayList<LogN_Class_AnunciosSinPros> importarTxt(File pTxt)throws InputException{
        ArrayList<LogN_Class_AnunciosSinPros> xColRespuesta = new ArrayList<LogN_Class_AnunciosSinPros>();
        ArrayList<LogN_Class_AnunciosSinPros> xColABorrar = new ArrayList<>();
        ArrayList<Dom_Class_TelefonoBloqueado> xColTelBloqueado = this.listarTelBloqueado();
                
        FileReader xfr = null;
        try {
            
            xfr = new FileReader (pTxt);
            BufferedReader xbr = new BufferedReader(xfr);
            
            // Lectura del fichero

            String xLinea;
            
            while((xLinea = xbr.readLine())!=null){
                Integer xIndice = xLinea.indexOf("Tel:");
                
                if (xIndice != -1){
                    String xTel = xLinea.substring(xIndice + 4);
                    String xDetalle = xLinea.substring(0, xIndice);
                    xIndice = xDetalle.indexOf(" ");
                    String xHTML = xDetalle.substring(0, xIndice);
                    if (xHTML.indexOf("http") == -1)
                        xHTML= "";
                    else
                        xDetalle = xDetalle.substring(xIndice);
                    
                    boolean xError = false;
                    
                    if (xTel.indexOf("<---") != -1){
                        xError = true;
                        xTel = xTel.replaceAll("<---", "");
                    }
                        
                    
                    LogN_Class_AnunciosSinPros xObjAsinP =new LogN_Class_AnunciosSinPros(xDetalle ,xTel, xHTML, xError);
                    
                    boolean xBloqueado = false;
                    int xSize = xColTelBloqueado.size();
                    
                    for (int i = 0; i < xSize; i++){
                        if (xObjAsinP.bloqueado(xColTelBloqueado.get(i))){
                            xBloqueado = true;
                            i = xSize + 1;
                        }
                    }
                    
                    if (!xBloqueado){
                        xIndice = xColRespuesta.indexOf(xObjAsinP);
                        if (xIndice == -1){
                            xColRespuesta.add(xObjAsinP);
                        }else{
                            if (!xColABorrar.contains(xObjAsinP))
                                xColABorrar.add(xObjAsinP);
                        }                        
                    }
                    
                }
            }
            
            for (LogN_Class_AnunciosSinPros xTelABorrar : xColABorrar){
                xColRespuesta.remove(xColABorrar);
            }
            
 
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LogN_Class_Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LogN_Class_Fachada.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                xfr.close();
            } catch (IOException ex) {
                Logger.getLogger(LogN_Class_Fachada.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return xColRespuesta;
    }
    
    @Override
    public void exportarATxt (ArrayList<LogN_Class_AnunciosSinPros> pColAnuncios , File pFichero)throws InputException{
        FileWriter xFichero = null;
        PrintWriter pw;
        try
        {
            xFichero  = new FileWriter(pFichero);
            pw = new PrintWriter(xFichero);
 
            for (LogN_Class_AnunciosSinPros xObjAn : pColAnuncios){
                String xError = "";
                if (xObjAn.ismNotificar())
                    xError = " <---";
                
                pw.println(xObjAn.getmURL() + " " + xObjAn.getmDetalle() + 
                        " Tel: " + xObjAn.getmTel().replaceAll(" -", "") + xError );
            }
                
 
        } catch (Exception e) {
           throw new InputException(e.getLocalizedMessage());
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != xFichero)
              xFichero.close();
           } catch (Exception e2) {
              throw new InputException(e2.getLocalizedMessage());
           }
        }
    }
    
    public File getFichero (String xDir)throws InputException{
        File xFichero = new File(xDir);
        
        if (!xFichero.exists())
            throw new InputException("El fichero seleccionado no existe");
        
        return xFichero;
            
    }
    
    // <editor-fold defaultstate="collapsed" desc="Metodos Telefono Bloqueado"> 

        @Override
        public void altaTelBloqueado(Dom_Class_TelefonoBloqueado mObjTelBloqueado) throws InputException {
            mObjManejTelB.altaTelBloqueado(mObjTelBloqueado);
        }

        @Override
        public void bajaTelBloqueado(Dom_Class_TelefonoBloqueado mObjTelBloqueado) throws InputException {
            mObjManejTelB.bajaTelBloqueado(mObjTelBloqueado);
        }

        @Override
        public ArrayList<Dom_Class_TelefonoBloqueado> listarTelBloqueado() throws InputException {
            return mObjManejTelB.listarTelBloqueado();
        }

        @Override
        public Dom_Class_TelefonoBloqueado getTelBloqueado(String pTel) throws InputException {
            return mObjManejTelB.getTelBloqueado(pTel);
        }
        
        @Override
        public void addObservadorTelBloq(Observer xObjObservador) {
            mObjManejTelB.addObservadorTelBloq(xObjObservador);
        }

        @Override
        public void deleteObservadorTelBloq(Observer xObjObservador) {
            mObjManejTelB.deleteObservadorTelBloq(xObjObservador);
        }
        

    // </editor-fold>     


    
    
}
