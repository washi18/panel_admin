package com.pricing.viewModel;


import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import pe.com.erp.crypto.Encryptar;

import com.pricing.extras.ServicioAutentificacion;
/////imagenes
public class LoginVM {

	private String tbNombreUsuario;
	private String tbContrasenia;
	private String lblMensaje;
	ServicioAutentificacion auth;
	boolean falloAutenticasion=false;
	@Wire("#tbUserName")
	Textbox tbUserName;
	
	public ServicioAutentificacion getAuth() {
		return auth;
	}

	public void setAuth(ServicioAutentificacion auth) {
		this.auth = auth;
	}

	private static final long serialVersionUID = 1L;

	BufferedImage foto;
	
	public BufferedImage getfoto() {
		return foto;
	}

	public void setfoto(BufferedImage foto) {
		this.foto = foto;
	}
	public String getTbNombreUsuario() {
		return tbNombreUsuario;
	}

	public void setTbNombreUsuario(String tbNombreUsuario) {
		this.tbNombreUsuario = tbNombreUsuario;
	}

	public String getTbContrasenia() {
		return tbContrasenia;
	}

	public void setTbContrasenia(String tbContrasenia) {
		this.tbContrasenia = tbContrasenia;
	}	
	public boolean isFalloAutenticasion() {
		return falloAutenticasion;
	}

	public void setFalloAutenticasion(boolean falloAutenticasion) {
		this.falloAutenticasion = falloAutenticasion;
	}
	public String getLblMensaje() {
		return lblMensaje;
	}

	public void setLblMensaje(String lblMensaje) {
		this.lblMensaje = lblMensaje;
	}

	@Init
	public void Autentificacion()
	{
		auth=new ServicioAutentificacion();
	}
	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		tbUserName.setFocus(true);
	}
	@NotifyChange({"falloAutenticasion","lblMensaje"})
	@Command
	public void doLogin()
	{		 
		try
		{
			Encryptar encrip= new Encryptar();
			System.out.println("Aqui esta la contraseña desencriptada-->"+encrip.decrypt("cyS249O3OHZTsG0ww1rYrw=="));
			System.out.println("Este es el username y la contraseña->"+tbNombreUsuario+"-->"+tbContrasenia);
//			Object[] ResultadosLogin=auth.login(tbNombreUsuario,tbContrasenia);
			System.out.println("necesito la contrasenia sin encriptar"+encrip.encrypt(tbContrasenia));
			Object[] ResultadosLogin=auth.login(tbNombreUsuario,tbContrasenia);
			if((boolean)ResultadosLogin[0])
			{
				System.out.println("Entre----->");
				Execution exec = Executions.getCurrent();
				HttpSession ses = (HttpSession)Sessions.getCurrent().getNativeSession();
				String user=encrip.encrypt( tbNombreUsuario);
				String clave=encrip.encrypt(tbContrasenia);
			    ses.setAttribute("usuario", user);
			    ses.setAttribute("clave", clave);
//			    exec.sendRedirect("ValidarSesion/ServletSesion", false);
//				exec.sendRedirect("../GPS/", false);
			   
				exec.sendRedirect("../panel_admin/?var1="+user+"&var2="+clave,false);
			    exec.setVoided(true);
		    }else{				
				tbUserName.setFocus(true);
				falloAutenticasion=true;
				lblMensaje=ResultadosLogin[2].toString();
			}
		 }
		 catch (Exception e){
			e.printStackTrace();
			Clients.showNotification("TIENE PROBLEMAS DE CONEXION : "+e.getMessage(), true);
		 }
	}
	public void enviarEmail(){
		
	}
	public BufferedImage newimg(String url) 
	{
		try
		{
	    BufferedImage before = ImageIO.read(new File(url));
	    float wf = 88/((float)before.getWidth());
	    float hf = 88/((float)before.getHeight());
	    BufferedImage after = new BufferedImage(88, 88, BufferedImage.TYPE_INT_ARGB);
	    AffineTransform at = new AffineTransform();
	    at.scale(wf, hf);
	    AffineTransformOp scaleOp = 
	    new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
	    after = scaleOp.filter(before, after);
	    Ellipse2D circle= new Ellipse2D.Float(0,0,88,88);
	    BufferedImage out = new BufferedImage( 88, 88, after.getType() );
	    Graphics g = out.getGraphics();
	    g.setClip( circle );
	    g.drawImage( after, 0, 0,null );
	    g.dispose();
	    //foto=out;
	    return out;
	    }
		catch(Exception Ex){
			return null;
		}
	}
}

