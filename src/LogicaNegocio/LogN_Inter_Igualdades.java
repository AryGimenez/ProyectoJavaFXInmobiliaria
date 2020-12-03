/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LogicaNegocio;

/**
 * <pre>
 * <b><font color="red">Identificar valores repetidos</b></font> Interfaces comÃºn
 * para aquellos objetos que necesitar representa el nombre de los valores que no
 * se pueden repetir.
 * </pre>
 * @author ary
 * @version 1.0
 * @updated 01-mar-2012 08:44:47 a.m.
 */
public interface LogN_Inter_Igualdades<T> {

    /**
	 * <b><font color="blue">Igualdades no permitidas </b></font><br> MÃ©todo
	 * encargado de retornar lo valores que se repiten de y que no estÃ¡n permitidos.
	 * 
	 * @param xObjComparado Objeto con el cual se compara
	 * @return Array de String conteniendo el nombre de valores que que se repiten
	 * 
	 * @param xObjCoparado
	 */
    public String[] getIgualInva (T xObjCoparado);

}
