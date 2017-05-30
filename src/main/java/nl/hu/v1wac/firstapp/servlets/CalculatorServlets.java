package nl.hu.v1wac.firstapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/CalculatorServlets.do")
public class CalculatorServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {

		int number1 = Integer.valueOf(req.getParameter("number1"));
		int number2 = Integer.valueOf(req.getParameter("number2"));
		String function = req.getParameter("function");
		
		int output = 0;
		
		switch(function) {
			case("+"): output = number1 + number2; break;
			case("-"): output = number1 - number2; break;
			case("*"): output = number1 * number2; break;
			case("/"): output = number1 / number2; break;
		}
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println(" <title>Dynamic Example</title>");
		out.println(" <body>");
		out.println(" <h2>Dynamic webapplication example</h2>");
		out.println(" <h2>Antwoord: " + output + "</h2>");
		out.println(" </body>");
		out.println("</html>");
	}
}