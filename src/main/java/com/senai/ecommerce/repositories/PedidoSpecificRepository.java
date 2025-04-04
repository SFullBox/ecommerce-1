package com.senai.ecommerce.repositories;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.senai.ecommerce.enums.StatusDoPedido;

@Repository
public class PedidoSpecificRepository {
	
	@Autowired
	private DataSource dataSource;
	
	public ResponseEntity createOrderPerUser(String moment, StatusDoPedido status, Long clientId) throws Exception {
		
		String sqlCreate = """
				INSERT INTO tb_pedido(momento,status,cliente_id) VALUES(?,?,?)
				""";
		
		try(Connection con = dataSource.getConnection()) {
			try(PreparedStatement stmtCreate = con.prepareStatement(sqlCreate)){
				stmtCreate.setString(1,moment);
				String doStatus = status.toString();
				stmtCreate.setString(2,status.name());
				stmtCreate.setLong(3, clientId);
				
				stmtCreate.executeUpdate();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Pedido do usuário tal criado");
		
		
	}
}
