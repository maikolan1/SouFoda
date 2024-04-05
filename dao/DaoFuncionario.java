package br.com.vemprafam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.vemprafam.pojo.Funcionario;

public class DaoFuncionario {
	private String user = "SA";
	private String password = "";
	private String url = "jdbc:hsqldb:hsql://localhost/";
	private Connection connection;

	public DaoFuncionario() {
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public void inserir(Funcionario funcionario) {
		try {
			String sql = "INSERT INTO FUNCIONARIOS VALUES(?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, funcionario.getRe());
			pstmt.setString(2, funcionario.getNome());
			pstmt.setDate(3, new java.sql.Date(funcionario.getDataAdmissao().getTime()));
			pstmt.setDouble(4, funcionario.getSalario());
			System.out.println("Linhas inseridas: "+pstmt.executeUpdate());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Funcionario> getLista() {
		List<Funcionario> result = new ArrayList<Funcionario>();
		try {
			String sql = "SELECT re,nome,dataAdmissao,salario from FUNCIONARIOS";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int re = rs.getInt(1);
				String nome = rs.getString("nome");
				Date dataAdmissao = rs.getDate("dataAdmissao");
				double salario = rs.getDouble("salario");
				Funcionario funcionario = new Funcionario(re, nome, dataAdmissao, salario);
				result.add(funcionario);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Funcionario buscarPeloRe(int re) {
		try {
			String sql = "SELECT re,nome,dataAdmissao,salario from FUNCIONARIOS WHERE RE=?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, re);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int reNovo = rs.getInt(1);
				String nome = rs.getString("nome");
				Date dataAdmissao = rs.getDate("dataAdmissao");
				double salario = rs.getDouble("salario");
				Funcionario funcionario = new Funcionario(re, nome, dataAdmissao, salario);
				return funcionario;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void atualizar(Funcionario funcionario) {
		try {
			String sql = "UPDATE FUNCIONARIOS SET nome=?,dataAdmissao=?,salario=? WHERE RE=?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, funcionario.getNome());
			pstmt.setDate(2, new java.sql.Date(funcionario.getDataAdmissao().getTime()));
			pstmt.setDouble(3, funcionario.getSalario());
			pstmt.setInt(4, funcionario.getRe());
			System.out.println("Linhas atualizadas: "+pstmt.executeUpdate());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void excluir(Funcionario funcionario) {
		try {
			String sql = "DELETE FROM FUNCIONARIOS WHERE RE=?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, funcionario.getRe());
			System.out.println("Linhas excluídas: "+pstmt.executeUpdate());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		DaoFuncionario dao = new DaoFuncionario();
	}

}
