
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarPaquete
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:05/19/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarPaquete(codPaquete varchar(10))
  RETURNS SETOF tpaquete AS
$$
	select * from tpaquete where cpaquetecod=$1 and bestado=true;
$$
  LANGUAGE sql;
  
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarImpuesto
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:08/04/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Mostrar los impuesto
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarImpuesto()
  RETURNS SETOF timpuesto AS
$$
	select * from timpuesto;
$$
  LANGUAGE sql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarEtiquetas
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_MostrarEtiquetas
()
returns setof tetiqueta as 
$$
	select * from tetiqueta
	order by codEtiqueta;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarServicios
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_MostrarServicios
()
returns setof tservicio as 
$$
	select * from tservicio where bestado=true
	order by nserviciocod;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_MostrarTodosServicios()
  RETURNS SETOF tservicio AS
$$
	select * from tservicio
	order by nserviciocod;
$$
  LANGUAGE sql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_MostrarServiciosconSubServicios
()
returns setof tservicio as 
$$
	select * from tservicio where crestriccionyesno=0 and crestriccionnum=0
	order by nserviciocod;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_MostrarSubServiciosconServiciosNombre
()
returns table (nsubserviciocod int,nserviciocod int,csubservicioindioma1 varchar(200),csubservicioindioma2 varchar(200),csubservicioindioma3 varchar(200),
	                csubservicioindioma4 varchar(200),csubservicioindioma5 varchar(200),cdescripcionidioma1 text,cdescripcionidioma2 text,cdescripcionidioma3 text,
			cdescripcionidioma4 text,cdescripcionidioma5 text,curlimg varchar(200),clink text,nprecioservicio decimal(10,2),bestado boolean,nservicionombre varchar(200)) as 
$$
	select a.nsubserviciocod,a.nserviciocod,a.csubservicioindioma1,a.csubservicioindioma2,a.csubservicioindioma3,
	a.csubservicioindioma4,a.csubservicioindioma5,a.cdescripcionidioma1,a.cdescripcionidioma2,a.cdescripcionidioma3,
	a.cdescripcionidioma4,a.cdescripcionidioma5,a.curlimg,a.clink,a.nprecioservicio,a.bestado,b.cservicioindioma1 
	from  tsubservicio AS a LEFT OUTER JOIN tservicio AS b ON(a.nserviciocod=b.nserviciocod);
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarPaquetes
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_MostrarPaquetes
()
returns setof tpaquete as 
$$
	select * from tpaquete where bestado=true
	order by cpaquetecod;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarSubServicios
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_MostrarSubServicios
()
returns setof tsubservicio as 
$$
	select * from tsubservicio where bestado=true
	order by nsubserviciocod;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarTodosHoteles
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_MostrarTodosHoteles
()
returns setof thotel as 
$$
	select * from thotel
	order by nhotelcod;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++*/
create or replace function Pricing_sp_MostrarTodosSuServicios
()
returns setof tsubservicio as
$$
	select * from tsubservicio
	order by nsubserviciocod;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarImpuesto
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_MostrarImpuesto
()
returns setof timpuesto as 
$$
	select * from timpuesto;
$$ 
LANGUAGE SQL;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarDestino
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION pricing_sp_mostrardestinos()
  RETURNS SETOF tdestino AS
$$
	select * from tdestino where bestado=true;
$$
  LANGUAGE sql;
  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarTodosDestino
Utilizado en	:Pagina Web PGT
Usuario		:PGT
Fecha Creacion	:6/07/2016
Ejecucion	:SELECT * FROM Pricing_sp_MostrarEtiquetas()
Eliminacion	:DROP FUNCTION Pricing_sp_MostrarEtiquetas()
Comentario	:Retorna todas las etiquetas del Pricing
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION pricing_sp_mostrarTodosdestinos()
  RETURNS SETOF tdestino AS
$$
	select * from tdestino
	order by ndestinocod;
$$
  LANGUAGE sql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarHotelesDestino
+++++++++++++++++++++++++++++++++++++++++++++++++*/ 
  create or replace function Pricing_sp_MostrarHotelesDestino
  (
  	codDestino int
  )
  returns setof thotel as
  $$
  	select * from thotel
  	where ndestinocod=$1;
  $$
  language sql;
  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarDestino
