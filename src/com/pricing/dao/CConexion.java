package com.pricing.dao;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pricing.entidades.CResultado;
import com.pricing.util.EjecutorSQL;
public class CConexion implements CEntidadDAO 
{
	private DataSource ds;
    private EjecutorSQL ejec;
   // private DriverManagerDataSource dsmanager;
    private Boolean estaconectado;
   // private Connection conexion;
    
    public DataSource getDataSource(){    	
    	return ds;
    }
    public EjecutorSQL getEjecutorSQL(){
    	return ejec;
    }
    public CConexion(){    	
    	try {
    		ApplicationContext ap=new ClassPathXmlApplicationContext("com/pricing/resources/applicationContext.xml");
            ds=(DataSource) ap.getBean("dataSource");   
            ejec=EjecutorSQL.getEjecutorSQL(ds);
		} catch (Exception e) {
			// TODO: handle exception
			estaconectado=false;
			System.out.println(e.toString());
		}
    }
	public Boolean getEstaconectado() {
		return estaconectado;
	}	
	
	public CResultado guardarEntidad(){							
		return null;		
	}
    public CResultado eliminarEntidad(){
    	return null;
    }
   /* public int[] mantenimientoMasivo(String procedimiento,String[][] params){
    	return  getEjecutorSQL().ejecutarMantenimientoMasivo(procedimiento, params);
    }
    public List ejecutarProcedimiento(String procedimiento){
    	return getEjecutorSQL().ejecutarProcedimiento(procedimiento);
    }*/
    public List ejecutarProcedimiento(String procedimiento,String[] values){
    	return getEjecutorSQL().ejecutarProcedimiento(procedimiento, values);
    }
    /*public List ejecutarConsulta(String consulta){
    	return getEjecutorSQL().ejecutarConsulta(consulta);
    }*/
	
}
