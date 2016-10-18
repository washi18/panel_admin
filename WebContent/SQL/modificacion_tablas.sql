
--TABLA TPAQUETE--
ALTER TABLE tpaquete ADD COLUMN nDiaCaminoInka int DEFAULT 0
ALTER TABLE tpaquete alter COLUMN nDiaCaminoInka drop DEFAULT

SELECT  * from tdestinohotel  
--TABLA TPAQUETEDESTINO--
ALTER TABLE tpaquetedestino ADD COLUMN nNoches int DEFAULT 1
ALTER TABLE tpaquetedestino alter COLUMN nNoches drop DEFAULT

ALTER TABLE tpaquetedestino ADD COLUMN nOrdenItinerario int DEFAULT 1
ALTER TABLE tpaquetedestino alter COLUMN nOrdenItinerario drop DEFAULT

ALTER TABLE tpaquetedestino ADD COLUMN bConCaminoInka boolean DEFAULT false
ALTER TABLE tpaquetedestino alter COLUMN bConCaminoInka drop DEFAULT

--TABLA TDESTINO--
ALTER TABLE tdestino ADD COLUMN nCodPostal int DEFAULT 84
ALTER TABLE tdestino alter COLUMN nCodPostal drop DEFAULT


--CREACION DE TABLAS LOGIN Y PERFIL
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
	bEsRepLeg boolean,				--indica si es representante legal de la agencia
	cSexo char(1),					--sexo del representante legal
	dFechaNac date,					--fecha de nacimiento del representante legal
	cCelular varchar(50),				--numero telefonico del usuario
	primary key (cUsuarioCod),
	foreign key (nPerfilCod) references TPerfil
);

insert into tusuario values('73077306','veamos',0001,'codigo.png','73077306','franklin',false,'M','2016-12-23','926345634'),
				('73077307','veamos2',0002,'codigo.png','73077307','juan',false,'M','2013-12-21','987564328')
insert into tperfil values(0001,'ADMINISTRADOR'),(0002,'SUPER ADMINISTRADOR'),(0003,'VISITANTE')


--CREACION DE TABLAS DE PAGOS PAYPAL Y DE VISA E INSERCION DE DATOS ALA TABLA
CREATE TABLE TPagoVisa
(
	nroOrden INT,					--codigo del numero de orden generado para el proceso de pago
	cReservaCod VARCHAR(10),			--codigo de la reserva que se pretende pagar
	nImporte decimal(10,2),				--importe de la reserva que se pretende pagar y/o amortizar
	nPorcentaje decimal(10,2),			--porcentaje de pago del total del importe de la reserva
	formaPago VARCHAR(20),				--forma de pago del cliente (VISA, MASTERCARD,PAYPAL)
	estado VARCHAR(10),				--estado de la transaccion (AUTORIZADO,CANCELADO,DENEGADO,EXTORNADO,INICIADO)
	fechayhora_initx timestamp,			--fecha y hora del inicio de transaccion
	eticket varchar(50),				--codigo de eticket generado por visanet
	respuesta INT,					--codigo de respuesta de visanet
	cod_tienda varchar(20),				--codigo del comercio
	cod_accion VARCHAR(3),				--codigo de accion emitida por visanet
	dsc_cod_accion varchar(150),			--descripcion del codigo de accion emitido por visanet
	nro_tarjeta VARCHAR(20),			--numero de la tarjeta del cliente
	nom_th VARCHAR(100),				--nombre del tarjeta habiente (cliente)
	ori_tarjeta VARCHAR(2),				--origen de la tarjeta
	nom_Emisor VARCHAR(50),				--nombre del banco emisor de la tarjeta
	eci VARCHAR(5),					--codigo eci emitido por visanet
	dsc_eci VARCHAR(100),				--descripcion del codigo ECI emitido por visanet
	cod_autorizacion VARCHAR(6),			--codigo de autorizacion de pago emitido por visanet
	cod_rescvv2 VARCHAR(10),			--codigo de 3 digitos del reverso de la tarjeta
	imp_autorizado DECIMAL(10,2),			--importe autporizado por visanet en la transaccion
	fechayhora_fintx varchar(50),			--fecha y hora de fin de la transaccion
	fechayhora_deposito varchar(50),		--fecha y hora del deposito de pagos de visanet
	fechayhora_devolucion varchar(50),		--fecha y hora de devolucion, anulacion y/o extorno de pago
	dato_comercio VARCHAR(100),			--dato adicional del comercio
	PRIMARY KEY (nroOrden),
	FOREIGN KEY (cReservaCod) REFERENCES TReserva
);


