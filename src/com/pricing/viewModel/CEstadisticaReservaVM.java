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

public class CEstadisticaReservaVM {
	private ArrayList<String> listaAnios;
	private boolean visibleGrafico;
	private String anio="";
	private long[] sumasMeses;
	private String[] nombresPaquetes={"","","","","","","","","","","",""};
	private CReporteReservaDAO reporteReservaDao;
    private ArrayList<CEstadistica_Paquete> listaPaquetesMasVendidos;
    String eneroInicio;
   	String eneroFinal;
   	String febreroInicio;
   	String febreroFinal;
   	String marzoInicio;
   	String marzoFinal;
   	String abrilInicio;
   	String abrilFinal;
   	String mayoInicio;
   	String mayoFinal;
   	String junioInicio;
   	String junioFinal;
   	String julioInicio;
   	String julioFinal;
   	String agostoInicio;
   	String agostoFinal;
   	String setiembreInicio;
   	String setiembreFinal;
   	String octubreInicio;
   	String octubreFinal;
   	String noviembreInicio;
   	String noviembreFinal;
   	String diciembreInicio;
   	String diciembreFinal;
   	//=====en formato date==========
   	Date EneroInicio=null;
   	Date EneroFinal=null;
   	Date FebreroInicio=null;
   	Date FebreroFinal=null;
   	Date MarzoInicio=null;
   	Date MarzoFinal=null;
   	Date AbrilInicio=null;
   	Date AbrilFinal=null;
   	Date MayoInicio=null;
   	Date MayoFinal=null;
   	Date JunioInicio=null;
   	Date JunioFinal=null;
   	Date JulioInicio=null;
   	Date JulioFinal=null;
   	Date AgostoInicio=null;
   	Date AgostoFinal=null;
   	Date SetiembreInicio=null;
   	Date SetiembreFinal=null;
   	Date OctubreInicio=null;
   	Date OctubreFinal=null;
   	Date NoviembreInicio=null;
   	Date NoviembreFinal=null;
   	Date DiciembreInicio=null;
   	Date DiciembreFinal=null;
       //===============getter and setter======
   	
   	public CReporteReservaDAO getReporteReservaDao() {
   		return reporteReservaDao;
   	}

   	public void setReporteReservaDao(CReporteReservaDAO reporteReservaDao) {
   		this.reporteReservaDao = reporteReservaDao;
   	}

   	public ArrayList<CEstadistica_Paquete> getListaPaquetesMasVendidos() {
   		return listaPaquetesMasVendidos;
   	}

   	public void setListaPaquetesMasVendidos(
   			ArrayList<CEstadistica_Paquete> listaPaquetesMasVendidos) {
   		this.listaPaquetesMasVendidos = listaPaquetesMasVendidos;
   	}
   	
   	
	public boolean isVisibleGrafico() {
		return visibleGrafico;
	}

	public void setVisibleGrafico(boolean visibleGrafico) {
		this.visibleGrafico = visibleGrafico;
	}

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
		sumasMeses=new long[12];
		visibleGrafico=false;
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
	@NotifyChange({"anio","grafica","visibleGrafico"})
	public void AsignarAnio(@BindingParam("anio")String anio)
	{
		visibleGrafico=true;
		this.anio=anio;
		procesarPaquetesMasVendidos();
	}
	
