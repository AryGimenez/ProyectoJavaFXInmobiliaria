/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import LogicaNegocio.Dominio.Dom_Class_TelefonoBloqueado;
import LogicaNegocio.MiExeption.InputException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Comprende a los anuncios que no se prosesan 
 * @author Ary
 */
public class LogN_Class_AnunciosSinPros implements LogN_Inter_Validar{
    private String mDetalle;
    private ArrayList <String> mColTel = new ArrayList<>();
    private String mTel = "";
    private String mURL = "";
    private boolean mNotificar;
    
    public LogN_Class_AnunciosSinPros(String mDetalle, String mTel, String mURL, boolean mNotificar) {
        this.mDetalle = mDetalle;
        this.setmTel(mTel);
        this.setmURL(mURL);
        this.mNotificar = mNotificar;
    }

    public boolean ismNotificar() {
        return mNotificar;
    }

    public void setmNotificar(boolean mNotificar) {
        this.mNotificar = mNotificar;
    }

    
    
    public String getmDetalle() {
        return mDetalle;
    }

    public void setmDetalle(String mDetalle) {
        this.mDetalle = mDetalle;
    }

    public String getmTel() {

        return mTel;
    }

    public void setmTel(String mTel) {
	StringTokenizer tokens=new StringTokenizer(mTel);
        mColTel.clear();
        this.mTel = "";
	while(tokens.hasMoreTokens()){
            mTel = tokens.nextToken();
            mColTel.add(mTel);
            this.mTel =  mTel + " - " + this.mTel;
        }
        this.mTel = this.mTel.substring(0, this.mTel.length() - 3);
    }

    public String getmURL() {
        return mURL;
    }

    public void setmURL(String mURL) {
        if (mURL == null || mURL.indexOf("http") == -1)
            mURL = "";
        
        this.mURL = mURL;
    }
    
    
    
    public ArrayList<String> getmColTel() {
        return mColTel;
    }

    public void setmColTel(ArrayList<String> mColTel) {
        if (mColTel == null)
            mColTel = new ArrayList<>();
        
        this.mColTel = mColTel;
        this.mTel = "";
        if (mColTel.size() > 0){
            for (String xItemTel : mColTel){
                this.mTel =  xItemTel + " - " + this.mTel;
            }
            
            this.mTel = this.mTel.substring(0, this.mTel.length() - 3);                
        }
        
        
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LogN_Class_AnunciosSinPros other = (LogN_Class_AnunciosSinPros) obj;
        
        for (String xTel: mColTel){
            for (String xTel2 : other.mColTel)
                if (xTel.equals(xTel2))
                    return true;
        }
        
        return false;
    }

    public boolean bloqueado(Dom_Class_TelefonoBloqueado pObjTelB) {
        for (String xTel : mColTel)
            if (xTel.equals(pObjTelB.getNom()))
                return true;
        return false;
    }

    @Override
    public void validar() throws InputException {
        
    }

 
    
    
    
    
    
    
    
    
    
}
