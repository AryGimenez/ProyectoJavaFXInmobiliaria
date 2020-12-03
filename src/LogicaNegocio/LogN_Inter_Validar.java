package LogicaNegocio;

import LogicaNegocio.MiExeption.InputException;





/**
 * Interface LogN_Class_Validar Interfase compungir para los datos que requieran
 * una validaciÃ³n de sus datos.
 * @author Ary
 * @version 1.0
 * @updated 01-mar-2012 08:44:48 a.m.
 */
public interface LogN_Inter_Validar {
    /**
     * Error de datos
     * @throws InputException
     */
  public void validar(  ) throws InputException;


}