+++++++++++++++++++++++++++++++++++++++++++++++++*/ 
  create or replace function Pricing_sp_MostrarDestino
  (
  	codDestino int
  )
  returns setof tdestino as
  $$
  	select * from tdestino
  	where ndestinocod=$1;
  $$
  language sql;
 /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_MostrarTodasReservas
+++++++++++++++++++++++++++++++++++++++++++++++++*/ 
create or replace function Pricing_sp_MostrarTodasReservas()
returns setof treserva as
$$
	select * from treserva;
$$
LANGUAGE SQL;
 /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BuscarReservasEntreFechas
Detalle     : Esta fue la primera version que recupera todo en golpe pero fue reemplazada
+++++++++++++++++++++++++++++++++++++++++++++++++*/
    CREATE OR REPLACE FUNCTION Pricing_sp_BuscarReservas(
	fechaInicio varchar(12),
	fechaFin varchar(12)
)
RETURNS table (creservacod varchar(12),dfechainicio Date,dfechafin Date,dfecha timestamp,categoriaHotelcod int,ccontacto varchar(12),
cemail varchar(100),ctelefono varchar(50),nnropersonas int,npreciopaquetepersona numeric,ctituloidioma1 varchar(200),
ccategoriaidioma1 varchar(200),cestado varchar(20)) AS
$$
	select r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,COALESCE( c.categoriahotelcod, 0 ),r.ccontacto,r.cemail,r.ctelefono,r.nnropersonas,r.npreciopaquetepersona,
		p.ctituloidioma1,c.ccategoriaidioma1,r.cestado
			from treserva as r 
			left join treservapaqueteservicio as rps on(r.creservacod=rps.creservacod) 
			left join tpaqueteservicio as ps on(rps.codpaqueteservicio=ps.codpaqueteservicio)
			left join treservapaquetecategoriahotel as pch on(r.creservacod=pch.creservacod)
			left join tpaquetecategoriahotel as pc on (pch.codpaquetecategoriah=pc.codpaquetecategoriah)
			left join tcategoriahotel as c on(pc.categoriahotelcod=c.categoriahotelcod)
			left join tpaquete as p on(ps.cpaquetecod=p.cpaquetecod)
			where (r.dfecha between to_date($1,'yyyy-MM-dd') and to_date($2,'yyyy-MM-dd'))
			group by r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,c.categoriahotelcod,r.ccontacto,r.cemail,r.ctelefono,r.nnropersonas,r.npreciopaquetepersona,
					p.ctituloidioma1,c.ccategoriaidioma1,r.cestado
			order by r.creservacod;
$$
  LANGUAGE sql;
 /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BuscarPagosEntreFechasBD
Detalle     :La busqueda se realiza en los distintos formas de pagos(VISA, PAYPAL) 
+++++++++++++++++++++++++++++++++++++++++++++++++*/
select Pricing_sp_BuscarPagosPaypalEntreFechasBD('2016-07-20','2016-08-28','PENDIENTE DE PAGO');
  select Pricing_sp_BuscarPagosPaypalEntreFechasBD('2016-07-20','2016-08-12','PENDIENTE DE PAGO');
 /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BuscarDestinosReserva
Detalle     :Realiza la busqueda de destinos de acuerdo ala reserva donde se encuentre
+++++++++++++++++++++++++++++++++++++++++++++++++*/
create or replace function Pricing_sp_BuscarDestinosReserva
(
  codReserva varchar(12)
)
RETURNS table (cdestino varchar(100),ncodpostal int,ndestinocod int) AS
$$
	select d.cdestino, d.ncodpostal,d.ndestinocod 
			from treservapaqueteservicio as rp 
			inner join tpaqueteservicio as ps on(rp.codpaqueteservicio=ps.codpaqueteservicio)
			inner join tpaquete as p on(ps.cpaquetecod=p.cpaquetecod)
			inner join tpaquetedestino as pd on(p.cpaquetecod=pd.cpaquetecod)
			inner join tdestino as d on(pd.ndestinocod=d.ndestinocod)
			where (rp.creservacod=$1 and d.bestado=true)
			group by d.cdestino, d.ncodpostal,d.ndestinocod
			order by d.cdestino;
$$
  LANGUAGE sql;


 select Pricing_sp_BuscarDestinosReserva('R000000002');
 
  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BuscarServiciosReserva
