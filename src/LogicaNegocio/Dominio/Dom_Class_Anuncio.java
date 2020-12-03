/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio.Dominio;

/**
 *
 * @author Ary
 */
public class Dom_Class_Anuncio {
	/**
	 * Representa el mail del Propietario
	 */
	private String mailProp;
	/**
	 * Nombre de propietario del imueble
	 */
	private String nomProp;
	/**
	 * Telefono Propietario Inmueble
	 */
	private String TelProp;
	public static final int TipI_Apartamento=1;
	public static final int TipI_Casa = 0;
	/**
	 * Tipo de Inpueble puede ser
	 * <ul>
	 * 	<li>Casa = 0</li>
	 * </ul>
	 * <ul>
	 * 	<li>Apartamento = 1</li>
	 * </ul>
	 */
	private int tipImueble;
	/**
	 * Representa la cantidad de anvientes 
	 */
	private int anvientes;
	/**
	 * Cantidad Avitaciones
	 */
	private int avitaciones;
	/**
	 * Representa el estado <b>Dentro del Mercado</b>
	 */
	private static final int Est_DentroMercado = 1;
	/**
	 * Representa el estado <b>Fuera del mercado</b>
	 */
	private static final int Est_FueraMer = 0;
	/**
	 * Representa al estado F<b>uera del mercado por la empresa </b>
	 */
	private static final int Est_FuerMEm = 2;
	/**
	 * Estado del anuncio
	 * <ul>
	 * 	<li>Fuera del mercado = 0</li>
	 * 	<li>Dentro del mercado = 1</li>
	 * 	<li>Fuera del mercado por la empresa = 2</li>
	 * </ul>
	 */
	private int estado = 1;
	/**
	 * Cantidad Piso
	 */
	private int Pisos;
	public static final int TipA_Alquiler = 0;
	public static final int TipA_Venta = 1;
	/**
	 * Represente el tipo de anuncio
	 * <ul>
	 * 	<li>Alquiler </li>
	 * 	<li>Venta</li>
	 * </ul>
	 */
	private int tipAnuncio;

    
}
