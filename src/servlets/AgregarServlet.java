package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Insurance;
import entity.InsuranceCategory;
import logic.InsuranceLogic;

import max.data.*;

/**
 * Servlet implementation class AgregarServlet
 */
@WebServlet("/AgregarServlet")
public class AgregarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected String LogicResponseToJSON(max.data.LogicResponse<Insurance> obj) {
    	String fn = "{ ";
    	fn += "\"status\":  " + (obj.status ? "true" : "false") + ", ";
    	fn += "\"message\": \"" + (obj.message) + "\", ";
    	fn += "\"errormessage\": \"" + (obj.errorMessage) + "\" ";
    	fn += " }";
    	return fn;
    }
    
    protected void die(HttpServletResponse res, String msg) throws IOException {
    	String json = "{ \"status\": false, \"message\": \"\", \"errormessage\": \"" + msg + "\" }";
    	PrintWriter out = res.getWriter();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush(); 
    }
    
    protected void addInsurance(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String description = "";
    	if(req.getParameter("description") == null) {
    		die(res, "'description' is null. ");
    		return;
    	} else description = (String) req.getParameter("description");

    	if(req.getParameter("category") == null) {
    		die(res, "'category' is null. ");
    		return;
    	} else {
    		try {
    			int cat = Integer.parseInt((String) req.getParameter("category"));
    		} catch(NumberFormatException e) {
    			die(res, "'category' is not a number. ");
    			return;
    		}
    	}
    	
    	
    	if(req.getParameter("hiringCost") == null) {
    		die(res, "'hiringCost' is null. ");
    		return;
    	} else {
    		try {
    			double hc = Double.parseDouble((String) req.getParameter("hiringCost"));
    		} catch(NumberFormatException e) {
    			die(res, "'hiringCost' is not a double. ");
    			return;
    		}
    	}
    	if(req.getParameter("insuredCost") == null) {
    		die(res, "'insuredCost' is null. ");
    		return;
    	} else {
    		try {
    			double hc = Double.parseDouble((String) req.getParameter("insuredCost"));
    		} catch(NumberFormatException e) {
    			die(res, "'insuredCost' is not a double. ");
    			return;
    		}
    	}
    	try {
    		int categoryId = Integer.parseInt((String) req.getParameter("category"));
    		double hiringCost = Double.parseDouble((String) req.getParameter("hiringCost"))
    			 , insuredCost = Double.parseDouble((String) req.getParameter("insuredCost"));
    		String ds = description;
    		Insurance obj = new Insurance() {{
    			setDescription(ds);
    			setCategory(new InsuranceCategory() {{ setId(categoryId); }});
    			setHiringCost(hiringCost);
    			setInsuredCost(insuredCost);
    		}};
    		LogicResponse<Insurance> result = new InsuranceLogic().insert(obj);
    		String strResult = LogicResponseToJSON(result);
    		PrintWriter out = res.getWriter();
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            out.print(strResult);
            out.flush(); 
    		
    	} catch(NumberFormatException | SQLException e) {
    		e.printStackTrace();
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Cannot GET ").append(request.getContextPath());
		die(response, "Cannot GET " + request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		addInsurance(request, response);
	}

}