Detalle     :Realiza la busqueda de servicios de acuerdo ala reserva donde se encuentre
+++++++++++++++++++++++++++++++++++++++++++++++++*/

 create or replace function Pricing_sp_BuscarServiciosReserva
(
  codReserva varchar(12)
)
RETURNS table (cservicioindioma1 varchar(200),nprecioservicio decimal(10,2)) AS
$$
	select s.cservicioindioma1, s.nprecioservicio 
			from treservapaqueteservicio as rp 
			inner join tpaqueteservicio as ps on(rp.codpaqueteservicio=ps.codpaqueteservicio)
			inner join tservicio as s on(ps.nserviciocod=s.nserviciocod)
			where (rp.creservacod=$1 and s.bestado=true)
			group by s.cservicioindioma1, s.nprecioservicio 
			order by s.cservicioindioma1;
$$
  LANGUAGE sql;

  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BuscarServiciosReserva
Detalle     :Realiza la busqueda de servicios de acuerdo ala reserva donde se encuentre
+++++++++++++++++++++++++++++++++++++++++++++++++*/
  create or replace function Pricing_sp_BuscarServiciosReserva
(
  codReserva varchar(12)
)
RETURNS table (csubservicioindioma1 varchar(200),nprecioservicio decimal(10,2),cservicioindioma1 varchar(200)) AS
$$
	select ss.csubservicioindioma1, ss.nprecioservicio ,s.cservicioindioma1
			from treservapaqueteservicio as rp 
			inner join tpaqueteservicio as ps on(rp.codpaqueteservicio=ps.codpaqueteservicio)
			inner join tservicio as s on(ps.nserviciocod=s.nserviciocod)
			inner join tsubservicio as ss on(ss.nserviciocod=s.nserviciocod)
			where (rp.creservacod=$1 and ss.bestado=true)
			group by ss.csubservicioindioma1, ss.nprecioservicio, s.cservicioindioma1 
			order by s.cservicioindioma1;
$$
  LANGUAGE sql;
 /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BuscarHotelesReserva
Detalle     :Realiza la busqueda de hoteles de acuerdo ala reserva donde se encuentre
+++++++++++++++++++++++++++++++++++++++++++++++++*/
   create or replace function Pricing_sp_BuscarHotelesReserva
(
  codReserva varchar(12),
  categoriaHotel int
)
RETURNS table (chotel varchar(200),npreciosimple decimal(10,2),npreciodoble decimal(10,2),npreciotriple decimal(10,2),cdestino varchar(100)) AS
$$
	select h.chotel, h.npreciosimple,h.npreciodoble,h.npreciotriple,d.cdestino 
			from treservapaqueteservicio as rp 
			inner join tpaqueteservicio as ps on(rp.codpaqueteservicio=ps.codpaqueteservicio)
			inner join tpaquete as p on(ps.cpaquetecod=p.cpaquetecod)
			inner join tpaquetedestino as pd on(p.cpaquetecod=pd.cpaquetecod)
			inner join tdestino as d on(pd.ndestinocod=d.ndestinocod)
			inner join tdestinohotel as dh on(d.ndestinocod=dh.ndestinocod)
			inner join thotel as h on(dh.nhotelcod=h.nhotelcod)
			where (rp.creservacod=$1 and d.bestado=true and h.categoriahotelcod=$2)
			group by h.chotel, h.npreciosimple,h.npreciodoble,h.npreciotriple,d.cdestino
			order by h.chotel;
$$
  LANGUAGE sql;
 /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BuscarReserva
Detalle     :Realiza la busqueda general de datos de la reserva
+++++++++++++++++++++++++++++++++++++++++++++++++*/  
  /*****martes****/
  CREATE OR REPLACE function AG_sp_ValidarLogin
(
	pcUsuarioCod varchar(150),
	pcClave varchar(128)
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		cUsuarioCod varchar(150),
		cClave varchar(128),
		imgUsuario varchar(200),
		nPerfilCod int,
		cPerfilIdioma1 varchar(100),
		cNroDoc varchar(12),
		cNombres varchar(150),
		cSexo char(1),
		dFechaNac date,
		cCelular varchar(50),
		dFechaSistema date) as		