create table TPagoPaypal(
    nroorden integer NOT NULL,   
    codpagador integer,
    cod_autorizacion character varying(20),
    nporcentaje numeric(10,2),
    creservacod character varying(10),
    formapago character varying(20),
    estado character varying(10),
    nimporte numeric(10,2),
    fechayhora_initx timestamp without time zone,
    nom_th character varying(100),
    nro_tarjeta character varying(20),
    adicionalpagador character varying(100),
    telefonopagador character varying(50),
    pais character varying(3),
    estadopagador character varying(100),
    detalleimpuesto character varying(20),
    direccion character varying(50),
    emailpagador character varying(100),
    PRIMARY KEY (nroOrden),
    FOREIGN KEY (cReservaCod) REFERENCES TReserva
);

insert into TPagoVisa values(1002,'R000000002',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E002',12344,'T002','A02','codigo de accion1','TAR0002',
			'JULIO GOMEZ','AA','CONTINENTAL','E0002','eci enviado 2','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1003,'R000000003',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E003',12344,'T003','A03','codigo de accion1','TAR0003',
			'RAUL GOMEZ','AA','CONTINENTAL','E0002','eci enviado 3','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1004,'R000000004',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E004',12344,'T004','A04','codigo de accion1','TAR0003',
			'PEDRO GOMEZ','AA','CONTINENTAL','E0002','eci enviado 4','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1005,'R000000005',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E005',12344,'T005','A05','codigo de accion1','TAR0003',
			'OSCAR GOMEZ','AA','CONTINENTAL','E0002','eci enviado 5','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2');


insert into tpagopaypal values(20006,40001,'C00001',0.40,'R000000006','PAYPAL','AUTORIZADO',237.00,'2016-07-22 13:29:44','LEONARDO CRUZ','T01938373',
								'trabaja en agencia','72353532','PER','ESTADO1','impuesto sera 1','av.collasuyo A-15','leo@gmail.com'),
								(20007,40002,'C00002',0.40,'R000000007','PAYPAL','AUTORIZADO',257.00,'2016-07-22 13:29:44','JUAN CRUZ','T019123373',
								'trabaja en agencia','72353532','PER','ESTADO1','impuesto sera 1','av.collasuyo A-15','juan@gmail.com'),
								(20008,40003,'C00003',0.40,'R000000008','PAYPAL','AUTORIZADO',187.00,'2016-07-23 13:29:44','MARIO CRUZ','T01924373',
								'trabaja en agencia','72353532','COL','ESTADO1','impuesto sera 1','av.collasuyo A-15','mario@gmail.com'),
								(20009,40004,'C00004',0.40,'R000000009','PAYPAL','INICIADO',297.00,'2016-07-24 13:29:44','CARMEN CRUZ','T01758373',
								'trabaja en agencia','72353532','USA','ESTADO1','impuesto sera 1','av.collasuyo A-15','carmen@gmail.com'),
								(20010,40005,'C00005',0.40,'R000000010','PAYPAL','INICIADO',183.00,'2016-07-25 13:29:44','JOSE CRUZ','T01468373',
								'trabaja en agencia','72353532','ECU','ESTADO1','impuesto sera 1','av.collasuyo A-15','jose@gmail.com');
