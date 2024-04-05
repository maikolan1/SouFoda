package br.com.vemprafam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.vemprafam.pojo.Aluno;

public class DaoAluno {
	private String user = "SA";
	private String password = "";
	private String url = "jdbc:hsqldb:hsql://localhost/";
	private Connection connection;

	public DaoAluno() {
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void inserir( Aluno aluno ) {
		try {
			String sql = "INSERT INTO ALUNOS VALUES(?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1,  aluno.getRa());
			pstmt.setString(2, aluno.getNome());
			pstmt.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
			pstmt.setDouble(4, aluno.getRenda());
			System.out.println("Linhas inseridas: "+pstmt.executeUpdate());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Aluno> getLista() {
		List<Aluno> result = new ArrayList<Aluno>();
		try {
			String sql = "SELECT ra,nome,dataNascimento,renda from Alunos";
			PreparedStatement pstmt=connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int ra = rs.getInt(1);
				String nome = rs.getString("nome");
				Date dataNascimento = rs.getDate("dataNascimento");
				double renda = rs.getDouble("renda");
				Aluno aluno = new Aluno(ra,nome,dataNascimento,renda);
				result.add(aluno);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Aluno buscarPeloRa(int ra) {
		try {
			String sql = "SELECT ra,nome,dataNascimento,renda from Alunos WHERE RA=?";
			PreparedStatement pstmt=connection.prepareStatement(sql);
			pstmt.setInt(1, ra);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int raNovo = rs.getInt(1);
				String nome = rs.getString("nome");
				Date dataNascimento = rs.getDate("dataNascimento");
				double renda = rs.getDouble("renda");
				Aluno aluno = new Aluno(ra,nome,dataNascimento,renda);
				return aluno;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void atualizar(Aluno aluno) {
		try {
			String sql = "UPDATE ALUNOS SET nome=?,dataNascimento=?,renda=? WHERE ra=?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,  aluno.getNome());
			pstmt.setDate(2, new java.sql.Date(aluno.getDataNascimento().getTime()));
			pstmt.setDouble(3, aluno.getRenda());
			pstmt.setInt(4, aluno.getRa());
			System.out.println("Linhas atualizadas: "+pstmt.executeUpdate());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void excluir(Aluno aluno) {
		try {
			String sql = "DELETE FROM ALUNOS WHERE RA=?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1,  aluno.getRa());
			System.out.println("Linhas excluídas: "+pstmt.executeUpdate());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		DaoAluno dao= new DaoAluno();

		dao.atualizar(new Aluno(444, "Joaquim", new Date(), 800.0));

	}

}