$$
begin
	if (select count (1) from tusuario u where u.cusuariocod =$1)>0 then
	begin
		if (select count (1) from tusuario u where u.cusuariocod =$1 and u.cclave=$2)>0 then
		begin
			resultado='correcto';
			mensaje='Acceso Autorizado';
			dFechaSistema=now();
			return Query select resultado,mensaje,u.cusuariocod,u.cclave,u.imgusuario,u.nperfilcod,
				p.cperfilidioma1,
				u.cnrodoc,u.cnombres,u.csexo,u.dfechanac,u.ccelular,dFechaSistema
			from tusuario u
			inner join tperfil p on u.nperfilcod=p.nperfilcod
			where u.cusuariocod =$1 and u.cClave=$2;
		end;
		else
		begin
			resultado='error';
			mensaje='Clave Incorrecta';
			nperfilcod =0;
			cperfilidioma1='';
			cnrodoc='';
			cnombres='';
			csexo='';
			ccelular='';
			dFechaSistema=now();
			return Query select resultado,mensaje,$1 as cusuariocod,$2 as cclave,imgusuario,nperfilcod,
					cperfilidioma1,cnrodoc,cnombres,csexo,to_date(to_char(now(), 'YYYY-MM-DD'), 'YYYY-MM-DD') as dfechanac ,ccelular,
					dFechaSistema;
		end;
		end if;
	end;
	else
	begin
		resultado='error';
		mensaje='Usuario No Registrado';
		nperfilcod =0;
		cperfilidioma1='';
		cnrodoc='';
		cnombres='';
		csexo='';
		ccelular='';
		dFechaSistema=now();
		return Query select resultado,mensaje,$1 as cusuariocod,$2 as cclave,imgusuario,nperfilcod,
				cperfilidioma1,
				cnrodoc,cnombres,csexo,to_date(to_char(now(), 'YYYY-MM-DD'), 'YYYY-MM-DD') as dfechanac ,ccelular,
				dFechaSistema;
	end;
	end if;
end
$$ 
LANGUAGE plpgsql;


select count (1) from tusuario u where u.cusuariocod ='73077306' and u.cClave='veamos'




 create or replace function Pricing_sp_BuscarPagosVisaEntreFechasBD
(
	fechaInicio varchar(12),
	fechaFin varchar(12),
	estadoPago varchar(10)
)
RETURNS table (creservacod varchar(12),dfechainicio Date,dfechafin Date,dfecha timestamp,categoriahotelcod int,npreciopaquetepersona numeric,nnropersonas int,ctituloidioma1 varchar(200),nimporte numeric,nporcentaje numeric,
formapago varchar(20),estado varchar(10),fechayhora_initx timestamp ,nro_tarjeta varchar(20),
codtransaccion varchar(20),nom_th varchar(100),cestado varchar(20),impuesto varchar(5)) AS
$$
		select r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,COALESCE( ch.categoriahotelcod, 0 ),r.npreciopaquetepersona,r.nnropersonas,paq.ctituloidioma1,pv.nimporte,pv.nporcentaje,
		pv.formapago,pv.estado,pv.fechayhora_initx,pv.nro_tarjeta,pv.cod_autorizacion,pv.nom_th,r.cestado,ti.impuestovisa
				from treserva as r 
				left join tpagovisa as pv on(r.creservacod=pv.creservacod)
				left join treservapaqueteservicio as rps on(r.creservacod=rps.creservacod)
				left join tpaqueteservicio as ps on(rps.codpaqueteservicio=ps.codpaqueteservicio)
				left join tpaquete as paq on(ps.cpaquetecod=paq.cpaquetecod)
				left join treservapaquetecategoriahotel as rpch on(rpch.creservacod=r.creservacod)
				left join tpaquetecategoriahotel as pch on(pch.codpaquetecategoriah=rpch.codpaquetecategoriah)
				left join tcategoriahotel as ch on(ch.categoriahotelcod=pch.categoriahotelcod), timpuesto as ti
				where (pv.fechayhora_initx between to_date($1,'yyyy-MM-dd') and to_date($2,'yyyy-MM-dd')) and r.cestado=$3
				group by r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,ch.categoriahotelcod,r.npreciopaquetepersona,r.nnropersonas,paq.ctituloidioma1,pv.nimporte,pv.nporcentaje,pv.formapago,pv.estado,pv.fechayhora_initx,pv.nro_tarjeta,
					 pv.cod_autorizacion,pv.nom_th,r.cestado,ti.impuestovisa
				order by r.creservacod;
