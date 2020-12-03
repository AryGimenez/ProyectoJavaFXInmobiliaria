package Presentacion;

import LogicaNegocio.LogN_Class_AnunciosSinPros;
import LogicaNegocio.Manejadora.LogN_Class_Fachada;
import LogicaNegocio.MiExeption.InputException;
import com.abrahamfx.uicontrols.DialogBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.util.Callback;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ary
 */
public class Pres_AnunciosSinPros extends Application implements Initializable {
    public static Stage mPrimaryStage;

    private FileChooser mFolderAplicacion;
    private ObservableList observableList;
    
    @FXML private ListView Lis_Anuncios;
    
    @FXML private ImageView Img_Prosesar;
    @FXML private ImageView Img_Eliminar;
    @FXML private ImageView Img_BloquearTel;
    @FXML private ImageView Img_ImportarTel;
    @FXML private ImageView Img_ExportarTel;

    @FXML private Button Btn_Prosesar;
    @FXML private Button Btn_Eliminar;
    @FXML private Button Btn_BloquearTel;
    @FXML private Button Btn_ImportarTel;
    @FXML private Button Btn_ExportarTel;
    
    private LogN_Class_Fachada mObjFachada = LogN_Class_Fachada.getInstancia();
 
    @FXML private ChoiceBox ComB_Imagen;
    
    @FXML private TextField Txt_Telefono;
    @FXML private TextField Txt_URL;
    
    @FXML private TextArea TxtA_Detalle;

    
    @FXML private void Btn_ImportarTelActionPerformed (ActionEvent evento){
        
        File file = mFolderAplicacion.showOpenDialog(mPrimaryStage);
        ArrayList<LogN_Class_AnunciosSinPros> xColAnuncio = new ArrayList<>();
        if (file != null)
            try {
                xColAnuncio = mObjFachada.importarTxt(file);
        } catch (InputException ex) {
            
        }
        this.observableList.setAll(xColAnuncio);
    }
    
    @FXML private void Btn_ExportarTelActionPerformed (ActionEvent evento){
        
        File xfile = mFolderAplicacion.showSaveDialog(mPrimaryStage);
        if (xfile == null)
            return;
        
        ArrayList<LogN_Class_AnunciosSinPros> xColASP = new ArrayList<>();
        for (Object xObj : observableList){
            xColASP.add((LogN_Class_AnunciosSinPros)xObj);
        }
        
        try {
            mObjFachada.exportarATxt(xColASP, xfile);
        } catch (InputException ex) {
            DialogBox xDialog = new DialogBox("Mensaje", ex.getLocalizedMessage());
        }
    }    

    @FXML private void Btn_BloquearTelActionPerformed (ActionEvent evento){
        Pres_BloquearTel xObjAnun = new Pres_BloquearTel();
       
    }

    @FXML private void Btn_EliminarActionPerformed (ActionEvent evento){
       int xIndex  = Lis_Anuncios.getSelectionModel().getSelectedIndex();
       if (xIndex == -1){
           DialogBox dialogo = new DialogBox("Mensaje", "Debe Seleccionar un anuncio a eliminar");
       }else
           observableList.remove(xIndex);
    }
    
    @FXML private void Btn_ProsesarActionPerformed (ActionEvent evento){
       LogN_Class_AnunciosSinPros xObjASP = new LogN_Class_AnunciosSinPros(TxtA_Detalle.getText(), Txt_Telefono.getText(), Txt_URL.getText(), false);
       
        try {
            xObjASP.validar();
            
            int xIndex  = Lis_Anuncios.getSelectionModel().getSelectedIndex();
            if (xIndex == -1)
                xIndex = 0;
            
            observableList.add(xIndex, xObjASP);
        } catch (InputException ex) {
            DialogBox dialogo = new DialogBox("Mensaje", ex.getLocalizedMessage());
        }

    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        mPrimaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Pres_AnunciosSinPros.fxml"));
        
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(new Image("/Iconos/IconosCasa.png"));
        primaryStage.setTitle("Gestion Casa");
        primaryStage.setScene(scene);
//        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mFolderAplicacion = new FileChooser();
        mFolderAplicacion.setTitle("Seleccione TXT a importar...");
        mFolderAplicacion.setInitialDirectory(new File(System.getProperty("user.home")));
        mFolderAplicacion.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        
        observableList = FXCollections.observableArrayList();
        
        ArrayList <LogN_Class_AnunciosSinPros> xColASProsesar = new ArrayList<>();

        observableList.setAll(xColASProsesar);
        Lis_Anuncios.setItems(observableList);
        
        Lis_Anuncios.setCellFactory(new Callback<ListView<LogN_Class_AnunciosSinPros>, javafx.scene.control.ListCell<LogN_Class_AnunciosSinPros>>() {
            @Override
            public ListCell<LogN_Class_AnunciosSinPros> call(ListView<LogN_Class_AnunciosSinPros> listView) {
                return new Pres_LisCellAnunciosSinP();
            }
        });
        
        
        Lis_Anuncios.getSelectionModel().selectedItemProperty()
            .addListener(new ChangeListener<LogN_Class_AnunciosSinPros>() {
              @Override
              public void changed(ObservableValue<? extends LogN_Class_AnunciosSinPros> observable,
                  LogN_Class_AnunciosSinPros oldValue, LogN_Class_AnunciosSinPros newValue) {
                  String xTel = "", xDetalle = "", xURL = "";

                  if (newValue != null){
                      xTel = newValue.getmTel().replace(" -", "");
                      xDetalle = newValue.getmDetalle();
                      xURL = newValue.getmURL();
                  }

                  Txt_Telefono.setText(xTel);
                  TxtA_Detalle.setText(xDetalle);
                  Txt_URL.setText(xURL);
              }
            });
        }
    
    
}
