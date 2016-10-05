

ALTER TABLE tpaquete ADD COLUMN nDiaCaminoInka int DEFAULT 0
ALTER TABLE tpaquete alter COLUMN nDiaCaminoInka drop DEFAULT

SELECT  * from tdestinohotel  

ALTER TABLE tpaquetedestino ADD COLUMN nNoches int DEFAULT 1
ALTER TABLE tpaquetedestino alter COLUMN nNoches drop DEFAULT

ALTER TABLE tdestino ADD COLUMN nCodPostal int DEFAULT 84
ALTER TABLE tdestino alter COLUMN nCodPostal drop DEFAULT