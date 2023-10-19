package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import max.*;
import max.data.LogicResponse;
import entity.*;
import logic.*;

/**
 * Servlet implementation class ListarServlet
 */
@WebServlet("/ListarServlet")
public class ListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected InsuranceLogic IL = new InsuranceLogic();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LogicResponse<Insurance> lri = new LogicResponse<Insurance>();
		String parCat = request.getParameter("category");
		if(parCat != null) {
			try {
	            int n = Integer.parseInt(parCat);
	            lri = n >= 0 ? IL.filterByCategory(n) : IL.getAll();
	        } catch (NumberFormatException e) {
	        	lri = IL.getAll();
	        }
		} else lri = IL.getAll();
		int siz = lri.listReturned.size();
		String arrJson = "[";
		for(int i = 0; i < siz; i++) {
			Insurance a = lri.listReturned.get(i);
			arrJson += a.toJSON();
			System.out.println(a.toJSON());
			if(i != siz - 1) {
				arrJson += ", ";
			}
		}
		arrJson += "]";
		String finalJSON = "{ \"data\": " + arrJson + " }";
		//response.setContentType("application/json");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(finalJSON);
        out.flush(); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
