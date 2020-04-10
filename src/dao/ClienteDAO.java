/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Cliente;
import java.sql.Connection;
import jdbc.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Endereco;



/**
 *
 * @author Acer
 */
public class ClienteDAO {
    private Connection conn;
    
    public ClienteDAO(){
        this.conn = new ConnectionFactory().getConnection();
    }
    public void cadastrarCliente(Cliente obj){
        PreparedStatement st = null;
        try{
          st= conn.prepareStatement( "insert tb_cliente (nome,email,sexo,cpf,rg,cnpj,telefone,celular,data_nascimento)" +
                 " values(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
          st.setString(1, obj.getNome());
          st.setString(2, obj.getEmail());
          st.setString(3, obj.getSexo());
          st.setString(4, obj.getCpf());
          st.setString(5, obj.getRg());
          st.setString(6, obj.getCnpj());
          st.setString(7, obj.getTelefone());
          st.setString(8, obj.getCelular());
          st.setDate(9, new java.sql.Date(obj.getData_nascimento().getTime()));
       
        int ra = st.executeUpdate();
            if (ra > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                        int id = rs.getInt(1);
                        obj.setId(id);
                }
                ConnectionFactory.closeResultSet(rs);
            }else {
		throw new SQLException("Error! No rows affect");
		}
          JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
                  
        }catch(SQLException e){
          JOptionPane.showMessageDialog(null, "Erro" + e);
        }
        finally{
            ConnectionFactory.closeStatement(st);
            ConnectionFactory.closeConnection(conn);
        }
    }
    
    public void alterarCliente(Cliente obj){
                PreparedStatement st = null;
        try{
          st= conn.prepareStatement( "update tb_clientes set nome=?,email=?,sexo=?,cpf=?,rg=?,cnpj=?,telefone=?,celular=?,data_nascimento=? where id=?");
          st.setString(1, obj.getNome());
          st.setString(2, obj.getEmail());
          st.setString(3, obj.getSexo());
          st.setString(4, obj.getCpf());
          st.setString(5, obj.getRg());
          st.setString(6, obj.getCnpj());
          st.setString(7, obj.getTelefone());
          st.setString(8, obj.getCelular());
          st.setDate(9, new java.sql.Date(obj.getData_nascimento().getTime()));
          
          st.setInt(8, obj.getId());
          st.execute();
          JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");
                  
        }catch(SQLException e){
          JOptionPane.showMessageDialog(null, "Erro" + e);
        }
        finally{
            ConnectionFactory.closeStatement(st);
            ConnectionFactory.closeConnection(conn);
        }
    }
    
    public void excluirCliente(Cliente obj){
                PreparedStatement st = null;
        try{
          st= conn.prepareStatement( "delete from tb_cliente where id=? ");
          st.setInt(1, obj.getId());
   
          st.execute();
          JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");
                  
        }catch(SQLException e){
          JOptionPane.showMessageDialog(null, "Erro" + e);
        }
        finally{
            ConnectionFactory.closeStatement(st);
            ConnectionFactory.closeConnection(conn);
        }
        
        
    }
    private Cliente instCliente(ResultSet rs) throws SQLException{
          Cliente obj = new Cliente();
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setEmail(rs.getString("email"));
            obj.setSexo(rs.getString("sexo"));
            obj.setCpf(rs.getString("cpf"));
            obj.setRg(rs.getString("rg"));
            obj.setCnpj(rs.getString("cnpj"));
            obj.setTelefone(rs.getString("telefone"));
            obj.setCelular(rs.getString("celular"));
            obj.setData_nascimento(new java.util.Date(rs.getTimestamp("data_nascimento").getTime()));
            
            return obj;
    }
    public java.util.List<Cliente> listarClientes(){
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            java.util.List<Cliente> lista = new ArrayList();
            
            st= conn.prepareStatement("select * from tb_cliente");
            rs = st.executeQuery();
            
            while(rs.next()){
                Cliente obj = instCliente(rs);
                lista.add(obj);
            }
            return lista;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro:" + e);
            //return null;
        }
        finally{
            ConnectionFactory.closeStatement(st);
            ConnectionFactory.closeResultSet(rs);
            ConnectionFactory.closeConnection(conn);
        }
        return null;
    }
    public Cliente consultaPorNome(String nome){
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("select * from tb_cliente where nome = ?");
            st.setString(1, nome);
            rs= st.executeQuery();
            
            if(rs.next()){
                Cliente obj = instCliente(rs);
                return obj;
            }
            
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
        }
        finally{
            ConnectionFactory.closeStatement(st);
            ConnectionFactory.closeResultSet(rs);
            ConnectionFactory.closeConnection(conn);
        }
        return null;
    }
    public java.util.List<Cliente> buscaClientePorNome(String nome){
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            java.util.List<Cliente> lista = new ArrayList();
            
            st= conn.prepareStatement("select * from tb_cliente where nome like ?");
            st.setString(1, nome);
            rs = st.executeQuery();
            
            while(rs.next()){
                Cliente obj = instCliente(rs);
                lista.add(obj);
            }
            return lista;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro:" + e);
            //return null;
        }
        finally{
            ConnectionFactory.closeStatement(st);
            ConnectionFactory.closeResultSet(rs);
            ConnectionFactory.closeConnection(conn);
        }
        return null;
    }
   /*   public Cliente buscaCep(String cep) {
       
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        Cliente obj = new Cliente();
          Endereco end = new Endereco();
        if (webServiceCep.wasSuccessful()) {
            end.setEndereco(webServiceCep.getLogradouroFull());
            end.setCidade(webServiceCep.getCidade());
            end.setBairro(webServiceCep.getBairro());
            end.setEstado(webServiceCep.getUf());
            obj.setEndereco(end);
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }
    }*/
}
