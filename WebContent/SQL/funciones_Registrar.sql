/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_RegistrarPaquete
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_RegistrarPaquete
(
	tituloPaqueteIdioma_1 varchar(200),
	tituloPaqueteIdioma_2 varchar(200),
	tituloPaqueteIdioma_3 varchar(200),
	tituloPaqueteIdioma_4 varchar(200),
	tituloPaqueteIdioma_5 varchar(200),
	nroDias int,
	nroNoches int,
	precio_1 decimal(10,2),
	precio_2 decimal(10,2),
	precio_3 decimal(10,2),
	precio_4 decimal(10,2),
	precio_5 decimal(10,2),
	manejo varchar(100),
	dia_caminoInka int
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		codPaquete varchar(10)) as
$$
begin
	codPaquete=(select concat('P-',right(concat('00',count(p.cpaquetecod)+1),2)) from tpaquete p where left(p.cpaquetecod,2)='P-');
	insert into tpaquete values(codPaquete,$1,$2,$3,$4,$5,'','','','','',
								$6,$7,$8,$9,$10,$11,$12,$13,true,'',$14);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codPaquete;
end
$$
language plpgsql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_RegistrarPaqueteServicio
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_RegistrarPaqueteServicio
(
	codPaquete varchar(10),
	codServicio int
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		codPaqueteServ int) as
$$
begin
	codPaqueteServ=(select max( codpaqueteservicio ) from tpaqueteservicio);
	if(codPaqueteServ is null)then
		codPaqueteServ=1;
	else
		codPaqueteServ=codPaqueteServ+1;
	end if;
	insert into tpaqueteservicio values(codPaqueteServ,$1,$2);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codPaqueteServ;
end
$$
language plpgsql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_RegistrarPaqueteDestino
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_RegistrarPaqueteDestino
(
	codPaquete varchar(10),
	codDestino int,
	noches int,
	ordenItinerario int,
	conCaminoInka boolean
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		codPaqueteDest int) as
$$
begin
	codPaqueteDest=(select max( codpaquetedestino ) from tpaquetedestino);
	if(codPaqueteDest is null)then
		codPaqueteDest=1;
	else
		codPaqueteDest=codPaqueteDest+1;
	end if;
	insert into tpaquetedestino values(codPaqueteDest,$1,$2,$3,$4,$5);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codPaqueteDest;
end
$$
language plpgsql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_RegistrarPaqueteCatHotel
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_RegistrarPaqueteCatHotel
(
	codPaquete varchar(10)
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		paqueteCod varchar(10)) as
$$
declare
	cont int;
	codPaqueteCH varchar(10);
begin
	cont=1;
	LOOP
	    IF cont > 7 THEN
	        EXIT;  -- exit loop
	    END IF;
	    codPaqueteCH=(select concat('PCH-',right(concat('0000',count(p.codpaquetecategoriah)+1),4)) from tpaquetecategoriahotel p where left(p.codpaquetecategoriah,4)='PCH-');
	    insert into tpaquetecategoriahotel values(codPaqueteCH,$1,cont);
	    cont=cont+1;
	END LOOP;
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,paqueteCod;
end
$$
language plpgsql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_RegistrarReserva
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:05/19/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE function Pricing_sp_RegistroReserva
(
	dFechaInicio Date,
	dFechaFin Date,
	cContacto varchar(100),
	cEmail varchar(100),
	cTelefono varchar(50),
	cPrecioPaquetePersona decimal(10,2),
	nNroPersonas int,
	cInformacionAdicional varchar(300)
)
RETURNS TABLE (resultado varchar(20),
		mensaje varchar(200),
		cReservaCod varchar(12)) as 
$$
begin
	--generar el codigo de la reserva
	cReservaCod = (select concat('R',right(concat('000000000',count(r.cReservaCod)+1),9)) from treserva r where left(r.cReservaCod,1)='R');
	--registrar los datos de la reserva
	insert into TReserva values (cReservaCod,now(),$1,$2,$3,$4,$5,$6,$7,$8,'PENDIENTE DE PAGO');
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,cReservaCod;
end
$$ 
LANGUAGE plpgsql;
--++++++++++++++++++++++++++++++++
create or replace function Pricing_sp_RegistrarPasajero
(
  creservacod varchar(12),
  nnro int,
  cnrodoc varchar(12),
  ntipodoc int,
  capellidos varchar(100),
  cnombres varchar(100),
  npaiscod int,
  csexo char(1),
  nedad int,
  curldocumento varchar(200)
)
returns table (resultado varchar(20),mensaje varchar(200),codReserva varchar(12))as
$$
begin
	codReserva=$1;
	insert into TPasajero (creservacod,nnro,cnrodoc,ntipodoc,capellidos,cnombres,npaiscod,csexo,nedad,curldocumento)
			values($1,$2,$3,$4,$5,$6,$7,$8,$9,$10);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codReserva;
end
$$
language plpgsql;
--***********************************
create or replace function Pricing_sp_RegistrarReservaPS
(
  codpaqueteservicio varchar(10),
  creservacod varchar(12),
  nroprestacionservicio decimal(10,2),
  precioprestacionservicio decimal(10,2)
)
returns table(resultado varchar(20),mensaje varchar(200),codReserva varchar(12))as
$$
begin
	codReserva=$2;
	insert into TReservaPaqueteServicio (codpaqueteservicio,creservacod,nroprestacionservicio,precioprestacionservicio)
					values($1,$2,$3,$4);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codReserva;
