DROP DATABASE IF EXISTS farmacia;
 
CREATE DATABASE farmacia;

USE farmacia;

/******
*LOCAL*
******/
CREATE TABLE `farmacia`.`tb_local` (
`id_local` INT NOT NULL AUTO_INCREMENT ,
`nombre_local` VARCHAR(50) NULL ,
`direccion_local` VARCHAR(200) NULL ,
`telefono_local` VARCHAR(20) NULL ,
PRIMARY KEY (`id_local`));


/********
*USUARIO*
********/
CREATE  TABLE `farmacia`.`tb_cargo` (
`id_cargo` INT NOT NULL AUTO_INCREMENT ,
`nombre_cargo` VARCHAR(50) NULL ,
PRIMARY KEY (`id_cargo`));

CREATE  TABLE `farmacia`.`tb_personal` (
`id_personal` INT NOT NULL AUTO_INCREMENT ,
`id_cargo` INT NULL ,
`id_local` INT NULL ,
`dni_personal` VARCHAR(11) NULL ,
`nombre_personal` VARCHAR(50) NULL ,
`apellido_personal` VARCHAR(100) NULL ,
`telefono_personal` VARCHAR(20) NULL ,
`celular_personal` VARCHAR(20) NULL ,
PRIMARY KEY (`id_personal`),
INDEX `id_cargo_idx` (`id_cargo` ASC) ,
INDEX `id_local_idx` (`id_local` ASC) ,
CONSTRAINT `id_cargo_fk`
	FOREIGN KEY (`id_cargo` )
    REFERENCES `farmacia`.`tb_cargo` (`id_cargo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `id_local_fk`
	FOREIGN KEY (`id_local` )
    REFERENCES `farmacia`.`tb_local` (`id_local` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE  TABLE `farmacia`.`tb_usuario_tipo` (
`id_usuario_tipo` INT NOT NULL AUTO_INCREMENT ,
`nombre_usuario_tipo` VARCHAR(50) NULL ,
PRIMARY KEY (`id_usuario_tipo`));

CREATE  TABLE `farmacia`.`tb_usuario` (
`id_usuario` INT NOT NULL AUTO_INCREMENT ,
`nombre_usuario` VARCHAR(50) NULL ,
`contrasena_usuario` VARCHAR(50) NULL ,
`id_persona` INT NULL ,
`id_usuario_tipo` INT NULL ,
PRIMARY KEY (`id_usuario`) ,
INDEX `id_usuario_tipo_idx` (`id_usuario_tipo` ASC) ,
INDEX `id_persona_idx` (`id_persona` ASC) ,
CONSTRAINT `id_usuario_tipo_fk`
	FOREIGN KEY (`id_usuario_tipo` )
    REFERENCES `farmacia`.`tb_usuario_tipo` (`id_usuario_tipo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `id_personal_fk`
    FOREIGN KEY (`id_persona` )
    REFERENCES `farmacia`.`tb_personal` (`id_personal` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE  TABLE `farmacia`.`tb_permiso` (
`id_permiso` INT NOT NULL AUTO_INCREMENT ,
`nombre_permiso` VARCHAR(50) NULL ,
PRIMARY KEY (`id_permiso`));

CREATE  TABLE `farmacia`.`tb_permiso_usuario` (
`id_permiso` INT NULL ,
`id_usuario_tipo` INT NULL ,
`visible_permiso_usuario` BIT NULL ,
PRIMARY KEY (`id_permiso`, `id_usuario_tipo`) ,
INDEX `pu_id_permiso_idx` (`id_permiso` ASC) ,
INDEX `pu_id_usuario_tipo_idx` (`id_usuario_tipo` ASC) ,
CONSTRAINT `pu_id_permiso_fk`
	FOREIGN KEY (`id_permiso` )
    REFERENCES `farmacia`.`tb_permiso` (`id_permiso` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `pu_id_usuario_tipo_fk`
    FOREIGN KEY (`id_usuario_tipo` )
    REFERENCES `farmacia`.`tb_usuario_tipo` (`id_usuario_tipo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

/*********
*COMISION*
*********/

CREATE  TABLE `farmacia`.`tb_comision_tipo` (
`id_comision_tipo` INT NOT NULL AUTO_INCREMENT ,
`nombre_comision_tipo` VARCHAR(50) NULL ,
PRIMARY KEY (`id_comision_tipo`));

CREATE  TABLE `farmacia`.`tb_comision` (
`id_comision` INT NOT NULL AUTO_INCREMENT ,
`id_comision_tipo` INT NULL ,
`cantidad_comision` DOUBLE NULL ,
PRIMARY KEY (`id_comision`) ,
INDEX `id_comisio_tipon_idx` (`id_comision_tipo` ASC) ,
CONSTRAINT `id_comision_tipo_fk`
	FOREIGN KEY (`id_comision_tipo` )
    REFERENCES `farmacia`.`tb_comision_tipo` (`id_comision_tipo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

/**********
*PROVEEDOR*
**********/

CREATE  TABLE `farmacia`.`tb_proveedor` (
`id_proveedor` INT NOT NULL AUTO_INCREMENT ,
`ruc_proveedor` VARCHAR(11) NULL ,
`nombre_proveedor` VARCHAR(50) NULL ,
`telefono_proveedor` VARCHAR(20) NULL ,
`direccion_proveedor` VARCHAR(100) NULL ,
`laboratorio` BIT NULL DEFAULT 0,
PRIMARY KEY (`id_proveedor`));

CREATE  TABLE `farmacia`.`tb_proveedor_contacto` (
`id_proveedor_contacto` INT NOT NULL AUTO_INCREMENT ,
`id_proveedor` INT NULL ,
`dni_proveedor_contacto` VARCHAR(11) NULL ,
`nombre_proveedor_contacto` VARCHAR(50) NULL ,
`apellido_proveedor_contacto` VARCHAR(100) NULL ,
`telefono_proveedor_contacto` VARCHAR(20) NULL ,
`celular_proveedor_contacto` VARCHAR(20) NULL ,
PRIMARY KEY (`id_proveedor_contacto`) ,
INDEX `id_proveedor_idx` (`id_proveedor` ASC) ,
CONSTRAINT `id_proveedorn_fk`
	FOREIGN KEY (`id_proveedor` )
    REFERENCES `farmacia`.`tb_proveedor` (`id_proveedor` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

/*********
*PRODUCTO*
*********/

CREATE  TABLE `farmacia`.`tb_producto_tipo` (
`id_producto_tipo` INT NOT NULL AUTO_INCREMENT ,
`nombre_producto_tipo` VARCHAR(50) NULL ,
PRIMARY KEY (`id_producto_tipo`));

CREATE  TABLE `farmacia`.`tb_producto` (
`id_producto` INT NOT NULL AUTO_INCREMENT,
`id_producto_tipo` INT NULL ,
`id_proveedor` INT NULL ,
`id_comision` INT NULL ,
`id_local` INT NULL ,
`nombre_producto` VARCHAR(100) NULL ,
`descripcion_producto` VARCHAR(1000) NULL ,
`precio_producto` DOUBLE NULL ,
`sustancia_producto` VARCHAR(100) NULL ,
`presentacion_producto` VARCHAR(100) NULL ,
`stock_producto` INT NULL ,
`fecha_vencimiento_producto` TIMESTAMP NULL ,
PRIMARY KEY (`id_producto`) ,
INDEX `pro_id_producto_tipo_idx` (`id_producto_tipo` ASC) ,
INDEX `pro_id_proveedor_idx` (`id_proveedor` ASC) ,
INDEX `pro_id_comision_idx` (`id_comision` ASC) ,
INDEX `pro_id_local_idx` (`id_local` ASC) ,
CONSTRAINT `pro_id_producto_tipo_fk`
	FOREIGN KEY (`id_producto_tipo` )
    REFERENCES `farmacia`.`tb_producto_tipo` (`id_producto_tipo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `pro_id_proveedor_fk`
	FOREIGN KEY (`id_proveedor` )
    REFERENCES `farmacia`.`tb_proveedor` (`id_proveedor` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `pro_id_comision_fk`
    FOREIGN KEY (`id_comision` )
    REFERENCES `farmacia`.`tb_comision` (`id_comision` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `pro_id_local_fk`
	FOREIGN KEY (`id_local` )
    REFERENCES `farmacia`.`tb_local` (`id_local` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


/**********
*CADUCIDAD*
**********/

CREATE  TABLE `farmacia`.`tb_caducidad_estado` (
`id_caducidad_estado` INT NOT NULL AUTO_INCREMENT ,
`nombre_caducidad_estado` VARCHAR(50) NULL ,
PRIMARY KEY (`id_caducidad_estado`));

CREATE  TABLE `farmacia`.`tb_caducidad` (
`id_caducidad` INT NOT NULL AUTO_INCREMENT ,
`id_personal` INT NULL ,
`id_caducidad_estado` INT NULL ,
`id_local` INT NULL ,
`descripcion_caducidad` TEXT NULL ,
`fecha_creacion_caducidad` TIMESTAMP NULL ,
`fecha_limite_caducidad` TIMESTAMP NULL ,
PRIMARY KEY (`id_caducidad`) ,
INDEX `cad_id_personal_idx` (`id_personal` ASC) ,
INDEX `cad_id_caducidad_estado_idx` (`id_caducidad_estado` ASC) ,
INDEX `cad_id_local_idx` (`id_local` ASC) ,
CONSTRAINT `cad_id_personal`
    FOREIGN KEY (`id_personal` )
	REFERENCES `farmacia`.`tb_personal` (`id_personal` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `cad_id_caducidad_estado`
    FOREIGN KEY (`id_caducidad_estado` )
    REFERENCES `farmacia`.`tb_caducidad_estado` (`id_caducidad_estado` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `cad_id_local_fk`
	FOREIGN KEY (`id_local` )
    REFERENCES `farmacia`.`tb_local` (`id_local` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);


CREATE  TABLE `farmacia`.`tb_caducidad_detalle` (
`id_caducidad_detalle` INT NOT NULL AUTO_INCREMENT ,
`id_producto` INT NULL ,
`id_caducidad` INT NULL ,
`cantidad_caducidad_detalle` INT NULL ,
PRIMARY KEY (`id_caducidad_detalle`) ,
INDEX `id_producto_idx` (`id_producto` ASC) ,
INDEX `id_caducidad_idx` (`id_caducidad` ASC) ,
CONSTRAINT `id_producto_fk`
    FOREIGN KEY (`id_producto` )
	REFERENCES `farmacia`.`tb_producto` (`id_producto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `tb_caducidad_fk`
    FOREIGN KEY (`id_caducidad` )
    REFERENCES `farmacia`.`tb_caducidad` (`id_caducidad` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

/******
*VENTA*
******/

CREATE  TABLE `farmacia`.`tb_cliente` (
`id_cliente` INT NOT NULL AUTO_INCREMENT ,
`ruc_cliente` VARCHAR(11) NULL ,
`nombre_cliente` VARCHAR(50) NULL ,
`apellido_cliente` VARCHAR(50) NULL ,
`direccion_cliente` VARCHAR(100) NULL ,
PRIMARY KEY (`id_cliente`));

CREATE  TABLE `farmacia`.`tb_comprobante` (
`id_comprobante` INT NOT NULL AUTO_INCREMENT ,
`nombre_comprobante` VARCHAR(11) NULL ,
`formato_comprobante` VARCHAR(11) NULL ,
`correlativo_primario_comprobante` INT NULL ,
`correlativo_secundario_comprobante` INT NULL ,
PRIMARY KEY (`id_comprobante`));

CREATE  TABLE `farmacia`.`tb_venta` (
`id_venta` INT NOT NULL AUTO_INCREMENT ,
`id_personal` INT NULL ,
`id_cliente` INT NULL ,
`id_local` INT NULL ,
`id_comprobante` INT NULL ,
`numero_venta` VARCHAR(25) NULL ,
`fecha_venta` TIMESTAMP NULL ,
`descripcion_venta` TEXT NULL ,
`total_venta` DOUBLE NULL ,
PRIMARY KEY (`id_venta`) ,
INDEX `ven_id_personal_idx` (`id_personal` ASC) ,
INDEX `ven_id_cliente_idx` (`id_cliente` ASC) ,
INDEX `ven_id_local_idx` (`id_local` ASC) ,
CONSTRAINT `ven_id_personal`
    FOREIGN KEY (`id_personal` )
	REFERENCES `farmacia`.`tb_personal` (`id_personal` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `ven_tb_cliente`
    FOREIGN KEY (`id_cliente` )
    REFERENCES `farmacia`.`tb_cliente` (`id_cliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `ven_id_local_fk`
	FOREIGN KEY (`id_local` )
    REFERENCES `farmacia`.`tb_local` (`id_local` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `ven_id_comprobante_fk`
	FOREIGN KEY (`id_comprobante` )
    REFERENCES `farmacia`.`tb_comprobante` (`id_comprobante` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE  TABLE `farmacia`.`tb_venta_detalle` (
`id_venta_detalle` INT NOT NULL AUTO_INCREMENT ,
`id_producto` INT NULL ,
`id_venta` INT NULL ,
`cantidad_venta_detalle` INT NULL ,
`precio_unitario_venta_detalle` DOUBLE NULL ,
`precio_total_venta_detalle` DOUBLE NULL ,
PRIMARY KEY (`id_venta_detalle`) ,
INDEX `ved_id_producto_idx` (`id_producto` ASC) ,
INDEX `ved_id_venta_idx` (`id_venta` ASC) ,
CONSTRAINT `ved_id_producto_fk`
    FOREIGN KEY (`id_producto` )
	REFERENCES `farmacia`.`tb_producto` (`id_producto` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `ved_tb_venta_fk`
    FOREIGN KEY (`id_venta` )
    REFERENCES `farmacia`.`tb_venta` (`id_venta` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

