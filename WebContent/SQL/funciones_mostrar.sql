
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
Nombre		:Pricing_sp_MostrarTodasReservas
+++++++++++++++++++++++++++++++++++++++++++++++++*/ 
create or replace function Pricing_sp_MostrarTodasReservas()
returns setof treserva as
$$
	select * from treserva;
$$
LANGUAGE SQL;
 /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BuscarReservas
+++++++++++++++++++++++++++++++++++++++++++++++++*/ 
CREATE OR REPLACE FUNCTION Pricing_sp_BuscarReservas(
fechaInicio Date,
fechaFin Date,
estadoPago varchar(20)
)
  RETURNS SETOF treserva AS
$$
	select * from treserva where dfechainicio=$1 and dfechafin=$2 and cestado=$3;
$$
  LANGUAGE sql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_BsucarReservasEntreFechasBD
+++++++++++++++++++++++++++++++++++++++++++++++++*/ 
CREATE OR REPLACE FUNCTION Pricing_sp_BuscarReservas(
	fechaInicio varchar(12),
	fechaFin varchar(12),
	estadoPago varchar(20)
)
RETURNS table (creservacod varchar(12),dfechainicio Date,dfechafin Date,dfecha timestamp,ccontacto varchar(12),
cemail varchar(100),ctelefono varchar(50),nnropersonas int,npreciopaquetepersona numeric,ctituloidioma1 varchar(200),
ccategoriaidioma1 varchar(200),cdestino varchar(100),chotel varchar(200),cservicioindioma1 varchar(200),csubservicioindioma1 varchar(200),cestado varchar(20)) AS
$$
	select r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,r.ccontacto,r.cemail,r.ctelefono,r.nnropersonas,r.npreciopaquetepersona,
		p.ctituloidioma1,c.ccategoriaidioma1,d.cdestino,h.chotel,s.cservicioindioma1,ss.csubservicioindioma1,r.cestado
			from treserva as r 
			left join treservapaqueteservicio as rps on(r.creservacod=rps.creservacod) 
			left join tpaqueteservicio as ps on(rps.codpaqueteservicio=ps.codpaqueteservicio)
			left join treservapaquetecategoriahotel as pch on(r.creservacod=pch.creservacod)
			left join tpaquetecategoriahotel as pc on (pch.codpaquetecategoriah=pc.codpaquetecategoriah)
			left join tpaquetedestino as pd on(ps.cpaquetecod=pd.cpaquetecod)
			left join tdestinohotel as dh on(pd.ndestinocod=dh.ndestinocod)
			left join tcategoriahotel as c on(pc.categoriahotelcod=c.categoriahotelcod)
			left join tpaquete as p on(ps.cpaquetecod=p.cpaquetecod)
			left join tservicio as s on(ps.nserviciocod=s.nserviciocod)
			left join tsubservicio as ss on(ps.nserviciocod=ss.nserviciocod)
			left join tdestino as d on(dh.ndestinocod=d.ndestinocod)
			left join thotel as h on(dh.nhotelcod=h.nhotelcod and c.categoriahotelcod=h.categoriahotelcod)
			where (r.dfecha between to_date($1,'yyyy-MM-dd') and to_date($2,'yyyy-MM-dd')) and r.cestado=$3
			group by r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,r.ccontacto,r.cemail,r.ctelefono,r.nnropersonas,r.npreciopaquetepersona,
				p.ctituloidioma1,c.ccategoriaidioma1,d.cdestino,h.chotel,s.cservicioindioma1,ss.csubservicioindioma1,r.cestado
			order by r.creservacod;
$$
  LANGUAGE sql;

select creservacod  from treserva where (dfecha between '2016-07-14' and '2016-08-14') and cestado='PENDIENTE DE PAGO';