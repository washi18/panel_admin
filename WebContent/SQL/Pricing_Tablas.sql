CREATE TABLE TActividad
(
  nActividadCod int NOT NULL,
  cActividadIdioma1 varchar(200),
  cActividadIdioma2 varchar(200),
  cActividadIdioma3 varchar(200),
  cActividadIdioma4 varchar(200),
  cActividadIdioma5 varchar(200),
  cDescripcionIdioma1 text,
  cDescripcionIdioma2 text,
  cDescripcionIdioma3 text,
  cDescripcionIdioma4 text,
  cDescripcionIdioma5 text,
  cUrlImg varchar(200),
  nPrecioActividad decimal(10,2),
  bEstado boolean,
  primary key(nActividadCod)
);
CREATE TABLE TPaqueteActividad
(
	nPaqueteActividad int not null,
	cPaqueteCod varchar(10),
	nActividadCod int,
	primary key(nPaqueteActividad),
	foreign key(cPaqueteCod)references TPaquete,
	foreign key(nActividadCod)references TActividad
);
CREATE TABLE TAcceso
(
	nAccesoCod int,
	nPerfilCod int,
	accesoIdiomas boolean,
	accesoUpdateDispo boolean,
	accesoEtiqueta boolean,
	accesoImpuesto boolean,
	accesoVisa boolean,
	accesoPaypal boolean,
	accesoMasterdCard boolean,
	accesoWesternUnion boolean,
	accesoRegUsuarios boolean,
	accesoCrearNuevoUser boolean,
	accesoPaquetes boolean,
	accesoServicios boolean,
	accesoSubServicios boolean,
	accesoActividades boolean,
	accesoHoteles boolean,
	accesoDestinos boolean,
	accesoReporReservas boolean,
	accesoReporPagos boolean,
	accesoEstadPagos boolean,
	accesoEstadPaquMasVendidos boolean,
	foreign key (nPerfilCod)references TPerfil,
	primary key (nAccesoCod)
);
CREATE TABLE TPerfil
(
	nPerfilCod int,					--codigo del perfil
	cPerfilIdioma1 varchar(100),			--descripcion del perfil en el primer idioma
	primary key (nPerfilCod)
);
/*
Datos de los usuarios que accederan al modulo de administracion de la pagina web de una agencia
*/
CREATE TABLE TUsuario
(
	cUsuarioCod varchar(150) not null,		--numero del documento del usuario
	cClave varchar(128),				--clave del usuario
	nPerfilCod int,					--codigo del perfil
	imgUsuario varchar(200),			--imagen de perfil del usuario
	cNroDoc varchar(12),				--numero del documento del usuario
	cNombres varchar(150),				--nombres del representante legal
	cSexo char(1),					--sexo del representante legal
	dFechaNac date,					--fecha de nacimiento del representante legal
	cCelular varchar(50),				--numero telefonico del usuario
	primary key (cUsuarioCod),
	foreign key (nPerfilCod) references TPerfil
);
insert into tusuario values('43997550','12345',1,'codigo.png','73077306','WASHINGTON BLAS HUAMAN','M','2016-12-23','926345634'),
				('73077307','12345',2,'codigo.png','73077307','FRANKLIN AIMITUMA SUYO','M','2013-12-21','987564328'),
				('45789976','12345',3,'codigo.png','73077307','JASON','M','2013-12-21','987564328')
insert into tperfil values(1,'SUPER ADMINISTRADOR'),(2,'ADMINISTRADOR'),(3,'SUB ADMINISTRADOR')
insert into tacceso values(1,1,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true),
			  (2,2,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true),
			  (3,3,false,true,false,false,false,false,false,false,true,true,false,true,false,false,true,true,false,false,false,false)
select * from tacceso