select * from tpagopaypal;
--==========MODIFICAR TABLA TPAQUETESERVICIO Y TRESERVAPAQUETESERVICIO==
--Modificar paso a paso
alter table treservapaqueteservicio drop constraint treservapaqueteservicio_codpaqueteservicio_fkey;
alter table tpaqueteservicio drop constraint tpaqueteservicio_pkey;
--==========================
	update tpaqueteservicio set codpaqueteservicio='1' where codpaqueteservicio='PS-001';
	update tpaqueteservicio set codpaqueteservicio='2' where codpaqueteservicio='PS-002';
	update tpaqueteservicio set codpaqueteservicio='3' where codpaqueteservicio='PS-003';
	update tpaqueteservicio set codpaqueteservicio='4' where codpaqueteservicio='PS-004';
	update tpaqueteservicio set codpaqueteservicio='5' where codpaqueteservicio='PS-005';
	update tpaqueteservicio set codpaqueteservicio='6' where codpaqueteservicio='PS-006';
	update tpaqueteservicio set codpaqueteservicio='7' where codpaqueteservicio='PS-007';
	update tpaqueteservicio set codpaqueteservicio='8' where codpaqueteservicio='PS-008';
	update tpaqueteservicio set codpaqueteservicio='9' where codpaqueteservicio='PS-009';
	update tpaqueteservicio set codpaqueteservicio='10' where codpaqueteservicio='PS-010';
	update tpaqueteservicio set codpaqueteservicio='11' where codpaqueteservicio='PS-011';
	update tpaqueteservicio set codpaqueteservicio='12' where codpaqueteservicio='PS-012';
	update tpaqueteservicio set codpaqueteservicio='13' where codpaqueteservicio='PS-013';
	update tpaqueteservicio set codpaqueteservicio='14' where codpaqueteservicio='PS-014';
	update tpaqueteservicio set codpaqueteservicio='15' where codpaqueteservicio='PS-015';
	update tpaqueteservicio set codpaqueteservicio='16' where codpaqueteservicio='PS-016';
	update tpaqueteservicio set codpaqueteservicio='17' where codpaqueteservicio='PS-017';
	update tpaqueteservicio set codpaqueteservicio='18' where codpaqueteservicio='PS-018';
	update tpaqueteservicio set codpaqueteservicio='19' where codpaqueteservicio='PS-019';
	update tpaqueteservicio set codpaqueteservicio='20' where codpaqueteservicio='PS-020';
	update tpaqueteservicio set codpaqueteservicio='21' where codpaqueteservicio='PS-021';
	update tpaqueteservicio set codpaqueteservicio='22' where codpaqueteservicio='PS-022';
	update tpaqueteservicio set codpaqueteservicio='23' where codpaqueteservicio='PS-023';
	update tpaqueteservicio set codpaqueteservicio='24' where codpaqueteservicio='PS-024';
	update tpaqueteservicio set codpaqueteservicio='25' where codpaqueteservicio='PS-025';
	update tpaqueteservicio set codpaqueteservicio='26' where codpaqueteservicio='PS-026';
	update tpaqueteservicio set codpaqueteservicio='27' where codpaqueteservicio='PS-027';
	update tpaqueteservicio set codpaqueteservicio='28' where codpaqueteservicio='PS-028';
	update tpaqueteservicio set codpaqueteservicio='29' where codpaqueteservicio='PS-029';
	update tpaqueteservicio set codpaqueteservicio='30' where codpaqueteservicio='PS-030';
	update tpaqueteservicio set codpaqueteservicio='31' where codpaqueteservicio='PS-031';
	update tpaqueteservicio set codpaqueteservicio='32' where codpaqueteservicio='PS-032';
	update tpaqueteservicio set codpaqueteservicio='33' where codpaqueteservicio='PS-033';
	update tpaqueteservicio set codpaqueteservicio='34' where codpaqueteservicio='PS-034';
	update tpaqueteservicio set codpaqueteservicio='35' where codpaqueteservicio='PS-035';
	update tpaqueteservicio set codpaqueteservicio='36' where codpaqueteservicio='PS-036';
	update tpaqueteservicio set codpaqueteservicio='37' where codpaqueteservicio='PS-037';
	update tpaqueteservicio set codpaqueteservicio='38' where codpaqueteservicio='PS-038';
	update tpaqueteservicio set codpaqueteservicio='39' where codpaqueteservicio='PS-039';
	update tpaqueteservicio set codpaqueteservicio='40' where codpaqueteservicio='PS-040';
	update tpaqueteservicio set codpaqueteservicio='41' where codpaqueteservicio='PS-041';
	update tpaqueteservicio set codpaqueteservicio='42' where codpaqueteservicio='PS-042';
	update tpaqueteservicio set codpaqueteservicio='43' where codpaqueteservicio='PS-043';
	update tpaqueteservicio set codpaqueteservicio='44' where codpaqueteservicio='PS-044';
	update tpaqueteservicio set codpaqueteservicio='45' where codpaqueteservicio='PS-045';
	update tpaqueteservicio set codpaqueteservicio='46' where codpaqueteservicio='PS-046';
	update tpaqueteservicio set codpaqueteservicio='47' where codpaqueteservicio='PS-047';
	update tpaqueteservicio set codpaqueteservicio='48' where codpaqueteservicio='PS-048';
	update tpaqueteservicio set codpaqueteservicio='49' where codpaqueteservicio='PS-049';
	update tpaqueteservicio set codpaqueteservicio='50' where codpaqueteservicio='PS-050';
	update tpaqueteservicio set codpaqueteservicio='51' where codpaqueteservicio='PS-051';
	update tpaqueteservicio set codpaqueteservicio='52' where codpaqueteservicio='PS-052';
	update tpaqueteservicio set codpaqueteservicio='53' where codpaqueteservicio='PS-053';
	update tpaqueteservicio set codpaqueteservicio='54' where codpaqueteservicio='PS-054';
	update tpaqueteservicio set codpaqueteservicio='55' where codpaqueteservicio='PS-055';
	update tpaqueteservicio set codpaqueteservicio='56' where codpaqueteservicio='PS-056';
	update tpaqueteservicio set codpaqueteservicio='57' where codpaqueteservicio='PS-057';
	update tpaqueteservicio set codpaqueteservicio='58' where codpaqueteservicio='PS-058';
	update tpaqueteservicio set codpaqueteservicio='59' where codpaqueteservicio='PS-059';
	update tpaqueteservicio set codpaqueteservicio='60' where codpaqueteservicio='PS-060';
	update tpaqueteservicio set codpaqueteservicio='61' where codpaqueteservicio='PS-061';
	update tpaqueteservicio set codpaqueteservicio='62' where codpaqueteservicio='PS-062';
	update tpaqueteservicio set codpaqueteservicio='63' where codpaqueteservicio='PS-063';
	update tpaqueteservicio set codpaqueteservicio='64' where codpaqueteservicio='PS-064';
	update tpaqueteservicio set codpaqueteservicio='65' where codpaqueteservicio='PS-065';
	update tpaqueteservicio set codpaqueteservicio='66' where codpaqueteservicio='PS-066';
	update tpaqueteservicio set codpaqueteservicio='67' where codpaqueteservicio='PS-067';
	update tpaqueteservicio set codpaqueteservicio='68' where codpaqueteservicio='PS-068';
	update tpaqueteservicio set codpaqueteservicio='69' where codpaqueteservicio='PS-069';
	update tpaqueteservicio set codpaqueteservicio='70' where codpaqueteservicio='PS-070';
	update tpaqueteservicio set codpaqueteservicio='71' where codpaqueteservicio='PS-071';
	update tpaqueteservicio set codpaqueteservicio='72' where codpaqueteservicio='PS-072';
	update tpaqueteservicio set codpaqueteservicio='73' where codpaqueteservicio='PS-073';
	update tpaqueteservicio set codpaqueteservicio='74' where codpaqueteservicio='PS-074';
	update tpaqueteservicio set codpaqueteservicio='75' where codpaqueteservicio='PS-075';
	update tpaqueteservicio set codpaqueteservicio='76' where codpaqueteservicio='PS-076';
	update tpaqueteservicio set codpaqueteservicio='77' where codpaqueteservicio='PS-077';
	update tpaqueteservicio set codpaqueteservicio='78' where codpaqueteservicio='PS-078';
	update tpaqueteservicio set codpaqueteservicio='79' where codpaqueteservicio='PS-079';
	update tpaqueteservicio set codpaqueteservicio='80' where codpaqueteservicio='PS-080';
	update tpaqueteservicio set codpaqueteservicio='81' where codpaqueteservicio='PS-081';
	update tpaqueteservicio set codpaqueteservicio='82' where codpaqueteservicio='PS-082';
	update tpaqueteservicio set codpaqueteservicio='83' where codpaqueteservicio='PS-083';
	update tpaqueteservicio set codpaqueteservicio='84' where codpaqueteservicio='PS-084';
	update tpaqueteservicio set codpaqueteservicio='85' where codpaqueteservicio='PS-085';
