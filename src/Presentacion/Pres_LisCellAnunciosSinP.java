/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import LogicaNegocio.LogN_Class_AnunciosSinPros;
import javafx.scene.control.ListCell;

/**
 *
 * @author Ary
 */
public class Pres_LisCellAnunciosSinP extends ListCell<LogN_Class_AnunciosSinPros>{
    
    
    @Override
    public void updateItem(LogN_Class_AnunciosSinPros string, boolean empty){
        super.updateItem(string,empty);
        if(string != null) {
            Pres_Class_CellAnuncioSinP xObjCell = new Pres_Class_CellAnuncioSinP();
            xObjCell.setmObjAnuncioSinP(string);
            setGraphic(xObjCell.getVB_Anuncio());
        }
} 
}