	@NotifyChange({"nombresPaquetes","sumasMeses"})
	public void procesarPaquetesMasVendidos()
	{
		reporteReservaDao=new CReporteReservaDAO();
	    System.out.println("el anio es:"+this.anio);
		reporteReservaDao.asignarPaquetesmasVendidos(reporteReservaDao.recuperarPaquetesMasVendidos(this.anio));
		this.setListaPaquetesMasVendidos(reporteReservaDao.getMasVendidosxMeses());
		System.out.println("el tamanio de la lista mas vendidos es:"+listaPaquetesMasVendidos.size());
		asignarAnioMeses(this.anio);
		//======meses del anio con inicio y fin=====
		String nombrePaqueteAnterior=listaPaquetesMasVendidos.get(0).getNombrePaquete();
		for(int i=0;i<listaPaquetesMasVendidos.size();i++)
		{
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(EneroInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(EneroFinal))
			{
				System.out.println("Estamos en enero");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[0]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[0]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[0]){
						sumasMeses[0]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[0]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(FebreroInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(FebreroFinal))
			{
				System.out.println("Estamos en febrero");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[1]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[1]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[1]){
						sumasMeses[1]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[1]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(MarzoInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(MarzoFinal))
			{
				System.out.println("Estamos en marzo");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[2]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[2]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[2]){
						sumasMeses[2]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[2]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(AbrilInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(AbrilFinal))
			{
				System.out.println("Estamos en abril");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[3]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[3]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[3]){
						sumasMeses[3]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[3]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(MayoInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(MayoFinal))
			{
				System.out.println("Estamos en mayo");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[4]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[4]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[4]){
						sumasMeses[4]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[4]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(JunioInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(JunioFinal))
			{
				System.out.println("Estamos en junio");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[5]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[5]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[5]){
						sumasMeses[5]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[5]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(JulioInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(JulioFinal))
			{
				System.out.println("Estamos en julio");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[6]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[6]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					System.out.println("vaklor de suma if: julio"+sumasMeses[6]);
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[6]){
						sumasMeses[6]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[6]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						System.out.println("vaklor de suma else: julio"+sumasMeses[6]);
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(AgostoInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(AgostoFinal))
			{
				System.out.println("Estamos en agosto");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[7]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[7]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					System.out.println("vaklor de suma: agosto"+sumasMeses[7]);
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[7]){
						sumasMeses[7]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[7]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(SetiembreInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(SetiembreFinal))
			{
				System.out.println("Estamos en setiembre");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[8]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[8]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[8]){
						sumasMeses[8]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[8]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(OctubreInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(OctubreFinal))
			{
				System.out.println("Estamos en octubre");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[9]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[9]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[9]){
						sumasMeses[9]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[9]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(NoviembreInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(NoviembreFinal))
			{
				System.out.println("Estamos en noviembre");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[10]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[10]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[10]){
						sumasMeses[10]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[10]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}else
			if(listaPaquetesMasVendidos.get(i).getFechaPago().after(DiciembreInicio) && listaPaquetesMasVendidos.get(i).getFechaPago().before(DiciembreFinal))
			{
				System.out.println("Estamos en diciembre");
				if(listaPaquetesMasVendidos.get(i).getNombrePaquete().equals(nombrePaqueteAnterior)){
					sumasMeses[11]+=listaPaquetesMasVendidos.get(i).getNroVentas();
					nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					nombresPaquetes[11]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
				}else
				{
					if(listaPaquetesMasVendidos.get(i).getNroVentas()>sumasMeses[11]){
						sumasMeses[11]=listaPaquetesMasVendidos.get(i).getNroVentas();
						nombrePaqueteAnterior=listaPaquetesMasVendidos.get(i).getNombrePaquete();
						nombresPaquetes[11]=listaPaquetesMasVendidos.get(i).getNombrePaquete();
					}
				}
			}
		}
	}
	public void asignarAnioMeses(String anioActual)
	{
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		eneroInicio=anioActual+"-01-01";
		eneroFinal=anioActual+"-01-31";
		febreroInicio=anioActual+"-02-01";
		febreroFinal=anioActual+"-02-31";
		marzoInicio=anioActual+"-03-01";
		marzoFinal=anioActual+"-03-31";
		abrilInicio=anioActual+"-04-01";
		abrilFinal=anioActual+"-04-31";
		mayoInicio=anioActual+"-05-01";
		mayoFinal=anioActual+"-05-31";
		junioInicio=anioActual+"-06-01";
		junioFinal=anioActual+"-06-31";
		julioInicio=anioActual+"-07-01";
		julioFinal=anioActual+"-07-31";
		agostoInicio=anioActual+"-08-01";
		agostoFinal=anioActual+"-08-31";
		setiembreInicio=anioActual+"-09-01";
		setiembreFinal=anioActual+"-09-31";
		octubreInicio=anioActual+"-10-01";
		octubreFinal=anioActual+"-10-31";
		noviembreInicio=anioActual+"-11-01";
		noviembreFinal=anioActual+"-11-31";
		diciembreInicio=anioActual+"-12-01";
		diciembreFinal=anioActual+"-12-31";
		
		try {
			 EneroInicio=formato.parse(eneroInicio);
			EneroFinal=formato.parse(eneroFinal);
			FebreroInicio=formato.parse(febreroInicio);
			FebreroFinal=formato.parse(febreroFinal);
			MarzoInicio=formato.parse(marzoInicio);
			MarzoFinal=formato.parse(marzoFinal);
			AbrilInicio=formato.parse(abrilInicio);
			AbrilFinal=formato.parse(abrilFinal);
		    MayoInicio=formato.parse(mayoInicio);
			MayoFinal=formato.parse(mayoFinal);
			JunioInicio=formato.parse(junioInicio);
			JunioFinal=formato.parse(junioFinal);
			JulioInicio=formato.parse(julioInicio);
			JulioFinal=formato.parse(julioFinal);
			AgostoInicio=formato.parse(agostoInicio);
			AgostoFinal=formato.parse(agostoFinal);
			SetiembreInicio=formato.parse(setiembreInicio);
			SetiembreFinal=formato.parse(setiembreFinal);
			OctubreInicio=formato.parse(octubreInicio);
			OctubreFinal=formato.parse(octubreFinal);
			NoviembreInicio=formato.parse(noviembreInicio);
			NoviembreFinal=formato.parse(noviembreFinal);
			DiciembreInicio=formato.parse(diciembreInicio);
			DiciembreFinal=formato.parse(diciembreFinal);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@NotifyChange("anio")
	public SimpleCategoryModel getGrafica(){
        System.out.println("entra aqui grafica..?");
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        SimpleCategoryModel demoModel = new SimpleCategoryModel();
            for(int j=0; j<12; j++){               
                if(j==0) {
                    demoModel.setValue(this.nombresPaquetes[j], meses[j], this.sumasMeses[j]);
                }
                if(j==1) {
                    demoModel.setValue(this.nombresPaquetes[j], meses[j], this.sumasMeses[j]);
                }
                if(j==2) {
                    demoModel.setValue(this.nombresPaquetes[j], meses[j], this.sumasMeses[j]);
                }
                if(j==3) {
                    demoModel.setValue(this.nombresPaquetes[j], meses[j], this.sumasMeses[j]);
                }
                if(j==4) {
                    demoModel.setValue(this.nombresPaquetes[j], meses[j], this.sumasMeses[j]);
                }
                if(j==5) {
                    demoModel.setValue(this.nombresPaquetes[j], meses[j], this.sumasMeses[j]);
                }
                if(j==6) {
                    demoModel.setValue(this.nombresPaquetes[j], meses[j], this.sumasMeses[j]);
                }
                if(j==7) {
                    demoModel.setValue(this.nombresPaquetes[j], meses[j], this.sumasMeses[j]);
                }
                if(j==8) {
                    demoModel.setValue(this.nombresPaquetes[j], meses[j], this.sumasMeses[j]);
                }
                if(j==9) {
                    demoModel.setValue(this.nombresPaquetes[j], meses[j], this.sumasMeses[j]);
                }
                if(j==10) {
                    demoModel.setValue(this.nombresPaquetes[j], meses[j], this.sumasMeses[j]);
                }
                if(j==11) {
                    demoModel.setValue(this.nombresPaquetes[j], meses[j], this.sumasMeses[j]);
                }
            }
        return demoModel;
    }
	
}
