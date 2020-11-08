package br.com.pi.factory;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConnectionFactory {
    //Declaração das variáveis
    
    public Statement stm; //Realizar uma pesquisa no banco de dados
    public ResultSet rs; //armazenar o valor da pesquisa no banco de dados
    private String driver = "org.postgresql.Driver"; //serviço do banco de dados
    private String caminho = "jdbc:postgresql://localhost:5432/IACIT"; //caminho do banco de dados
    private String usuario = "postgres"; //usuário do banco de dados
    private String senha = "1234"; // usuário do banco de dados (1234)
    public Connection con; //Conexão com o banco de dados
    
    public void conexao(){
        try{
        System.setProperty("jdbc.Drivers", driver);
        con=DriverManager.getConnection(caminho, usuario, senha);
        // JOptionPane.showMessageDialog(null, "Conexão Efetuada com sucesso!!!");
        }   catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao se conectar com o Banco de Dados!\n"+ex.getMessage());
            }
    }
    
    public void executasql(String sql){
        try{
            stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ExecutaSql:\n"+ex.getMessage());
        }
    }
    
    public void desconecta(){
        try{
            con.close();
            // JOptionPane.showMessageDialog(null, "Banco de Dados desconectado com sucesso!");
        }   catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão com o Banco de Dados!\n"+ex.getMessage());
            }
    }
}
    


