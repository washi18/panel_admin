  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_ModificarServicio
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarServicio
(
  Serviciocod int,
  cServicioindioma_1 varchar(200),
  cServicioindioma_2 varchar(200),
  cServicioindioma_3 varchar(200),
  cServicioindioma_4 varchar(200),
  cServicioindioma_5 varchar(200),
  cDescripcionidioma_1 text,
  cDescripcionidioma_2 text,
  cDescripcionidioma_3 text,
  cDescripcionidioma_4 text,
  cDescripcionidioma_5 text,
  Restriccionyesno int,
  Restriccionnum int,
  Incremento int,
  Urlimg varchar(200),
  Precioservicio decimal(10,2),
  estado boolean
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codSer int) as
$$
begin
	codSer=$1;
	update tservicio set cservicioindioma1=$2,
			  cservicioindioma2=$3,
			  cservicioindioma3=$4,
			  cservicioindioma4=$5,
			  cservicioindioma5=$6,
			  cdescripcionidioma1=$7,
			  cdescripcionidioma2=$8,
			  cdescripcionidioma3=$9,
			  cdescripcionidioma4=$10,
			  cdescripcionidioma5=$11,
			  crestriccionyesno=$12,
			  crestriccionnum=$13,
			  cincremento=$14,
			  curlimg=$15,
			  nprecioservicio=$16 
			  bestado=$17 where nserviciocod=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codSer;
end
$$
LANGUAGE plpgsql;
  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_ModificarEtiqueta
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarEtiqueta
(
	CodEtiquet int,
	CIdioma_1 text,
	CIdioma_2 text,
	CIdioma_3 text,
	CIdioma_4 text,
	CIdioma_5 text
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codEt int) as
$$
begin
	codEt=$1;
	update TEtiqueta set cidioma1=$2,cidioma2=$3,cidioma3=$4,cidioma4=$5,cidioma5=$6 where codetiqueta=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codEt;
end
$$
LANGUAGE plpgsql;

  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_ModificarEtiqueta
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarSubServicio
(
  Subserviciocod int,
  Serviciocod int,
  cSubservicioindioma_1 varchar(200),
  cSubservicioindioma_2 varchar(200),
  cSubservicioindioma_3 varchar(200),
  cSubservicioindioma_4 varchar(200),
  cSubservicioindioma_5 varchar(200),
  cDescripcionidioma_1 text,
  cDescripcionidioma_2 text,
  cDescripcionidioma_3 text,
  cDescripcionidioma_4 text,
  cDescripcionidioma_5 text,
  Urlimg varchar(200),
  Link text,
  Precioservicio decimal(10,2),
  estado boolean
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codSubSer int) as
$$
begin
	codSubSer=$1;
	update tsubservicio set nserviciocod=$2,
			  csubservicioindioma1=$3,
			  csubservicioindioma2=$4,
			  csubservicioindioma3=$5,
			  csubservicioindioma4=$6,
			  csubservicioindioma5=$7,
			  cdescripcionidioma1=$8,
			  cdescripcionidioma2=$9,
			  cdescripcionidioma3=$10,
			  cdescripcionidioma4=$11,
			  cdescripcionidioma5=$12,
			  curlimg=$13,
			  clink=$14,
			  nprecioservicio=$15,
			  bestado=$16 where nsubserviciocod=$1;
			  if(estado=false) THEN
				 if((select count(nsubserviciocod) from tsubservicio where nserviciocod=$2 and bestado=true 
					group by nserviciocod) is null) THEN
						update tservicio set bestado=false where nserviciocod=$2;
					END IF;
			  else 
				update tservicio set bestado=true where nserviciocod=$2;
			  END IF;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codSubSer;
end
$$
LANGUAGE plpgsql;
  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_ModificarPaquetes
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarPaquetes
(
  Paquetecod varchar(10),
  cTituloidioma_1 varchar(200),
  cTituloidioma_2 varchar(200),
  cTituloidioma_3 varchar(200),
  cTituloidioma_4 varchar(200),
  cTituloidioma_5 varchar(200),
  cDescripcionidioma_1 text,
  cDescripcionidioma_2 text,
  cDescripcionidioma_3 text,
  cDescripcionidioma_4 text,
  cDescripcionidioma_5 text,
  Dias int,
  Noches int,
  Preciouno decimal(10,2),
  Preciodos decimal(10,2),
  Preciotres decimal(10,2),
  Preciocuatro decimal(10,2),
  Preciocinco decimal(10,2),
  Disponibilidad varchar(100),
  diaCaminoInka int,
  estado boolean
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codPaquete varchar(10)) as
$$
begin
	codPaquete=$1;
	update tpaquete set ctituloidioma1=$2,
			  ctituloidioma2=$3,
			  ctituloidioma3=$4,
			  ctituloidioma4=$5,
			  ctituloidioma5=$6,
			  cdescripcionidioma1=$7,
			  cdescripcionidioma2=$8,
			  cdescripcionidioma3=$9,
			  cdescripcionidioma4=$10,
			  cdescripcionidioma5=$11,
			  ndias=$12,
			  nnoches=$13,
			  npreciouno=$14,
			  npreciodos=$15,
			  npreciotres=$16,
			  npreciocuatro=$17,
			  npreciocinco=$18,
			  cdisponibilidad=$19,
			  ndiacaminoinka=$20,
			  bestado=$21
			  where cpaquetecod=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codPaquete;
