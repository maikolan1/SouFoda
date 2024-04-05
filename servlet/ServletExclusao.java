package br.com.vemprafam.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vemprafam.dao.DaoFuncionario;
import br.com.vemprafam.pojo.Funcionario;


@WebServlet("/ServletExclusao")
public class ServletExclusao extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ServletExclusao() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out = response.getWriter();
	int re = Integer.parseInt(request.getParameter("re"));
	DaoFuncionario dao = new DaoFuncionario();
	dao.excluir(new Funcionario(re,null,null,0));
	out.println("<!DOCTYPE html>\r\n"
			+ "<html>\r\n"
			+ "<head>\r\n"
			+ "<meta charset=\"ISO-8859-1\">\r\n"
			+ "<title>Resultado</title>\r\n"
			+ "</head>\r\n"
			+ "<body>\r\n"
			+ "<p>RE "+re+" excluído</p>\r\n"
			+ "<a href='index.html'>voltar</a>\r\n"
			+ "</body>\r\n"
			+ "</html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
