package br.com.vemprafam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vemprafam.dao.DaoFuncionario;
import br.com.vemprafam.pojo.Funcionario;


@WebServlet("/ServelTabela2")
public class ServelTabela2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServelTabela2() {
        super();
        }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		DaoFuncionario dao  = new DaoFuncionario();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

			int re = Integer.parseInt(request.getParameter("re"));
			String nome = request.getParameter("nome");
			Date dataAdmissao;
			try {
				dataAdmissao = dateFormat.parse(request.getParameter("dataAdmissao"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				dataAdmissao = new Date();
			}
			double salario = Double.parseDouble(request.getParameter("salario"));
			dao.inserir(new Funcionario(re,nome,dataAdmissao,salario));

		out.println("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"ISO-8859-1\">\r\n"
				+ "<title>Tabela Funcionário</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "<h1>1 Funcionário cadastrado</h1>\r\n"
				+ "<table border='1'>\r\n"
				+ "<tr>\r\n"
				+ "	<th>RE</th>\r\n"
				+ "	<th>Nome</th>\r\n"
				+ "	<th>Data de Admissão</th>\r\n"
				+ "	<th>Salário</th>\r\n"
				+ "</tr>\r\n");
				List<Funcionario> lista = dao.getLista();

		        for(Funcionario f: lista) {
		        	out.println("</tr>\r\n"
				+ "<tr>\r\n"
				+ "	<td>"+f.getRe()+"</td>\r\n"
				+ "	<td>"+f.getNome()+"</td>\r\n"
				+ "	<td>"+dateFormat.format(f.getDataAdmissao())+"</td>\r\n"
				+ "	<td>"+numberFormat.format(f.getSalario())+"</td>\r\n"
				+ "</tr>\r\n");
		        }
				out.println("</table>\r\n"
			    + "<a href='index.html'>voltar</a>"
				+ "</body>\r\n"
				+ "</html>\r\n");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
