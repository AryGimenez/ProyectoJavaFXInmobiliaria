package LogicaNegocio.Manejadora;

import LogicaNegocio.LogN_Class_AnunciosSinPros;
import LogicaNegocio.MiExeption.InputException;
import java.io.File;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ary
 */
public interface LogN_Inter_Fachada extends LogN_Inter_ManejTelefonoBloqueado{
    public ArrayList <LogN_Class_AnunciosSinPros> importarTxt (File pTxt)throws InputException;
    
    public void exportarATxt (ArrayList<LogN_Class_AnunciosSinPros> pColAnuncios , File pFichero)throws InputException;
}
