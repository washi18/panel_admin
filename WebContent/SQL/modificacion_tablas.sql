
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

--creacion de tabla tpagoonline
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
	FOREIGN KEY (cReservaCod) REFERENCES TReserva,
);

insert into TPagoVisa values(1002,'R000000002',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E002',12344,'T002','A02','codigo de accion1','TAR0002',
			'JULIO GOMEZ','AA','CONTINENTAL','E0002','eci enviado 2','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1003,'R000000003',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E003',12344,'T003','A03','codigo de accion1','TAR0003',
			'RAUL GOMEZ','AA','CONTINENTAL','E0002','eci enviado 3','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1004,'R000000004',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E004',12344,'T004','A04','codigo de accion1','TAR0003',
			'PEDRO GOMEZ','AA','CONTINENTAL','E0002','eci enviado 4','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2'),
			(1005,'R000000005',125.50,0.3,'VISA','AUTORIZADO','2016-07-22 13:29:44','E005',12344,'T005','A05','codigo de accion1','TAR0003',
			'OSCAR GOMEZ','AA','CONTINENTAL','E0002','eci enviado 5','C00002','123',0.18,'2016-07-22 14:29:44','2016-07-22 14:34:44','2016-07-22 15:29:44','comercio2');

select * from tpagovisa;



CREATE TABLE TPagoPaypal
(
	nroOrden INT,						--codigo del numero de orden generado para el proceso de pago
	codPagador INT,						--codigo del pagador 
	codTransaccion VARCHAR(20),			--codigo de transaccion de paypal
	nPorcentaje decimal(10,2),			--porcentaje de pago del total del importe de la reserva
	cReservaCod VARCHAR(10),			--codigo de la reserva que se pretende pagar
	formaPago VARCHAR(20),				--forma de pago del cliente (VISA, MASTERCARD,PAYPAL)
	estado VARCHAR(10),					--estado de la transaccion (AUTORIZADO,CANCELADO,DENEGADO,EXTORNADO,INICIADO)
	importe decimal(10.2),				--importe de la reserva que se pretende pagar y/o amortizar
	nombrePagador VARCHAR(100),			--nombre del pagador
	nro_tarjeta VARCHAR(20),			--numero de la tarjeta del cliente
	adicionalPagador VARCHAR(100),		--datos adicionales del pagador 
	telefonoPagador VARCHAR(50),		--nro de telefono del pagador
	pais VARCHAR(3),					--pais del pagador
	estadoPagador VARCHAR(100),			--estado del pagador
	detalleImpuesto VARCHAR(20),		--detalle del impuesto a cobrar
	direccion VARCHAR(50),				--direccion del pagador
	emailPagador VARCHAR(100), 			--correo electronico del pagador
	PRIMARY KEY (nroOrden),
	FOREIGN KEY (cReservaCod) REFERENCES TReserva,
);


insert into tpagopaypal values(20001,40001,'C00001',0.40,'R000000006','PAYPAL','INICIADO',237.00,'LEONARDO CRUZ','T01938373',
								'trabaja en agencia','72353532','PERU','ESTADO1','impuesto sera 1','av.collasuyo A-15','leo@gmail.com'),
								(20002,40002,'C00002',0.40,'R000000007','PAYPAL','INICIADO',257.00,'JUAN CRUZ','T019123373',
								'trabaja en agencia','72353532','PERU','ESTADO1','impuesto sera 1','av.collasuyo A-15','juan@gmail.com'),
								(20003,40003,'C00003',0.40,'R000000008','PAYPAL','INICIADO',187.00,'MARIO CRUZ','T01924373',
								'trabaja en agencia','72353532','COLOMBIA','ESTADO1','impuesto sera 1','av.collasuyo A-15','mario@gmail.com'),
								(20004,40004,'C00004',0.40,'R000000009','PAYPAL','INICIADO',297.00,'CARMEN CRUZ','T01758373',
								'trabaja en agencia','72353532','USA','ESTADO1','impuesto sera 1','av.collasuyo A-15','carmen@gmail.com'),
								(20005,40005,'C00005',0.40,'R000000010','PAYPAL','INICIADO',183.00,'JOSE CRUZ','T01468373',
								'trabaja en agencia','72353532','ECUADOR','ESTADO1','impuesto sera 1','av.collasuyo A-15','jose@gmail.com');
								
								
								
								
								