$$
  LANGUAGE sql;
create or replace function Pricing_sp_BuscarPagosPaypalEntreFechasBD
(
	fechaInicio varchar(12),
	fechaFin varchar(12),
	estadoPago varchar(10)
)
RETURNS table (creservacod varchar(12),dfechainicio Date,dfechafin Date,dfecha timestamp,categoriahotelcod int,npreciopaquetepersona numeric,nnropersonas int,ctituloidioma1 varchar(200),nimporte numeric,nporcentaje numeric,
formapago varchar(20),estado varchar(10),fechayhora_initx timestamp ,nro_tarjeta varchar(20),
codtransaccion varchar(20),nom_th varchar(100),cestado varchar(20),impuesto varchar(5)) AS
$$
		select r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,COALESCE( ch.categoriahotelcod, 0 ),r.npreciopaquetepersona,r.nnropersonas,paq.ctituloidioma1,pp.nimporte,pp.nporcentaje,
		pp.formapago,pp.estado,pp.fechayhora_initx,pp.nro_tarjeta,pp.cod_autorizacion,pp.nom_th,r.cestado,ti.impuestopaypal
				from treserva as r 
				left join tpagopaypal as pp on(r.creservacod=pp.creservacod)
				left join treservapaqueteservicio as rps on(r.creservacod=rps.creservacod)
				left join tpaqueteservicio as ps on(rps.codpaqueteservicio=ps.codpaqueteservicio)
				left join tpaquete as paq on(ps.cpaquetecod=paq.cpaquetecod)
				left join treservapaquetecategoriahotel as rpch on(rpch.creservacod=r.creservacod)
				left join tpaquetecategoriahotel as pch on(pch.codpaquetecategoriah=rpch.codpaquetecategoriah)
				left join tcategoriahotel as ch on(ch.categoriahotelcod=pch.categoriahotelcod), timpuesto as ti
				where (pp.fechayhora_initx between to_date($1,'yyyy-MM-dd') and to_date($2,'yyyy-MM-dd')) and r.cestado=$3
				group by r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,ch.categoriahotelcod,r.npreciopaquetepersona,r.nnropersonas,paq.ctituloidioma1,pp.nimporte,pp.nporcentaje,pp.formapago,pp.estado,pp.fechayhora_initx,pp.nro_tarjeta,
					 pp.cod_autorizacion,pp.nom_th,r.cestado,ti.impuestopaypal
				order by r.creservacod;
$$
  LANGUAGE sql;

  create or replace function Pricing_sp_BuscarPasajerosReserva(
	codReserva varchar(12)
  )
  returns table (capellidos varchar(100),cnombres varchar(100),csexo char(1),nedad int,cabrevtipodoc varchar(20),cnrodoc varchar(12),cnombreesp varchar(60)) as
  $$
		select pa.capellidos,pa.cnombres, pa.csexo,pa.nedad,tp.cabrevtipodoc,pa.cnrodoc,pais.cnombreesp
			from tpasajero as pa
			 inner join ttipodocumento as tp on(pa.ntipodoc=tp.ntipodoc)
			 inner join tpais as pais on(pa.npaiscod=pais.npaiscod)
			 where (pa.creservacod=$1)
			 group by pa.capellidos,pa.cnombres, pa.csexo,pa.nedad,tp.cabrevtipodoc,pa.cnrodoc,pais.cnombreesp
			 order by pa.capellidos
 $$
 LANGUAGE sql;




  select Pricing_sp_BuscarPasajerosReserva('R000000002');
  
  
  /*======estadistica======*/
  
  create or replace function Pricing_sp_listaPagosVisa()
  returns table(formapago varchar(20),nropagos bigint) as
  $$
  	select  formapago,count(*)
  	from tpagovisa
  	group by formapago;
  $$
  language sql;


  create or replace function Pricing_sp_listaPagosPaypal()
  returns table(formapago varchar(20),nropagos bigint) as
  $$
  	select  formapago,count(*)
  	from tpagopaypal
  	group by formapago;
  $$
  language sql;
  
  
  
  /*hoy viernes*
   */
  
  create or replace function Pricing_sp_BuscarPaquetesMasVendidos
