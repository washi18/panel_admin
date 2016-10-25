package com.pricing.viewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.SimpleCategoryModel;

import com.pricing.dao.CReporteReservaDAO;
import com.pricing.model.CEstadistica_Paquete;
import com.pricing.model.CEstadistica_Reservas;

public class CEstadisticaReservaVM {
	private ArrayList<String> listaAnios;
	private String anio="";
	private CEstadistica_Reservas estadisticaReserva;
	private long[] sumasMeses;
	private String[] nombresPaquetes;
	//=================getter and setter======
	public ArrayList<String> getListaAnios() {
		return listaAnios;
	}

	public void setListaAnios(ArrayList<String> listaAnios) {
		this.listaAnios = listaAnios;
	}
	
	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public CEstadistica_Reservas getEstadisticaReserva() {
		return estadisticaReserva;
	}

	public void setEstadisticaReserva(CEstadistica_Reservas estadisticaReserva) {
		this.estadisticaReserva = estadisticaReserva;
	}
	
	public long[] getSumasMeses() {
		return sumasMeses;
	}

	public void setSumasMeses(long[] sumasMeses) {
		this.sumasMeses = sumasMeses;
	}

	public String[] getNombresPaquetes() {
		return nombresPaquetes;
	}

	public void setNombresPaquetes(String[] nombresPaquetes) {
		this.nombresPaquetes = nombresPaquetes;
	}

	//==============metodos=======
	@Init
	public void initVM()
	{
		/**Inicializando los objetos**/
		listaAnios=new ArrayList<String>();
		estadisticaReserva=new CEstadistica_Reservas();
		sumasMeses=new long[12];
		nombresPaquetes=new String[12];
		/**Obtencion de las etiquetas de la base de datos**/
		/**Asignacion de las etiquetas a la listaEtiquetas**/
		int primerAnio=2016;
		this.listaAnios.add(String.valueOf(primerAnio));
		Calendar fechaActual=Calendar.getInstance();
		int anioActual=fechaActual.get(Calendar.YEAR);
		if(anioActual>primerAnio)
		{
			primerAnio=anioActual;
			this.listaAnios.add(String.valueOf(primerAnio));
		}
	}

	@Command
	@NotifyChange({"anio"})
	public void asignarAnio(@BindingParam("anio")String anio){
		System.out.println("entra en el principal");
		this.anio=anio;
		estadisticaReserva.setAnio(this.anio);
		System.out.println("termina en el principal");
	}
	
	
	@Command
	@NotifyChange("estadisticaReserva")
	public void mostrarGrafico() throws Exception
	{
		System.out.println("ejecuta esto de grafico?");
		estadisticaReserva.ejecutarGrafico();
	}
	
	public SimpleCategoryModel getGrafica(){
        this.setSumasMeses(estadisticaReserva.getSumasMeses());
        this.setNombresPaquetes(estadisticaReserva.getNombresPaquetes());
        
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        String tipo[] = {"Defunciones", "Nuevas Altas", "Vacunas", "Citas"};
        
        SimpleCategoryModel demoModel = new SimpleCategoryModel();
        
        for(int i=0; i<4; i++){
            for(int j=0; j<12; j++){
                
                if(j==0) {
                    demoModel.setValue(nombresPaquetes[j], meses[j], sumasMeses[j]);
                }
                if(j==1) {
                    demoModel.setValue(nombresPaquetes[j], meses[j], sumasMeses[j]);
                }
                if(j==2) {
                    demoModel.setValue(nombresPaquetes[j], meses[j], sumasMeses[j]);
                }
                if(j==3) {
                    demoModel.setValue(nombresPaquetes[j], meses[j], sumasMeses[j]);
                }
                if(j==4) {
                    demoModel.setValue(nombresPaquetes[j], meses[j], sumasMeses[j]);
                }
                if(j==5) {
                    demoModel.setValue(nombresPaquetes[j], meses[j], sumasMeses[j]);
                }
                if(j==6) {
                    demoModel.setValue(nombresPaquetes[j], meses[j], sumasMeses[j]);
                }
                if(j==7) {
                    demoModel.setValue(nombresPaquetes[j], meses[j], sumasMeses[j]);
                }
                if(j==8) {
                    demoModel.setValue(nombresPaquetes[j], meses[j], sumasMeses[j]);
                }
                if(j==9) {
                    demoModel.setValue(nombresPaquetes[j], meses[j], sumasMeses[j]);
                }
                if(j==10) {
                    demoModel.setValue(nombresPaquetes[j], meses[j], sumasMeses[j]);
                }
                if(j==11) {
                    demoModel.setValue(nombresPaquetes[j], meses[j], sumasMeses[j]);
                }
            }
        }
        return demoModel;
    }
	
}
