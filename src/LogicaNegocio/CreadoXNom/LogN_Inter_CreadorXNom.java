/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaNegocio.CreadoXNom;




import LogicaNegocio.MiExeption.InputException;
import java.util.ArrayList;

/**
 *
 * @author ary
 */
public interface LogN_Inter_CreadorXNom {

    public void altaXNom (LogN_ClassAb_CrePorNom xObjCreXNom) throws InputException;

    public void bajaXNom (LogN_ClassAb_CrePorNom xObjCreXNom) throws InputException;

    public void modificarXNom (LogN_ClassAb_CrePorNom xObjCreXNom) throws InputException;

    public  ArrayList <LogN_ClassAb_CrePorNom> getColCreXNom ();
}
