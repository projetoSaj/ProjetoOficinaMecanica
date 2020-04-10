/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ConnectionFactory {
    
    public Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/oficina_mecanica","Desenvolvedor","1234");
        }
        catch(SQLException e){
            throw new RuntimeException(e.getMessage());
       }
    }
    
    public static void closeConnection(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			}
			catch(SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
	public static void closeStatement(Statement st) {
		if(st!= null){
			try {
				st.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	public static void closeResultSet(ResultSet rs) {
		if(rs!= null){
			try {
				rs.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
}
