package com.pricing.viewModel;

import java.util.Date;
import java.util.Calendar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;

import com.pricing.dao.CEtiquetaDAO;
import com.pricing.extras.lectorPDF;
import com.pricing.model.CDia;
import com.pricing.model.CDias7;
import com.pricing.util.Util;

public class disponibilidadVM 
{
	@Wire
	Combobox cbMes,cbAnio;
	@Wire
	Label lblUpdateDate;
	@Wire
	Comboitem cbAnioActual,cbAnioSig;
	//=======================================
	private ArrayList<String> listDispMes;
	private ArrayList<CDias7> listaDias;
	private ArrayList<CDia> listaAnioActual;
	private ArrayList<CDia> listaAnioSig;
	private CDias7 dias7;
	private ArrayList<String> listaUpdate;
	private ArrayList<String[]> listaFechasSeleccionadas;
	private String mesRecuperado;
	/***************************/
	private CEtiquetaDAO etiquetaDao;
	private String[] etiqueta;
	private String language;
	/***************************/
	Session ses;
	//=======================================
	public ArrayList<String[]> getListaFechasSeleccionadas() {
		return listaFechasSeleccionadas;
	}
	public void setListaFechasSeleccionadas(
			ArrayList<String[]> listaFechasSeleccionadas) {
		this.listaFechasSeleccionadas = listaFechasSeleccionadas;
	}
	public String getMesRecuperado() {
		return mesRecuperado;
	}
	public void setMesRecuperado(String mesRecuperado) {
		this.mesRecuperado = mesRecuperado;
	}
	public String[] getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String[] etiqueta) {
		this.etiqueta = etiqueta;
	}
	public ArrayList<CDias7> getListaDias() {
		return listaDias;
	}
	public void setListaDias(ArrayList<CDias7> listaDias) {
		this.listaDias = listaDias;
	}
	public CDias7 getDias7() {
		return dias7;
	}
	public void setDias7(CDias7 dias7) {
		this.dias7 = dias7;
	}
	//=======================================
	@Init
	public void inicializarVM() throws IOException
	{
		iniciarEtiquetas();
		/*******************/
		dias7=new CDias7();
		listaDias=new ArrayList<CDias7>();
		listDispMes=new ArrayList<String>();
		ses=Sessions.getCurrent();
		ses.setAttribute("fechaPrioridad",1);
		ses.setAttribute("cantidadFechas",0);
		//Inicializamos los dias y el numero de disponibilidades
		iniDiasYNumDisp();
		//===========================
		iniciarLosDiasAnio();
		//========================
		iniciarFechasSeleccionadas();
	}
	public void iniciarEtiquetas()
	{
		etiquetaDao=new CEtiquetaDAO();
		etiquetaDao.asignarEtiquetaIdiomas(etiquetaDao.recuperarEtiquetasBD());
		etiqueta=new String[etiquetaDao.getIdioma().getIdioma1().length];//Se asigna el tamaño de etiqueta
		
		
		language= Executions.getCurrent().getHeader("accept-language").split(",")[0];
		if(language.equals("es-ES"))
			etiqueta=etiquetaDao.getIdioma().getIdioma1();
		else if(language.equals("pt-BR") || language.equals("pt-PT"))
			etiqueta=etiquetaDao.getIdioma().getIdioma3();
		else
			etiqueta=etiquetaDao.getIdioma().getIdioma2();
		Sessions.getCurrent().setAttribute("etiqueta", etiqueta);
	}
	public void iniciarLosDiasAnio() throws IOException
	{
		listaAnioActual=new ArrayList<CDia>();
		listaAnioSig=new ArrayList<CDia>();
		listaUpdate=new ArrayList<String>();
		int nroDia=1;
		for(int i=0;i<337;i++)
		{
			CDia dia=new CDia();
			if(i==31 || i==62 || i==92 || i==123 || i==153 || 
					i==184 || i==215 || i==245 || i==276 || i==306)
				nroDia=1;
			dia.setcNroDia(""+nroDia);
			listaAnioActual.add(dia);
			CDia dia1=new CDia();
			dia1.setcNroDia(""+nroDia);
			dia1.setCantDisp("500");
//			dia1.setDisponible("/img/dispon/ok.png");
			dia1.setDisponible("icon-checkmark");
			dia1.setColorDisp("chek_style");
			dia1.setVisible(true);
			listaAnioSig.add(dia1);
			nroDia++;
		}
		int n=0;
		for(int i=0;i<12;i++)
		{
			System.out.println("pos:"+n);
			listDispMes=new ArrayList<String>();
			if(i==1)continue;//mes de febrero
			String mes=mesAnio(i);
			//=====Leemos el archivo del mes correspondiente=====
			String nameFileMes=txtMesCorrespondiente(mes);
			//si no existe el archivo pasamos a leer el siguiente archivo
			if(!(new File(Util.getPath()+nameFileMes)).exists())
				continue;
	        FileReader f = new FileReader(Util.getPath()+nameFileMes);
	        BufferedReader b = new BufferedReader(f);
	        String contenidoTxt="";
	        String cadena;
	        while((cadena = b.readLine())!=null) 
	        	contenidoTxt+=cadena+"\n";
	        b.close();
	        //Se procede a obtener los dias disponibles
			String[] s=contenidoTxt.split("\n");
		    for(int j=5;j<s.length-1;j++)
		    {
		    	String[] aux=s[j].split(" ");
		    	listDispMes.add(aux[1]);
		    }
		    listaUpdate.add(s[2]);
		    //Una vez obtenida las disponibilidades se almacena en la listaAnioActual
		    for(String disp:listDispMes)
		    {
			    listaAnioActual.get(n).setCantDisp(disp);
			    listaAnioActual.get(n).setVisible(true);
			    if(Integer.parseInt(disp)!=0)
			    {
			    	listaAnioActual.get(n).setDisponible("icon-checkmark");
			    	listaAnioActual.get(n).setColorDisp("chek_style");
			    	listaAnioActual.get(n).setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
			    }
//			    	listaAnioActual.get(n).setDisponible("/img/dispon/ok.png");
			    else
			    {
			    	listaAnioActual.get(n).setDisponible("icon-cross");
			    	listaAnioActual.get(n).setColorDisp("cross_style");
			    	listaAnioActual.get(n).setImgPrioridad("background:rgba(247, 87, 65,0.3);border-radius:5px;");
			    }
//			    	listaAnioActual.get(n).setDisponible("/img/dispon/x5.png");
			    n++;
		    }
		}
	}
	public void iniciarFechasSeleccionadas()
	{
		listaFechasSeleccionadas=new ArrayList<String[]>();
		for(int i=0;i<5;i++)
		{
			String[] fecha=new String[3];
			fecha[0]="";//dia
			fecha[1]="";//mes
			fecha[2]="";//año
			listaFechasSeleccionadas.add(fecha);
		}
	}
	public boolean selectMesValido(String mes,CDia oDia,String annio)
	{
		/*************Fecha Inicio*******************/
		Calendar calIni=Calendar.getInstance();
		calIni.set(Integer.parseInt(annio),nMesAnio(mes)-1,Integer.parseInt(oDia.getcNroDia()));
		/********Fecha Actual*******/
		Calendar calActual=Calendar.getInstance();
		/*****Comparando******/
		if(calIni.before(calActual))
			return false;
		else return true;
	}
	/**
	 * -Si hacemos click en un dia del calendario
	 * en el que la disponibilidad != 0 se debe de marcar
	 * la fecha con un color dependiendo de si la fecha es
	 * principal(1) o alterna(2)
	 * -Se puede elegir 1 fecha principal y max 4 fechas alternas
	 * @param fila: La fila al que pertenece el dia elegido
	 * @param dia: El dia elegido del mes
	 * @param cantDisp: La cantidad disponible en este dia
	 */
	@Command
	public void onDia(@BindingParam("dias")CDias7 dias7,@BindingParam("dia")CDia dia,@BindingParam("valueMes")Object mes)
	{
		if(!selectMesValido(mes.toString(),dia,cbAnio.getValue()))
			return;
		int n=(int)ses.getAttribute("cantidadFechas");
		//n<5 -> 1 fecha principal y 4 alternativas
		if(Integer.parseInt(dia.getCantDisp())!=0 && n<5)
		{
			int p=(int)ses.getAttribute("fechaPrioridad");
			String imagen="";
			String Descripcion="";
			if(p==1)
			{
//				imagen="/img/dispon/blue.jpg";
				imagen="background:#6E96E2;border-radius:5px;border:1px solid black;";
				Descripcion=etiqueta[44];
			}
			else
			{
				Descripcion=etiqueta[45];
//				imagen="/img/dispon/green.png";
				imagen="background:#C7D542;border-radius:5px;border:1px solid black;";
			}
					if(!dia.isElegido())
					{
						if(p==1)
							ses.setAttribute("fechaPrioridad",2);
						dia.setElegido(true);
						dia.setDescDiaElegido(Descripcion);
						dia.setImgPrioridad(imagen);
						dia.setPrioridad(p);
						
						refrescarDia(dias7,dia);
						//Se adiciona la fecha
						adicionarFechaSeleccionada(dia,p);
						n++;
					}
					else
					{
						if(dia.getPrioridad()==1)
						{
							ses.setAttribute("fechaPrioridad",1);
							dia.setElegido(false);
							dia.setDescDiaElegido("");
							dia.setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
							dia.setPrioridad(0);
							
							refrescarDia(dias7,dia);
							//Se quita la fecha principal
							eliminarFechaSeleccionada(dia,1);
							n--;
						}
						else
						{
							dia.setElegido(false);
							dia.setDescDiaElegido("");
							dia.setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
							dia.setPrioridad(0);
							
							refrescarDia(dias7,dia);
							//Se quita la fecha alterna
							eliminarFechaSeleccionada(dia,2);
							n--;
						}
					}
			ses.setAttribute("cantidadFechas",n);
		}
		else
		{
			if(n>=5 && Integer.parseInt(dia.getCantDisp())!=0)
			{
				for(int i=0;i<7;i++)
				{
					if(dia.isElegido())
					{
						if(dia.getPrioridad()==1)
						{
							ses.setAttribute("fechaPrioridad",1);
							dia.setElegido(false);
							dia.setDescDiaElegido("");
							dia.setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
							dia.setPrioridad(0);
							
							refrescarDia(dias7,dia);
							//Se quita la fecha principal
							eliminarFechaSeleccionada(dia,1);
							n--;
						}
						else
						{
							dia.setElegido(false);
							dia.setDescDiaElegido("");
							dia.setImgPrioridad("background:rgba(25, 206, 61,0.3);border-radius:5px;");
							dia.setPrioridad(0);
							
							refrescarDia(dias7,dia);
							//Se quita la fecha alterna
							eliminarFechaSeleccionada(dia,2);
							n--;
						}
					}
				}
				ses.setAttribute("cantidadFechas",n);
			}
		}
//		for(int i=0;i<listaFechasSeleccionadas.size();i++)
//			System.out.println("Fechas:"+listaFechasSeleccionadas.get(i)[0]+","+listaFechasSeleccionadas.get(i)[1]+","+listaFechasSeleccionadas.get(i)[2]);
//		System.out.println("soy la fila ->"+fila);
	}
	public void refrescarDia(CDias7 dias7,CDia dia)
	{
		if(dia.getNro()==1)
			BindUtils.postNotifyChange(null, null, dias7,"dia_1");
		else if(dia.getNro()==2)
			BindUtils.postNotifyChange(null, null, dias7,"dia_2");
		else if(dia.getNro()==3)
			BindUtils.postNotifyChange(null, null, dias7,"dia_3");
		else if(dia.getNro()==4)
			BindUtils.postNotifyChange(null, null, dias7,"dia_4");
		else if(dia.getNro()==5)
			BindUtils.postNotifyChange(null, null, dias7,"dia_5");
		else if(dia.getNro()==6)
			BindUtils.postNotifyChange(null, null, dias7,"dia_6");
		else if(dia.getNro()==7)
			BindUtils.postNotifyChange(null, null, dias7,"dia_7");
	}
	public void adicionarFechaSeleccionada(CDia dia,int prioridad)
	{
		if(prioridad==1)
		{
			//Se agrega la fecha principal
			listaFechasSeleccionadas.get(0)[0]=dia.getcNroDia();
			listaFechasSeleccionadas.get(0)[1]=mesRecuperado;
			listaFechasSeleccionadas.get(0)[2]=cbAnio.getValue();
		}
		else
		{
			for(int i=1;i<5;i++)
			{
				if(listaFechasSeleccionadas.get(i)[0].equals(""))
				{
					listaFechasSeleccionadas.get(i)[0]=dia.getcNroDia();
					listaFechasSeleccionadas.get(i)[1]=mesRecuperado;
					listaFechasSeleccionadas.get(i)[2]=cbAnio.getValue();
					break;
				}
			}
		}
		//-----------------------------
		BindUtils.postNotifyChange(null, null, this,"listaFechasSeleccionadas");
	}
	public void eliminarFechaSeleccionada(CDia dia,int prioridad)
	{
		if(prioridad==1)
		{
			//Se quita la fechaprincipal
			listaFechasSeleccionadas.get(0)[0]="";
			listaFechasSeleccionadas.get(0)[1]="";
			listaFechasSeleccionadas.get(0)[2]="";
		}
		else
		{
			for(int i=1;i<5;i++)
			{
				if(listaFechasSeleccionadas.get(i)[0].equals(dia.getcNroDia()))
				{
					listaFechasSeleccionadas.get(i)[0]="";
					listaFechasSeleccionadas.get(i)[1]="";
					listaFechasSeleccionadas.get(i)[2]="";
					break;
				}
			}
		}
		//-----------------------------
		BindUtils.postNotifyChange(null, null, this,"listaFechasSeleccionadas");
	}
	/**
	 * Obtiene el nombre del primer dia del mes en el año correspondiente
	 * @param anio
	 * @param mes
	 * @return
	 */
	public String obtenerPrimerDiaMes(int anio,int mes)
	{
		Calendar cal=Calendar.getInstance();
		cal.set(anio, mes-1,1);
		return cal.getTime().toString().substring(0, 3);
	}
	/**
	 * Obtiene el nro de dias del mes del anio correspondiente
	 * @param anio
	 * @param mes
	 * @return
	 */
	public int obtenerNroDiasMes(int anio,int mes)
	{
		Calendar cal=Calendar.getInstance();
		cal.set(anio,mes-1,1);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	/**
	 * Inicializa el grid con los dias y numero de disponibilidades vacios
	 */
	public void iniDiasYNumDisp()
	{
		listaDias=new ArrayList<CDias7>();
		for(int i=0;i<6;i++)
		{
			dias7=new CDias7();
			listaDias.add(dias7);
		}
		BindUtils.postNotifyChange(null, null, this,"listaDias");
	}
	public Date sumarRestarDiasFecha(Date fecha, int dias)
	{
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(fecha); // Configuramos la fecha que se recibe
		 calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
		 return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
	}
	/**
	 * Retorna el nro de dia a partir de su nombre
	 * @param dia
	 * @return
	 */
	public int diaSemana(String dia)
	{
		int d=-1;
		switch(dia)
		{
			case "Mon":d=1;break;
			case "Tue":d=2;break;
			case "Wed":d=3;break;
			case "Thu":d=4;break;
			case "Fri":d=5;break;
			case "Sat":d=6;break;
			case "Sun":d=7;break;
		}
		return d;
	}
	/**
	 * La primera forma de como se mostrara la disponibilidad
	 * @throws WrongValueException
	 * @throws IOException
	 */
	@NotifyChange({"dias","numDisp"})
	public void iniDisponibilidades() throws WrongValueException, IOException
	{
		Calendar calIni=Calendar.getInstance();
		cbAnio.setValue(Integer.toString(calIni.get(Calendar.YEAR)));
		//----------------------------
		cbAnioActual.setLabel(Integer.toString(calIni.get(Calendar.YEAR)));
		cbAnioSig.setLabel(Integer.toString(calIni.get(Calendar.YEAR)+1));
		//----------------------------
		cbMes.setValue(mesAnioIdioma(calIni.get(Calendar.MONTH)));
		mesRecuperado=mesAnio(calIni.get(Calendar.MONTH));
		//===================================
		mostrarCalendarDispAnioActual(cbAnio.getValue(),mesRecuperado);
	}
	public int obtenerPosInicioMes(int mes)
	{
		int pos=-1;
		switch(mes)
		{
			case 1:pos=0;break;
			case 3:pos=31;break;
			case 4:pos=62;break;
			case 5:pos=92;break;
			case 6:pos=123;break;
			case 7:pos=153;break;
			case 8:pos=184;break;
			case 9:pos=215;break;
			case 10:pos=245;break;
			case 11:pos=276;break;
			case 12:pos=306;break;
		}
		return pos;
	}
	public int obtenerPosFinMes(int mes)
	{
		int pos=-1;
		switch(mes)
		{
			case 1:pos=30;break;
			case 3:pos=61;break;
			case 4:pos=91;break;
			case 5:pos=122;break;
			case 6:pos=152;break;
			case 7:pos=183;break;
			case 8:pos=214;break;
			case 9:pos=244;break;
			case 10:pos=275;break;
			case 11:pos=305;break;
			case 12:pos=336;break;
		}
		return pos;
	}
	/**
	 * -Asigna los dias en su posicion correspondiente en el grid
	 * -Lee el pdf de disponibiidades del mes y año correspondiente
	 *  y asigna a numDisp el nro de disponibilidades
	 * -Todos los datos obtenidos son del año actual
	 * @param anio
	 * @param mes
	 * @throws IOException
	 */
	public void mostrarCalendarDispAnioActual(String anio,String mes) throws IOException
	{
		//Inicializamos los dias y nro disponibilidades en el grid todos en vacio
		iniDiasYNumDisp();
		//==================
		int m=nMesAnio(mes);
		int a=Integer.parseInt(anio);
		//Obtenemos el primer dia del mes seleccionado
		String primerDiaMes=obtenerPrimerDiaMes(a,m);
		int nroDiaSemana=diaSemana(primerDiaMes);
	    //Se escriben los datos en el grid a partir del primer dia obtenido anteriormente
	    int posInicioMes=obtenerPosInicioMes(m);
	    int posFinMes=obtenerPosFinMes(m);
	    int k=posInicioMes;
	    for(int i=0;i<listaDias.size();i++)
	    {
	    	System.out.println("posAdvance->"+k);
	    	if(k>posFinMes)break;
	    	if(i==0)
	    	{
	    		if(nroDiaSemana==1)
	    		{
	    			listaAnioActual.get(k).setNro(1);
	    			listaDias.get(i).setDia_1(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(2);
	    			listaDias.get(i).setDia_2(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(3);
	    			listaDias.get(i).setDia_3(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    		}
	    		else if(nroDiaSemana==2)
	    		{
	    			listaAnioActual.get(k).setNro(2);
	    			listaDias.get(i).setDia_2(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(3);
	    			listaDias.get(i).setDia_3(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    		}
	    		else if(nroDiaSemana==3)
	    		{
	    			listaAnioActual.get(k).setNro(3);
	    			listaDias.get(i).setDia_3(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    		}
	    		else if(nroDiaSemana==4)
	    		{
	    			listaAnioActual.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    		}
	    		else if(nroDiaSemana==5)
	    		{
	    			listaAnioActual.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    		}
	    		else if(nroDiaSemana==6)
	    		{
	    			listaAnioActual.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioActual.get(k++));
	    			listaAnioActual.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    		}
	    		else if(nroDiaSemana==7)
	    		{
	    			listaAnioActual.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    		}
	    	}
	    	else
	    	{
	    		if(k>posFinMes)break;
	    		listaAnioActual.get(k).setNro(1);
	    		listaDias.get(i).setDia_1(listaAnioActual.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioActual.get(k).setNro(2);
	    		listaDias.get(i).setDia_2(listaAnioActual.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioActual.get(k).setNro(3);
	    		listaDias.get(i).setDia_3(listaAnioActual.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioActual.get(k).setNro(4);
	    		listaDias.get(i).setDia_4(listaAnioActual.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioActual.get(k).setNro(5);
	    		listaDias.get(i).setDia_5(listaAnioActual.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioActual.get(k).setNro(6);
	    		listaDias.get(i).setDia_6(listaAnioActual.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioActual.get(k).setNro(7);
	    		listaDias.get(i).setDia_7(listaAnioActual.get(k++));
	    	}
	    }
	    if(m==1)
	    	lblUpdateDate.setValue(listaUpdate.get(m-1));
	    else
	    	lblUpdateDate.setValue(listaUpdate.get(m-2));
		//-------------------------------------------------
		BindUtils.postNotifyChange(null, null, this,"listaDias");
	}
	/**
	 * -Se asignan datos por defecto en el grid correspondientes al año siguiente del actual
	 * -A cada disponibilidad se le asigna un valor por defecto de 500
	 * @param anio
	 * @param mes
	 */
	public void mostrarCalendarDispAnioSiguiente(String anio,String mes)
	{
		//Inicializamos los dias y nro disponibilidades en el grid todos en vacio
		//==================
		int m=nMesAnio(mes);
		int a=Integer.parseInt(anio);
		iniDiasYNumDisp();
		//Obtenemos el primer dia y el numero de dias del mes seleccionado
		String primerDiaMes=obtenerPrimerDiaMes(a,m);
		int nroDiaSemana=diaSemana(primerDiaMes);
		//===================================
		lblUpdateDate.setValue("");
		//Se asignan las disponibilidades por defecto
		int posInicioMes=obtenerPosInicioMes(m);
	    int posFinMes=obtenerPosFinMes(m);
	    int k=posInicioMes;
	    for(int i=0;i<listaDias.size();i++)
	    {
	    	if(k>posFinMes)break;
	    	if(i==0)
	    	{
	    		if(nroDiaSemana==1)
	    		{
	    			listaAnioSig.get(k).setNro(1);
	    			listaDias.get(i).setDia_1(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(2);
	    			listaDias.get(i).setDia_2(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(3);
	    			listaDias.get(i).setDia_3(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    		}
	    		else if(nroDiaSemana==2)
	    		{
	    			listaAnioSig.get(k).setNro(2);
	    			listaDias.get(i).setDia_2(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(3);
	    			listaDias.get(i).setDia_3(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    		}
	    		else if(nroDiaSemana==3)
	    		{
	    			listaAnioSig.get(k).setNro(3);
	    			listaDias.get(i).setDia_3(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    		}
	    		else if(nroDiaSemana==4)
	    		{
	    			listaAnioSig.get(k).setNro(4);
	    			listaDias.get(i).setDia_4(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    		}
	    		else if(nroDiaSemana==5)
	    		{
	    			listaAnioSig.get(k).setNro(5);
	    			listaDias.get(i).setDia_5(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    		}
	    		else if(nroDiaSemana==6)
	    		{
	    			listaAnioSig.get(k).setNro(6);
	    			listaDias.get(i).setDia_6(listaAnioSig.get(k++));
	    			listaAnioSig.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    		}
	    		else if(nroDiaSemana==7)
	    		{
	    			listaAnioSig.get(k).setNro(7);
	    			listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    		}
	    	}
	    	else
	    	{
	    		if(k>posFinMes)break;
	    		listaAnioSig.get(k).setNro(1);
	    		listaDias.get(i).setDia_1(listaAnioSig.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioSig.get(k).setNro(2);
	    		listaDias.get(i).setDia_2(listaAnioSig.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioSig.get(k).setNro(3);
	    		listaDias.get(i).setDia_3(listaAnioSig.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioSig.get(k).setNro(4);
	    		listaDias.get(i).setDia_4(listaAnioSig.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioSig.get(k).setNro(5);
	    		listaDias.get(i).setDia_5(listaAnioSig.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioSig.get(k).setNro(6);
	    		listaDias.get(i).setDia_6(listaAnioSig.get(k++));
	    		if(k>posFinMes)break;
	    		listaAnioSig.get(k).setNro(7);
	    		listaDias.get(i).setDia_7(listaAnioSig.get(k++));
	    	}
	    }
		//-------------------------------------------------
		BindUtils.postNotifyChange(null, null, this,"listaDias");
	}
	/**
	 * Retorna el nombre del pdf correspondiente al parametro mes
	 * @param mes
	 * @return
	 */
	public String txtMesCorrespondiente(String mes)
	{
		String nameFile="";
		switch(mes)
		{
			case "Enero":nameFile="enero.txt";break;
			case "Marzo":nameFile="marzo.txt";break;
			case "Abril":nameFile="abril.txt";break;
			case "Mayo":nameFile="mayo.txt";break;
			case "Junio":nameFile="junio.txt";break;
			case "Julio":nameFile="julio.txt";break;
			case "Agosto":nameFile="agosto.txt";break;
			case "Setiembre":nameFile="setiembre.txt";break;
			case "Octubre":nameFile="octubre.txt";break;
			case "Noviembre":nameFile="noviembre.txt";break;
			case "Diciembre":nameFile="diciembre.txt";break;
		}
		return nameFile;
	}
	/**
	 * Obtiene el nro de mes a partir de su nombre
	 * @param mes
	 * @return
	 */
	public int nMesAnio(String mes)
	{
		int n=-1;
		switch(mes)
		{
			case "Enero":n=1;break;
			case "Marzo":n=3;break;
			case "Abril":n=4;break;
			case "Mayo":n=5;break;
			case "Junio":n=6;break;
			case "Julio":n=7;break;
			case "Agosto":n=8;break;
			case "Setiembre":n=9;break;
			case "Octubre":n=10;break;
			case "Noviembre":n=11;break;
			case "Diciembre":n=12;break;
		}
		return n;
	}
	/**
	 * Obtiene el nombre del mes a partir de su nro de mes
	 * @param mes
	 * @return
	 */
	public String mesAnio(int mes)
	{
		String rMes="";
		switch(mes+1)
		{
			case 1:rMes="Enero";break;
			case 3:rMes="Marzo";break;
			case 4:rMes="Abril";break;
			case 5:rMes="Mayo";break;
			case 6:rMes="Junio";break;
			case 7:rMes="Julio";break;
			case 8:rMes="Agosto";break;
			case 9:rMes="Setiembre";break;
			case 10:rMes="Octubre";break;
			case 11:rMes="Noviembre";break;
			case 12:rMes="Diciembre";break;
		}
		return rMes;
	}
	public String mesAnioIdioma(int mes)
	{
		String rMes="";
		switch(mes+1)
		{
			case 1:rMes=etiqueta[24];break;
			case 3:rMes=etiqueta[26];break;
			case 4:rMes=etiqueta[27];break;
			case 5:rMes=etiqueta[28];break;
			case 6:rMes=etiqueta[29];break;
			case 7:rMes=etiqueta[30];break;
			case 8:rMes=etiqueta[31];break;
			case 9:rMes=etiqueta[32];break;
			case 10:rMes=etiqueta[33];break;
			case 11:rMes=etiqueta[34];break;
			case 12:rMes=etiqueta[35];break;
		}
		return rMes;
	}
	@Command
	public void changeMonth(@BindingParam ("valueMes") Object valueMes) throws WrongValueException, IOException
	{
		String mes=valueMes.toString();
		mesRecuperado=mes;
		Calendar cal=Calendar.getInstance();
		//Si el año del cbAnio es = al año actual obtenido se procede a mostrar los datos reales
		if(Integer.toString(cal.get(Calendar.YEAR)).equals(cbAnio.getValue()))
			mostrarCalendarDispAnioActual(cbAnio.getValue(),mes);
		else
			mostrarCalendarDispAnioSiguiente(Integer.toString(cal.get(Calendar.YEAR)+1),mes);
	}
	@Command
	public void changeAnio(@BindingParam("valueAnio")String anio) throws WrongValueException, IOException
	{
		Calendar cal=Calendar.getInstance();
		System.out.println("El año se cambio a =>"+anio+"------->"+Integer.toString(cal.get(Calendar.YEAR)));
		//Si el año del cbAnio es = al año actual obtenido se procede a mostrar los datos reales
		if(Integer.toString(cal.get(Calendar.YEAR)).equals(anio))
		{
			mostrarCalendarDispAnioActual(anio,mesRecuperado);
		}
		else
			mostrarCalendarDispAnioSiguiente(anio,mesRecuperado);
	}
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws WrongValueException, IOException
	{
		Selectors.wireComponents(view, this, false);
		iniDisponibilidades();
	}
}
