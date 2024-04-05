package br.com.vemprafam.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServLetImc")
public class ServLetImc extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServLetImc() {
        super();
       }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		double peso = Double.parseDouble(request.getParameter("peso"));
		double altura = Double.parseDouble(request.getParameter("altura"));
		double imc = peso/(altura*altura);
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"ISO-8859-1\">\r\n"
				+ "<title>Resultado!</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "<p>O IMC é: "+String.format("%.2f",imc)
				+ "</p>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