--==========================
alter table tpaqueteservicio alter codpaqueteservicio type int using codpaqueteservicio::int;
--==========================
	update treservapaqueteservicio set codpaqueteservicio='50' where codpaqueteservicio='PS-050';
	update treservapaqueteservicio set codpaqueteservicio='51' where codpaqueteservicio='PS-051';
	update treservapaqueteservicio set codpaqueteservicio='49' where codpaqueteservicio='PS-049';
	update treservapaqueteservicio set codpaqueteservicio='49' where codpaqueteservicio='PS-049';
	update treservapaqueteservicio set codpaqueteservicio='49' where codpaqueteservicio='PS-049';
	update treservapaqueteservicio set codpaqueteservicio='50' where codpaqueteservicio='PS-050';
	update treservapaqueteservicio set codpaqueteservicio='49' where codpaqueteservicio='PS-049';
	update treservapaqueteservicio set codpaqueteservicio='29' where codpaqueteservicio='PS-029';
	update treservapaqueteservicio set codpaqueteservicio='29' where codpaqueteservicio='PS-029';
	update treservapaqueteservicio set codpaqueteservicio='30' where codpaqueteservicio='PS-030';
--==========================
alter table treservapaqueteservicio alter codpaqueteservicio type int using codpaqueteservicio::int;
--==========================
ALTER TABLE tpaqueteservicio ADD PRIMARY KEY(codpaqueteservicio);
ALTER TABLE treservapaqueteservicio ADD FOREIGN KEY(codpaqueteservicio) REFERENCES tpaqueteservicio(codpaqueteservicio);			