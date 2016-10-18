package com.pricing.dao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.pricing.model.CUsuarioLogin;

public class CUsuarioLoginDAO extends CConexion implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CUsuarioLogin usuario;
	//====CONSTRUCTORES=====
	public CUsuarioLoginDAO(){
		super();
		usuario=new CUsuarioLogin();
	}
	//====GETTER AND SETTER====
	public CUsuarioLogin getUsuario() {
		return usuario;
	}
	public void setUsuario(CUsuarioLogin usuario) {
		this.usuario = usuario;
	}
	//--------------------------
	//=======METODOS==========
	public List validarLogin()
	{
		Object[] user={usuario.getcUsuarioCod(),usuario.getcClave()};
		return ejecutarProcedimiento("ag_sp_validarlogin", user);
	}
	public List obtenerSistemasPerfil(String subsistema)
	{
		return ejecutarProcedimiento("CP_sp_SistemasPerfil_Listar", new String[]{Integer.toString(usuario.getnPerfilCod()),subsistema});
	}
    /*METODOS REDEFINIDOS*/
    public List ejecutarProcedimiento(String procedimiento){
    	return getEjecutorSQL().ejecutarProcedimiento(procedimiento);
    }
    public List ejecutarProcedimiento(String procedimiento,Object[] values){
    	return getEjecutorSQL().ejecutarProcedimiento(procedimiento, values);
    }
}
