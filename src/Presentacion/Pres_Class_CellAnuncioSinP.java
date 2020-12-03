/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import LogicaNegocio.LogN_Class_AnunciosSinPros;
import edu.stanford.ejalbert.BrowserLauncher;
import edu.stanford.ejalbert.exception.BrowserLaunchingInitializingException;
import edu.stanford.ejalbert.exception.UnsupportedOperatingSystemException;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import com.abrahamfx.uicontrols.DialogBox;

/**
 *
 * @author Ary
 */
public class Pres_Class_CellAnuncioSinP {
    
    @FXML
    private VBox VB_Anuncio;
    @FXML
    private Label Lbl_Detalle;
    @FXML
    private Label Lbl_Telefono;
    @FXML
    private ImageView ImgV_Url;
    
    private LogN_Class_AnunciosSinPros mObjAnuncioSinP;
            
    public Pres_Class_CellAnuncioSinP() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Pres_Class_CellAnuncioSinP.fxml"));
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        ImgV_Url.setOnMouseClicked(new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
                ImgV_UrlOnMouseClicked(event);
            }
        });
      //  stylesheets="@Pres_Class_CellAnuncioSinP-FALCE.css"
      
 
      
    }

    public VBox getVB_Anuncio() {
        return VB_Anuncio;
    }
    
    public LogN_Class_AnunciosSinPros getmObjAnuncioSinP() {
        return mObjAnuncioSinP;
    }

    public void setmObjAnuncioSinP(LogN_Class_AnunciosSinPros mObjAnuncioSinP) {
        this.mObjAnuncioSinP = mObjAnuncioSinP;
        String xTelefono = "", xDetalle = "";
        
        if (mObjAnuncioSinP != null){
            xTelefono = mObjAnuncioSinP.getmTel();
            xDetalle = mObjAnuncioSinP.getmDetalle();
        }
            
        Lbl_Telefono.setText(xTelefono);
        Lbl_Detalle.setText(xDetalle);
        
        VB_Anuncio.getStylesheets().clear();
        if (mObjAnuncioSinP.ismNotificar())
            VB_Anuncio.getStylesheets().add("Presentacion/Pres_Class_CellAnuncioSinP-TRUE.css");
        else
            VB_Anuncio.getStylesheets().add("Presentacion/Pres_Class_CellAnuncioSinP-FALCE.css");
            
    }
    
    public void ImgV_UrlOnMouseClicked(MouseEvent t){
        try {
            
            BrowserLauncher xObjUrl = new BrowserLauncher();
            String mTitulo = mObjAnuncioSinP.getmURL();
            if (mTitulo.equals("")){
                DialogBox xDialogo = new DialogBox("Mensaje", "No existe el URL");
            }else
                xObjUrl.openURLinBrowser(mTitulo);
            
        } catch (BrowserLaunchingInitializingException ex) {
            Logger.getLogger(Pres_Class_CellAnuncioSinP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedOperatingSystemException ex) {
            Logger.getLogger(Pres_Class_CellAnuncioSinP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