end
$$
language plpgsql;
--***********************************
create or replace function Pricing_sp_RegistrarReservaPCH
(
  creservacod varchar(12),
  codpaquetecategoriah varchar(10),
  nnropersonassimple int,
  npreciototalsimple decimal(10,2),
  nnropersonasdoble int,
  npreciototaldoble decimal(10,2),
  nnropersonastriple int,
  npreciototaltriple decimal(10,2)
)
returns table(resultado varchar(20),mensaje varchar(200),codReserva varchar(12))as
$$
begin
	codReserva=$1;
	insert into TReservaPaqueteCategoriaHotel (creservacod,codpaquetecategoriah,nnropersonassimple,npreciototalsimple,nnropersonasdoble,npreciototaldoble,nnropersonastriple,npreciototaltriple)
						values($1,$2,$3,$4,$5,$6,$7,$8);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codReserva;
end
$$
language plpgsql;
--*****************************************
create or replace function Pricing_sp_RegistrarFechaAlterna
(
  creservacod varchar(12),
  dfechainicio date,
  dfechafin date
)
returns table(resultado varchar(20),mensaje varchar(200),codReserva varchar(12))as
$$
begin
	codReserva=$1;
	insert into TFechaAlterna (creservacod,dfechainicio,dfechafin)
				values($1,$2,$3);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codReserva;
end
$$
language plpgsql;
--********************** regitrar hoteles********************
create or replace function Pricing_sp_RegistrarHotel
(
  hotel varchar(200),
  descripcionIdioma1 text,
  descripcionIdioma2 text,
  descripcionIdioma3 text,
  descripcionIdioma4 text,
  descripcionIdioma5 text,
  url varchar(200),
  categoria int,
  precioSimple decimal(10,2),
  precioDoble decimal(10,2),
  precioTriple decimal(10,2),
  codDestino int
)
returns table(resultado varchar(20),mensaje varchar(200),codHotel int)as
$$
declare
	codDestinoHotel varchar(10);
begin
	codHotel=(select max( nhotelcod ) from thotel);
	if(codHotel is null)then
		codHotel=1;
	else
		codHotel=codHotel+1;
	end if;
	insert into THotel (nhotelcod,chotel,cdescripcionidioma1,cdescripcionidioma2,cdescripcionidioma3,
			cdescripcionidioma4,cdescripcionidioma5,curl,categoriahotelcod,npreciosimple,npreciodoble,
			npreciotriple,bestado)
			values(codHotel,$1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,true);
	
	codDestinoHotel=select concat('DH-',right(concat('000',count(dh.destinohotelcod)+1),3)) from tdestinohotel dh where left(dh.destinohotelcod,3)='DH-';
	
	insert into TDestinoHotel values(codDestinoHotel,$12,codHotel);
	update TDestino set bestado=true where ndestinocod=$12;
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codHotel;
end
$$
language plpgsql;

--******************* registrar sub servicios***********************
create or replace function Pricing_sp_RegistrarSubServicio
(
  codigoServicio int,
  subServicioIdioma1 varchar(200),
  subServicioIdioma2 varchar(200),
  subServicioIdioma3 varchar(200),
  subServicioIdioma4 varchar(200),
  subServicioIdioma5 varchar(200),
  descripcionIdioma1 text,
  descripcionIdioma2 text,
  descripcionIdioma3 text,
  descripcionIdioma4 text,
  descripcionIdioma5 text,
  urlimagen varchar(200),
  link text,
  precioServicio decimal(10,2)
)
returns table(resultado varchar(20),mensaje varchar(200),codSubServicio int)as
$$
begin
	codSubServicio=(select max( nsubserviciocod ) from tsubservicio)+1;
	insert into TSubServicio (nsubserviciocod,nserviciocod,csubservicioidioma1,csubservicioidioma2,csubservicioidioma3,
	                csubservicioidioma4,csubservicioidioma5,cdescripcionidioma1,cdescripcionidioma2,cdescripcionidioma3,
			cdescripcionidioma4,cdescripcionidioma5,curlimg,clink,nprecioservicio,bestado)
			values(codSubServicio,$1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,$14,true);
	resultado='correcto';
	mensaje='Datos Registrados Correctamente';
	return Query select resultado,mensaje,codSubServicio;
end
$$
language plpgsql;
--***Registrar Servicio***
create or replace function Pricing_sp_RegistrarServicio
(
  cservicioindioma1 varchar(200),
  cservicioindioma2 varchar(200),
  cservicioindioma3 varchar(200),
  cservicioindioma4 varchar(200),
  cservicioindioma5 varchar(200),
  cdescripcionidioma1 text,
  cdescripcionidioma2 text,
  cdescripcionidioma3 text,
  cdescripcionidioma4 text,
  cdescripcionidioma5 text,
  crestriccionyesno int,
  crestriccionnum int,
  cincremento int,
  curlimg varchar(200),
  nprecioservicio decimal(10,2),
  bestado boolean
)
returns table(resultado varchar(20),mensaje varchar(200),codServicio int)as
$$
begin
        codServicio=(select max( nserviciocod ) from tservicio)+1;
        insert into tservicio values(codServicio,$1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,$15,$16);
        resultado='correcto';
        mensaje='Datos Registrados Correctamente';
        return Query select resultado,mensaje,codServicio;
end
$$
language plpgsql;
--**Registrar Destino**--
create or replace function Pricing_sp_RegistrarDestino
(
	nameDestino varchar(100),
	codPostal int
)
returns table(resultado varchar(20),mensaje varchar(200),codDestino int)as
$$
begin
	codDestino=(select max( ndestinocod ) from tdestino);
	if(codDestino is null)then
		codDestino=1;
	else
		codDestino=codDestino+1;
	end if;
        insert into tdestino values(codDestino,$1,false,$2);
        resultado='correcto';
        mensaje='Datos Registrados Correctamente';
        return Query select resultado,mensaje,codDestino;
end
$$
language plpgsql;