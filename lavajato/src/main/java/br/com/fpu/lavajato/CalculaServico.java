package br.com.fpu.lavajato;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/calculaServico")
public class CalculaServico extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CalculaNota nota = new CalculaNota();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			CalculaNota nota = new CalculaNota();

			nota.setNome(req.getParameter("nome"));
			nota.setVeiculo(req.getParameter("veiculo"));
			nota.setServicos(req.getParameterValues("servicos"));

			if (nota.getServicos() == null) {

				PrintWriter writer = resp.getWriter();
				writer.write("o serviço nao pode ser nulo");

			}else {
				nota.calculoNota();

				geraNota(resp, nota);
			}


		} catch (NumberFormatException e) {
			PrintWriter writer = resp.getWriter();
			writer.write("deve ser digitado apenas numeros");
		}

	}

	private void geraNota(HttpServletResponse resp, CalculaNota nota) throws IOException {
		PrintWriter writer = resp.getWriter();

		StringBuilder text = new StringBuilder();
		DecimalFormat fmt = new DecimalFormat("#,###.00");
		
		text.append("<html>");
		text.append("<head>");
		text.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		text.append("<meta charset=\"utf-8\">");
		text.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		text.append("<title>Somando com Servlet</title>");
		text.append("<link rel=\"stylesheet\"");
		text.append("href=\"resources/bootstrap-3.3.5-dist/css/bootstrap.min.css\">");
		text.append("<link rel=\"stylesheet\"");
		text.append("href=\"resources/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css\">");
		text.append("</head>");
		text.append("<body>");
		text.append("");

		text.append("<div class=\"container\"style=\"width:305px;\">");
		text.append("<div class=\"panel panel-default\">");
		text.append("<div class=\"panel-heading\">Nota Lava Jato</div>");

		text.append("<h6>Cliente:<strong>");
		text.append(nota.getNome());
		text.append("</strong></h6>");

		text.append("<h6>Veiculo:<strong>");
		text.append(nota.getVeiculo());
		text.append("</strong></h6>");

		text.append("<table class=\"table table-bordered table-striped\" style=\"width:300px;\"><thead>");
		text.append("<tr>");
		text.append("<th>Servico</th>");
		text.append("<th>valor</th>");
		text.append("</tr>");
		text.append("</thead><tbody>");
		for (String servico : nota.getServicos()) {

			switch (servico) {
			case "lavar":
				text.append("<tr>");
				text.append("<td>");
				text.append(servico);
				text.append("</td>");
				text.append("<td>R$");
				text.append(fmt.format(nota.getLavar()));
				text.append("</td>");
				text.append("</tr>");
				break;
			case "aspirar":
				text.append("<tr>");
				text.append("<td>");
				text.append(servico);
				text.append("</td>");
				text.append("<td>R$");
				text.append(fmt.format(nota.getAspirar()));
				text.append("</td>");
				text.append("</tr>");
				break;
			case "encerar":
				text.append("<tr>");
				text.append("<td>");
				text.append(servico);
				text.append("</td>");
				text.append("<td>R$");
				text.append(fmt.format(nota.getEncerar()));
				text.append("</td>");
				text.append("</tr>");
				break;
			}
		}

		text.append("<tr>");
		text.append("<td><em>SubTotal</em></td>");
		text.append("<td><em>R$");
		text.append(fmt.format(nota.getSubtotal()));
		text.append("</em></td>");
		text.append("</tr>");
		text.append("<tr>");
		text.append("<td><em>Desconto</em></td>");
		text.append("<td><em>R$");
		text.append(fmt.format(nota.getDesconto()));
		text.append("</em></td>");
		text.append("</tr>");
		text.append("<tr>");
		text.append("<td><strong>Total</strong></td>");
		text.append("<td><strong>R$");
		text.append(fmt.format(nota.getTotalNota()));
		text.append("</strong></td>");
		text.append("</tr>");
		text.append("</tbody></table>");
		text.append("</div>");
		text.append("</div>");
		text.append("</body>");
		text.append(" </html>");

		System.out.println(text.toString());
		// writer.print(String.format("Resultado da soma %s", resultado));
		writer.print(String.format(text.toString()));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		writer.write("suporte apenas para metodo post");
	}

}
