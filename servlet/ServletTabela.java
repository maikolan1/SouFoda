package br.com.vemprafam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vemprafam.dao.DaoAluno;
import br.com.vemprafam.pojo.Aluno;

@WebServlet("/ServletTabela")
public class ServletTabela extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ServletTabela() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		DaoAluno dao = new DaoAluno();
		out.println("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"ISO-8859-1\">\r\n"
				+ "<title>tabela</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "<table border='1'>\r\n"
				+ "<tr>\r\n"
				+ "	<th>RA</th>\r\n"
				+ "	<th>nome</th>\r\n"
				+ "	<th>data de Nascimento</th>\r\n"
				+ "	<th>renda</th>\r\n"
				+ "</tr>\r\n");
		        List<Aluno> lista = dao.getLista();

		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

		        for(Aluno a: lista){
		        	out.println("<tr>\r\n"
				+ "	<td>"+a.getRa()+"</td>\r\n"
				+ "	<td>"+a.getNome()+"</td>\r\n"
				+ "	<td>"+dateFormat.format(a.getDataNascimento())+"</td>\r\n"
				+ "	<td>"+numberFormat.format(a.getRenda())+"</td>\r\n"
				+ "</tr>\r\n");
		        }

		        out.println("</table>\r\n"
				+ "</body>\r\n"
				+ "</html>\r\n");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
