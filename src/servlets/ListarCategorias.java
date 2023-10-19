package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Insurance;
import entity.InsuranceCategory;
import logic.InsuranceCategoryLogic;
import max.data.LogicResponse;

/**
 * Servlet implementation class ListarCategorias
 */
@WebServlet("/ListarCategorias")
public class ListarCategorias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarCategorias() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private InsuranceCategoryLogic ICL = new InsuranceCategoryLogic();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				LogicResponse<InsuranceCategory> lri = new LogicResponse<InsuranceCategory>();
				lri = ICL.getAll();
				int siz = lri.listReturned.size();
				String arrJson = "[";
				for(int i = 0; i < siz; i++) {
					InsuranceCategory a = lri.listReturned.get(i);
					arrJson += a.toJSON();
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
