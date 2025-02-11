package com.senai.ecommerce.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoRespositorySpecific {
	@Autowired
	private DataSource dataSource;
	
	public ResponseEntity<?> PostProduct(String nome,String descricao,Double preco,String imgUrl) throws Exception{
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = dataSource.getConnection();
			
			String Sql = """
					INSERT INTO tb_produto(nome,descricao,preco,img_url) VALUES(?,?,?,?)
					""";
			stmt = con.prepareStatement(Sql);
			stmt.setString(1, nome);
			stmt.setString(2, descricao);
			stmt.setDouble(3, preco);
			stmt.setString(4, imgUrl);
			stmt.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			
		}
		finally {
			if(con !=null) {
				con.close();
			}
			if(stmt!=null) {
				stmt.close();
			}
		}
		return ResponseEntity.ok("Usuário cadastrado");
	}
}
