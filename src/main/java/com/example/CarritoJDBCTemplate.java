package com.example; 

import java.util.List; 
import java.sql.Connection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


public class CarritoJDBCTemplate implements CarritoDAO {    

private JdbcTemplate jdbcTemplateObject; 
 
public void setDataSource(Connection connection) {   
    this.jdbcTemplateObject =      
        new JdbcTemplate(      
            new SingleConnectionDataSource(connection, false)     
    ); 
} 
 
public void create(Integer ID_Usuario, Integer ID_Articulo,
        Integer unidades) {   
    String SQL = "insert into Carrito (ID_Usuario, " +
    "ID_Articulo, unidades) values (?, ?, ?)";   
    jdbcTemplateObject.update(SQL, ID_Usuario, ID_Articulo, unidades);   
    System.out.println("Registro creado = " + unidades);   
    return; 
}

public Carrito getCarrito(Integer ID_Carrito) {
    String SQL = "select * from Carrito where ID_Carrito = ?";   
    Carrito carrito = jdbcTemplateObject.queryForObject(SQL,       
        new Object[]{ID_Carrito}, new CarritoMapper());   
    return carrito; 
} 
 
public List<Carrito> listCarrito() {   
    String SQL = "select * from Carrito";   
    List <Carrito> carritos =      
    jdbcTemplateObject.query(SQL, new CarritoMapper());   
    return carritos; 
}

public void delete(Integer ID_Carrito) {
    String SQL = "delete from Carrito where ID_Carrito = ?";
    jdbcTemplateObject.update(SQL, ID_Carrito);
    System.out.println("Borrado ID_Carrito = " + ID_Carrito );
    return;
}

public void update(Integer ID_Carrito, Integer ID_Usuario,
        Integer ID_Articulo, Integer unidades) {
    String SQL = "update Carrito set ID_Usuario = ?, ID_Articulo = ?, " +
    "unidades = ? where ID_Carrito = ?";
    jdbcTemplateObject.update(SQL, ID_Usuario, ID_Articulo, unidades, ID_Carrito);
    System.out.println("Actualizado ID = " + ID_Carrito );
    return;
}


} 