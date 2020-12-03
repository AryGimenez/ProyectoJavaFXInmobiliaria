/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import LogicaNegocio.Dominio.Dom_Class_TelefonoBloqueado;
import LogicaNegocio.Manejadora.LogN_Class_Fachada;
import LogicaNegocio.MiExeption.InputException;
import com.abrahamfx.uicontrols.DialogBox;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ary
 */
public class Pres_BloquearTel extends Stage {

   @FXML private Button Btn_Agregar;
   @FXML private Button Btn_Eliminar;
   @FXML private Button Btn_Salir;
   
   @FXML private ListView Lis_Telefono;
   @FXML private VBox VB_Prin;
   @FXML private TextField Txt_Telefono;
   
   private ObservableList observableList;
   
   private LogN_Class_Fachada mObjFachada = LogN_Class_Fachada.getInstancia();
   
   
   
    public Pres_BloquearTel() {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Pres_BloquearTel.fxml"));
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } 
        
        this.initStyle(StageStyle.TRANSPARENT);
        this.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(VB_Prin, Color.TRANSPARENT);
        
        this.getIcons().add(new Image("/Iconos/IconosCasa.png"));
        this.setTitle("Bloquear Telefono");
        
        this.setScene(scene);
        this.show();
        
        
        Btn_Salir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Btn_SalirTelActionPerformed(t);
            }
        });
        
        Btn_Agregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Btn_AgregarTelActionPerformed(t);
            }
        });
        Btn_Eliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Btn_EliminarTelActionPerformed(t);
            }
        });
        
        //Iniciar ListModel-----------------------------------------------------
        observableList = FXCollections.observableArrayList();
       try {
           observableList.setAll(mObjFachada.listarTelBloqueado());
           Lis_Telefono.setItems(observableList);
       } catch (InputException ex) {
           Logger.getLogger(Pres_BloquearTel.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    
    private void Btn_SalirTelActionPerformed (ActionEvent evento){
        this.close();
    }
        
    private void Btn_AgregarTelActionPerformed (ActionEvent evento){
        Dom_Class_TelefonoBloqueado xObjTelB = new Dom_Class_TelefonoBloqueado(Txt_Telefono.getText());
       try {
           mObjFachada.altaTelBloqueado(xObjTelB);
           observableList.add(xObjTelB);
       } catch (InputException ex) {
           DialogBox dialogo = new DialogBox("Mensaje", ex.getLocalizedMessage());
       }
        
    }
    
    private void Btn_EliminarTelActionPerformed (ActionEvent evento){
        Dom_Class_TelefonoBloqueado xObjTelBloqueado = (Dom_Class_TelefonoBloqueado) Lis_Telefono.getSelectionModel().getSelectedItem();
        
        
        if (xObjTelBloqueado == null){
            DialogBox dialogo = new DialogBox("Mensaje", "Debe Seleccionar un teléfono a eliminar");
        }else{
            try {
                mObjFachada.bajaTelBloqueado(xObjTelBloqueado);
                observableList.remove(xObjTelBloqueado);
            } catch (InputException ex) {
                DialogBox dialogo = new DialogBox("Mensaje Error", ex.getLocalizedMessage());
            }
        }
            
    }
    
}