end
$$
LANGUAGE plpgsql;
  /*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_ModificarImpuesto
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarImpuesto --para PGT
(
	nCodImpuesto int,
	cImpuestoPaypal varchar(5),
	cImpuestoVisa varchar(5),
	cPorcentajeCobro varchar(5)
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codImp int) as
$$
begin
	codImp=$1;
	update timpuesto set impuestopaypal=$2,
			impuestovisa=$3,
			porcentajecobro=$4 where codimpuesto=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codImp;
end
$$
LANGUAGE plpgsql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_ModificarImpuesto
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
CREATE OR REPLACE FUNCTION Pricing_sp_ModificarImpuesto --para INFO
(
	nCodImpuesto int,
	cImpuestoPaypal varchar(5),
	cImpuestoVisa varchar(5)
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codImp int) as
$$
begin
	codImp=$1;
	update timpuesto set impuestopaypal=$2,
			impuestovisa=$3 where codimpuesto=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codImp;
end
$$
LANGUAGE plpgsql;
/*+++++++++++++++++++++++++++++++++++++++++++++++++
Nombre		:Pricing_sp_ModificarImpuesto
Utilizado en	:Aplicacion Web FootPathPeru
Usuario		:
Fecha Creacion	:07/14/2016
Ejecucion	:SELECT * FROM AG_sp_RegistrarReserva('','20150410','20150430','43027528','luis@gmail.com','984289670',3,'informacion')
Eliminacion	:DROP FUNCTION AG_sp_RegistrarReserva(varchar(10),date,date,varchar(12),varchar(100),varchar(50),int,varchar(300))
Comentario	:Registrar una reserva
Modificacion	:
+++++++++++++++++++++++++++++++++++++++++++++++++*/
create or replace function Pricing_sp_ModificarHotel
(
  hotelcod int,
  hotel varchar(200),
  descripcionidioma1 text,
  descripcionidioma2 text,
  descripcionidioma3 text,
  descripcionidioma4 text,
  descripcionidioma5 text,
  url varchar(200),
  codcategoriahotel int,
  preciosimple decimal(10,2),
  preciodoble decimal(10,2),
  preciotriple decimal(10,2),
  estado boolean,
  codDestino int
)
returns table(resultado varchar(20),
		mensaje varchar(200),
		codHotel int)as
$$
begin
        codHotel=$1;
        update thotel set chotel=$2,
                          cdescripcionidioma1=$3,
                          cdescripcionidioma2=$4,
                          cdescripcionidioma3=$5,
                          cdescripcionidioma4=$6,
                          cdescripcionidioma5=$7,
                          curl=$8,
                          categoriahotelcod=$9,
                          npreciosimple=$10,
                          npreciodoble=$11,
                          npreciotriple=$12,
                          bestado=$13,
                          ndestinocod=$14
                      where nhotelcod=$1;
    resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codHotel;
end
$$
language plpgsql;
--******************************************
create or replace function Pricing_sp_ModificarEstadoPagoReserva
(
	reservacod varchar(12),
	estado varchar(20)
)
returns table(resultado varchar(20),mensaje varchar(200),codReserva varchar(12))as
$$
begin
	codReserva=$1;
	update TReserva set cestado=$2 where creservacod=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codReserva;
end
$$
language plpgsql;
--***************************************
create or replace function Pricing_sp_ModificarDestino
(
	codDestino int,
	nameDestino varchar(100),
	estado boolean,
	codPostal int
)
returns table(resultado varchar(20),mensaje varchar(200),codDest int)as
$$
begin
	codDest=$1;
	update TDestino set bestado=$3, cdestino=$2, ncodpostal=$4 where ndestinocod=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codDest;
end
$$
language plpgsql;
--**************************************
create or replace function Pricing_sp_ModificarPaqueteDestino
(
	codpd int,
	noches int,
	itinerario int,
	con_caminoInka boolean
)
returns table(resultado varchar(20),mensaje varchar(200),codPDest int)as
$$
begin
	codPDest=$1;
	update TPaqueteDestino set nnoches=$2, nordenitinerario=$3, bconcaminoinka=$4 where codpd=$1;
	resultado='correcto';
	mensaje='Datos Actualizados Correctamente';
	return Query select resultado,mensaje,codPDest;
end
$$
language plpgsql;
--*********************************