(
	fechaInicio varchar(12),
	fechaFin varchar(12)
)
RETURNS table (ctituloidioma1 varchar(200),nrovendidos bigint) AS
$$
	select p.ctituloidioma1,count(*) as nrovendidos
			from treserva as r 
			left join treservapaqueteservicio as rps on(r.creservacod=rps.creservacod) 
			left join tpaqueteservicio as ps on(rps.codpaqueteservicio=ps.codpaqueteservicio)
			left join tpaquete as p on(ps.cpaquetecod=p.cpaquetecod)
			where (r.dfecha between to_date($1,'yyyy-MM-dd') and to_date($2,'yyyy-MM-dd'))
			group by p.cpaquetecod
			order by nrovendidos desc
			limit 1 offset 0;
$$
  LANGUAGE sql;



  select Pricing_sp_BuscarPaquetesMasVendidos('2016-07-01','2016-07-30');


select pricing_sp_buscarpaquetesmasvendidos('2016-07-01','2016-07-31')
create or replace function Pricing_sp_BuscarPaquetesMas
(
	fechaanio varchar(12)
)
RETURNS table (ctituloidioma1 varchar(200),nrovendidos bigint,fecha timestamp) AS
$$
	SELECT p.ctituloidioma1,
                SUM(CASE WHEN to_date($1||''||'-01-01','yyy-MM-dd') <= r.dfecha AND r.dfecha <=  to_date($1||''||'-01-31','yyy-MM-dd') THEN 1
			WHEN to_date($1||''||'-02-01','yyy-MM-dd') <= r.dfecha AND r.dfecha <=  to_date($1||''||'-02-31','yyy-MM-dd') THEN 1
			WHEN to_date($1||''||'-03-01','yyy-MM-dd') <= r.dfecha AND r.dfecha <=  to_date($1||''||'-03-31','yyy-MM-dd') THEN 1
			WHEN to_date($1||''||'-04-01','yyy-MM-dd') <= r.dfecha AND r.dfecha <=  to_date($1||''||'-04-31','yyy-MM-dd') THEN 1
			WHEN to_date($1||''||'-05-01','yyy-MM-dd') <= r.dfecha AND r.dfecha <=  to_date($1||''||'-05-31','yyy-MM-dd') THEN 1
			WHEN to_date($1||''||'-06-01','yyy-MM-dd') <= r.dfecha AND r.dfecha <=  to_date($1||''||'-06-31','yyy-MM-dd') THEN 1
			WHEN to_date($1||''||'-07-01','yyy-MM-dd') <= r.dfecha AND r.dfecha <=  to_date($1||''||'-07-31','yyy-MM-dd') THEN 1
			WHEN to_date($1||''||'-08-01','yyy-MM-dd') <= r.dfecha AND r.dfecha <=  to_date($1||''||'-08-31','yyy-MM-dd') THEN 1
			WHEN to_date($1||''||'-09-01','yyy-MM-dd') <= r.dfecha AND r.dfecha <=  to_date($1||''||'-09-31','yyy-MM-dd') THEN 1
			WHEN to_date($1||''||'-10-01','yyy-MM-dd') <= r.dfecha AND r.dfecha <=  to_date($1||''||'-10-31','yyy-MM-dd') THEN 1
			WHEN to_date($1||''||'-11-01','yyy-MM-dd') <= r.dfecha AND r.dfecha <=  to_date($1||''||'-11-31','yyy-MM-dd') THEN 1
			WHEN to_date($1||''||'-12-01','yyy-MM-dd') <= r.dfecha AND r.dfecha <=  to_date($1||''||'-12-31','yyy-MM-dd') THEN 1
		END) as nrovendidos,r.dfecha
               from treserva as r 
			inner join treservapaqueteservicio as rps on(r.creservacod=rps.creservacod) 
			inner join tpaqueteservicio as ps on(rps.codpaqueteservicio=ps.codpaqueteservicio)
			inner join tpaquete as p on(ps.cpaquetecod=p.cpaquetecod)
			group by p.ctituloidioma1,p.cpaquetecod,r.dfecha
			order by p.ctituloidioma1,nrovendidos desc
$$
  LANGUAGE sql;

