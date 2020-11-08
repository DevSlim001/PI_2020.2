/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.DAO;
import br.com.pi.factory.ConnectionFactory;
import br.com.pi.model.Veículos;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControleVeículos {
    ConnectionFactory conexao = new ConnectionFactory();
    Veículos veiculos = new Veículos();
    
    public void Salvar(Veículos veiculos){
        conexao.conexao();
        try {
            PreparedStatement pst = conexao.con.prepareStatement("insert into veiculos(NOME, MARCA, PLACA, ANO, COR) values(?,?,?,?,?)");
            pst.setString(1,veiculos.getNome());
            pst.setString(2,veiculos.getMarca());
            pst.setString(3,veiculos.getPlaca());
            pst.setString(4,veiculos.getAno());
            pst.setString(5,veiculos.getCor());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
        }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao salvar os dados!\n"+ex);
            }
            
        conexao.desconecta();
    }
    
    public void Excluir (Veículos veiculos){
    conexao.conexao();
        try {
            PreparedStatement pst = conexao.con.prepareStatement("delete from veiculos where identificacao=?");
            pst.setInt(1, veiculos.getId());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados exluídos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir dados de Motoristas:\n"+ex);
        }
    
        conexao.desconecta();
    }
    
    public void Editar(Veículos veiculos){
    conexao.conexao();
        try {
            PreparedStatement pst = conexao.con.prepareStatement("update veiculos set nome=?,marca=?,placa=?,ano=?,cor=? where identificacao=?");
            pst.setString(1,veiculos.getNome());
            pst.setString(2,veiculos.getMarca());
            pst.setString(3, veiculos.getPlaca());
            pst.setString(4, veiculos.getAno());
            pst.setString(5, veiculos.getCor());
            pst.setInt(6,veiculos.getId());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, "Falha ao editar dados dos Veículos:\n"+ex);
        }
    
    conexao.desconecta();
    }
    
    public Veículos buscaVeiculos (Veículos veiculos){
        conexao.conexao();
        conexao.executasql("select *from veiculos where nome like'%"+veiculos.getPesquisa()+"%'");
        try {
            conexao.rs.first();
            veiculos.setId(conexao.rs.getInt("identificacao"));
            veiculos.setNome(conexao.rs.getString("nome"));
            veiculos.setMarca(conexao.rs.getString("marca"));
            veiculos.setPlaca(conexao.rs.getString("placa"));
            veiculos.setAno(conexao.rs.getString("ano"));
            veiculos.setCor(conexao.rs.getString("cor"));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao pesquisar Veículos:\n"+ex);
        }
        
        conexao.desconecta();
        return veiculos;
    }
    
}
