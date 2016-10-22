CREATE TABLE TAcceso
(
	nAccesoCod int,
	nPerfilCod int,
	accesoUpdateDisdo boolean,
	accesoEtiqueta boolean,
	accesoImpuesto boolean,
	accesoCodVisa boolean,
	accesoRegUsuarios boolean,
	accesoCrearNuevoUser boolean,
	accesoCategorias boolean,
	accesoPaquetes boolean,
	accesoServicios boolean,
	accesoSubServicios boolean,
	accesoHoteles boolean,
	accesoDestinos boolean,
	accesoReporReservas boolean,
	accesoReporPagos boolean,
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
insert into tacceso values(1,1,true,true,false,true,true,true,true,true,true,true,true,true,true,true),
			  (2,2,true,true,true,true,true,true,true,true,true,true,true,true,true,true),
			  (3,3,true,true,false,true,false,false,true,true,true,true,true,true,false,false)
select * from tacceso