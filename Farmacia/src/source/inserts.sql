INSERT INTO `farmacia`.`tb_local`(nombre_local,direccion_local,telefono_local)VALUES
('ACORFARMA Lima','AV. PERU 424 SAN MARTIN DE PORRES','+51 525-4489');

INSERT INTO `farmacia`.`tb_cargo`(nombre_cargo)VALUES 
('Otros'),('Quimico Farmceutico'),('Técnico Farmaceutico');

INSERT INTO `farmacia`.`tb_personal`(id_cargo,id_local,dni_personal,nombre_personal,apellido_personal,telefono_personal,celular_personal)VALUES
(1,1,'45241487','ADMIN','SISTEMA','',''),
(2,1,'45241487','JOSE LUIS','FELICIANO SOTO','+51 515-4547','992 154 700'),
(2,1,'41021534','ELVIRA MARIA','AREVALOS MOZA','+51 525-4788','993 154 850'),
(3,1,'28548745','KAREN ELIAS','MORILLO BRIONES','+51 536-2124','995 984 525'),
(3,1,'34874874','WILLIAM MAXIMO','GONZALES GARCIA','+51 515-8855','992 951 357');

INSERT INTO `farmacia`.`tb_usuario_tipo`(nombre_usuario_tipo)VALUES
('ADMINISTRADOR'),
('QFARMACEUTICO'),
('TFARMACEUTICO');

INSERT INTO `farmacia`.`tb_usuario`(nombre_usuario,contrasena_usuario,id_persona,id_usuario_tipo)
VALUES
('admin','123',1,1),
('jfeliciano','123',2,2),
('earevalos','123',3,2),
('kmorillo','123',4,3),
('wgonzales','123',5,3);

INSERT INTO `farmacia`.`tb_permiso`(nombre_permiso)VALUES
('INICIO'),
('MANTENIMIENTO'),
('PROCESOS'),
('CONSULTAS');

INSERT INTO `farmacia`.`tb_permiso_usuario`(id_permiso,id_usuario_tipo,visible_permiso_usuario)VALUES
(1,1,TRUE),
(2,1,TRUE),
(3,1,TRUE),
(4,1,TRUE),
(1,2,TRUE),
(2,2,FALSE),
(3,2,TRUE),
(4,2,TRUE),
(1,3,TRUE),
(2,3,TRUE),
(3,3,FALSE),
(4,3,TRUE);

INSERT INTO `farmacia`.`tb_comprobante`(nombre_comprobante,formato_comprobante,correlativo_primario_comprobante,correlativo_secundario_comprobante)VALUES
('BOLETA', 'B#-#',1,0),
('FACTURA', 'F#-#',1,0);

INSERT INTO `farmacia`.`tb_cliente`(ruc_cliente,nombre_cliente,apellido_cliente,direccion_cliente)
VALUES
('1011818111','JUAN','PEREZ',''),
('1042587490','PEDRO','GALVEZ',''),
('8948418448','PABLO','MARMOL','AV. PERU 431 - SMP');


INSERT INTO `farmacia`.`tb_caducidad_estado`(nombre_caducidad_estado)VALUES
('CREADO'),
('EVALUANDO'),
('CANCELADO'),
('ACEPTADO'),
('RECHAZADO');

INSERT INTO `farmacia`.`tb_proveedor`(ruc_proveedor,nombre_proveedor,telefono_proveedor,direccion_proveedor,laboratorio)VALUES
('20452448780','LABORATORIO COLTIFAR','+51 554-3184','AV ALJOVIN 123 - SAN ISIDRO',TRUE),
('20153144740','PASTILLAR S.A.C','+51 551-1237','AV MIROQUESADA 953 - COMAS',FALSE),
('20132887798','JARABEXPERT S.A.C','+51 513-9851','AV UNIVERSARIA 635 - LA MARINA',FALSE),
('20151577989','LABORATORIO QUIMICA PURA','+51 254-3184','AV 28 DE JULIO 423 - SAN ISIDRO',TRUE);


INSERT INTO `farmacia`.`tb_producto_tipo`(nombre_producto_tipo)VALUES
('JABABES'),
('GOTAS'),
('COMPRIMIDOS'),
('CAPSULAS'),
('PASTILLAS'),
('PILDORAS'),
('POLVOS'),
('POMADAS'),
('CHAMPÚS');

INSERT INTO `farmacia`.`tb_comision_tipo`(nombre_comision_tipo)VALUES
('FIJO'),
('CANTIDAD'),
('PORCENTAJE');

INSERT INTO `farmacia`.`tb_comision`(id_comision_tipo,cantidad_comision)VALUES
(1,1),
(1,2),
(2,1),
(3,0.1),
(3,0.2);

