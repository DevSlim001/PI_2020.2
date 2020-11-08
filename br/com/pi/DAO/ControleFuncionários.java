/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi.DAO;

import br.com.pi.factory.ConnectionFactory;
import br.com.pi.model.Funcionários;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControleFuncionários {
    ConnectionFactory conexao = new ConnectionFactory();
    Funcionários funcionarios = new Funcionários();
    
    public void Salvar(Funcionários funcionarios){
        conexao.conexao();
        try {
            PreparedStatement pst = conexao.con.prepareStatement("insert into funcionarios(NOME, EMAIL, GENERO, MUNICIPIO, ENDERECO, ESTADO, SENHA, CPF, CEP, CELULAR) values(?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1,funcionarios.getNome());
            pst.setString(2,funcionarios.getEmail());
            pst.setString(3,funcionarios.getGenero());
            pst.setString(4,funcionarios.getMunicipio());
            pst.setString(5,funcionarios.getEndereco());
            pst.setString(6,funcionarios.getEstado());
            pst.setString(7,funcionarios.getSenha());
            pst.setString(8,funcionarios.getCpf());
            pst.setString(9,funcionarios.getCep());
            pst.setString(10,funcionarios.getTel());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
        }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao salvar os dados!\n"+ex);
            }
            
        conexao.desconecta();
    }
    public void Editar(Funcionários funcionarios){
    conexao.conexao();
        try {
            PreparedStatement pst = conexao.con.prepareStatement("update funcionarios set nome=?,email=?,genero=?,municipio=?,endereco=?,estado=?,senha=?,cpf=?,cep=?,celular=? where id=?");
            pst.setString(1,funcionarios.getNome());
            pst.setString(2,funcionarios.getEmail());
            pst.setString(3,funcionarios.getGenero());
            pst.setString(4,funcionarios.getMunicipio());
            pst.setString(5,funcionarios.getEndereco());
            pst.setString(6,funcionarios.getEstado());
            pst.setString(7,funcionarios.getSenha());
            pst.setString(8,funcionarios.getCpf());
            pst.setString(9,funcionarios.getCep());
            pst.setString(10,funcionarios.getTel());
            pst.setInt(11,funcionarios.getId());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, "Falha ao editar dados dos Funcionários:\n"+ex);
        }
    
    conexao.desconecta();
    }
    public void Excluir (Funcionários funcionarios){
    conexao.conexao();
        try {
            PreparedStatement pst = conexao.con.prepareStatement("delete from funcionarios where id=?");
            pst.setInt(1, funcionarios.getId());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados exluídos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir dados de Funcionários:\n"+ex);
        }
    
        conexao.desconecta();
    }
    
    public Funcionários buscaFuncionarios(Funcionários funcionarios){
        conexao.conexao();
        conexao.executasql("select *from funcionarios where nome like'%"+funcionarios.getPesquisa()+"%'");
        try {
            conexao.rs.first();
            funcionarios.setId(conexao.rs.getInt("id"));
            funcionarios.setNome(conexao.rs.getString("nome"));
            funcionarios.setEmail(conexao.rs.getString("email"));
            funcionarios.setGenero(conexao.rs.getString("genero"));
            funcionarios.setMunicipio(conexao.rs.getString("municipio"));
            funcionarios.setEndereco(conexao.rs.getString("endereco"));
            funcionarios.setEstado(conexao.rs.getString("estado"));
            funcionarios.setSenha(conexao.rs.getString("senha"));
            funcionarios.setCpf(conexao.rs.getString("cpf"));
            funcionarios.setCep(conexao.rs.getString("cep"));
            funcionarios.setTel(conexao.rs.getString("celular"));
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao pesquisar Funcionarios:\n"+ex);
        }
        
        conexao.desconecta();
        return funcionarios;
    }
}
