package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Insurance;
import entity.InsuranceCategory;
import logic.InsuranceLogic;

import max.data.*;
import max.schema.Schema;
import max.schema.SchemaProperty;
import max.schema.SchemaValidationResult;

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
    	Schema parameters = new Schema("notb", "nobd");
    	parameters.setProperties(
    		new SchemaProperty("description") {{
    			required = true;
    			type = Types.VARCHAR;
    			maxlength = 200;
    			minlength = 1;
    		}},
    		new SchemaProperty("category") {{
    			required = true;
    			type = Types.INTEGER;
    			min = 0;
    			ref = InsuranceCategory._schema.ref("idTipo");
    		}},
    		new SchemaProperty("hiringCost") {{
    			required = true;
    			type = Types.DOUBLE;
    			min = 0;
    		}},
    		new SchemaProperty("insuredCost") {{
    			required = true;
    			type = Types.DOUBLE;
    			min = 0;
    		}}
    	);
    	Object _description = req.getParameter("description");
    	Object _category = req.getParameter("category"), _hc = req.getParameter("hiringCost"), _ic = req.getParameter("insuredCost");
    	try {
	    	_category = req.getParameter("category") == null ? null : Integer.parseInt(req.getParameter("category"));
	    	_hc = req.getParameter("hiringCost") == null ? null : Double.parseDouble(req.getParameter("hiringCost"));
	    	_ic = req.getParameter("insuredCost") == null ? null : Double.parseDouble(req.getParameter("insuredCost"));    		
    	} catch(NumberFormatException e) {
    		e.printStackTrace();
    	}
    	
    	Dictionary values = Dictionary.fromArray( 
    		"description", _description,
    		"category", _category,
    		"hiringCost", _hc,
    		"insuredCost", _ic
    	);
    	
    	SchemaValidationResult svr = parameters.validate(values);
    	if(!svr.status) {
    		die(res, svr.message);
    		return;
    	}
    	
    	
    	try {
    		String description = (String) req.getParameter("description");
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
