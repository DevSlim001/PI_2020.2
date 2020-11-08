/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.DAO;

import br.com.pi.factory.ConnectionFactory;
import br.com.pi.model.Motoristas;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ControleMotoristas {
    ConnectionFactory conexao = new ConnectionFactory();
    Motoristas motoristas = new Motoristas();
    
    
    public void Salvar(Motoristas motoristas){
        conexao.conexao();
        try {
            PreparedStatement pst = conexao.con.prepareStatement("insert into motoristas(NOME, EMAIL, GENERO, MUNICIPIO, ENDERECO, ESTADO, SENHA, CPF, CEP, CELULAR) values(?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1,motoristas.getNome());
            pst.setString(2,motoristas.getEmail());
            pst.setString(3,motoristas.getGenero());
            pst.setString(4,motoristas.getMunicipio());
            pst.setString(5,motoristas.getEndereco());
            pst.setString(6,motoristas.getEstado());
            pst.setString(7,motoristas.getSenha());
            pst.setString(8,motoristas.getCpf());
            pst.setString(9,motoristas.getCep());
            pst.setString(10,motoristas.getTel());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
        }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao salvar os dados!\n"+ex);
            }
            
        conexao.desconecta();
    }
    
    public void Editar(Motoristas motoristas){
    conexao.conexao();
        try {
            PreparedStatement pst = conexao.con.prepareStatement("update motoristas set nome=?,email=?,genero=?,municipio=?,endereco=?,estado=?,senha=?,cpf=?,cep=?,celular=? where codigo=?");
            pst.setString(1,motoristas.getNome());
            pst.setString(2,motoristas.getEmail());
            pst.setString(3,motoristas.getGenero());
            pst.setString(4,motoristas.getMunicipio());
            pst.setString(5,motoristas.getEndereco());
            pst.setString(6,motoristas.getEstado());
            pst.setString(7,motoristas.getSenha());
            pst.setString(8,motoristas.getCpf());
            pst.setString(9,motoristas.getCep());
            pst.setString(10,motoristas.getTel());
            pst.setInt(11,motoristas.getId());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, "Falha ao editar dados dos Motoristas:\n"+ex);
        }
    
    conexao.desconecta();
    }
    
    public void Excluir(Motoristas motoristas){
        conexao.conexao();
        try {
            PreparedStatement pst = conexao.con.prepareStatement("delete from motoristas where codigo=?");
            pst.setInt(1, motoristas.getId());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados exluídos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir dados de Motoristas:\n"+ex);
        }
    
        conexao.desconecta();
    }
    
    public Motoristas buscaMotoristas(Motoristas motoristas){
        conexao.conexao();
        conexao.executasql("select *from motoristas where nome like'%"+motoristas.getPesquisa()+"%'");
        try {
            conexao.rs.first();
            motoristas.setId(conexao.rs.getInt("codigo"));
            motoristas.setNome(conexao.rs.getString("nome"));
            motoristas.setEmail(conexao.rs.getString("email"));
            motoristas.setGenero(conexao.rs.getString("genero"));
            motoristas.setMunicipio(conexao.rs.getString("municipio"));
            motoristas.setEndereco(conexao.rs.getString("endereco"));
            motoristas.setEstado(conexao.rs.getString("estado"));
            motoristas.setSenha(conexao.rs.getString("senha"));
            motoristas.setCpf(conexao.rs.getString("cpf"));
            motoristas.setCep(conexao.rs.getString("cep"));
            motoristas.setTel(conexao.rs.getString("celular"));
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao pesquisar Motoristas:\n"+ex);
        }
        
        conexao.desconecta();
        return motoristas;
    }
}
