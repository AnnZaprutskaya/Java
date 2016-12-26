package edu.iba.controlModel;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.iba.db2ConnectionModel.DAOException;
import edu.iba.db2ConnectionModel.GroupDAO;
import edu.iba.db2ConnectionModel.MarkDAO;
import edu.iba.db2ConnectionModel.ProfessorDAO;
import edu.iba.db2ConnectionModel.StudentDAO;
import edu.iba.db2ConnectionModel.StudyDAO;
import edu.iba.db2ConnectionModel.TemplateDAO;
import edu.iba.db2ConnectionModel.UserDAO;


/**
 * Servlet implementation class listServlet
 */
@WebServlet("/content/list")
public class listServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TemplateDAO DAO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mode=request.getParameter("mode");
		if(mode==null||mode.isEmpty()){
			request.setAttribute("mode", "St");
			mode="St";
		}
		else{
			request.setAttribute("mode", mode);
		}
		if("St".equals(mode)){
			DAO=new StudentDAO();
		}
		if("Us".equals(mode)){
			DAO=new UserDAO();
		}
		if("Gr".equals(mode)){
			DAO=new GroupDAO();
		}
		if("Sd".equals(mode)){
			DAO=new StudyDAO();
		}
		if("Pr".equals(mode)){
			DAO=new ProfessorDAO();
		}
		if("Mr".equals(mode)){
			DAO=new MarkDAO();
		}
		Map<String, String> list;
		try {
			list = DAO.getList();
			request.setAttribute("listOfData", list);
			request.getRequestDispatcher("Pages/List.jsp").forward(request, response);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode=request.getParameter("mode");
		if(mode==null||mode.isEmpty()){
			request.setAttribute("mode", "St");
			mode="St";
		}
		else{
			request.setAttribute("mode", mode);
		}
		if(mode.equals("St")){
			DAO=new StudentDAO();
		}
		if(mode.equals("Us")){
			DAO=new UserDAO();
		}
		if(mode.equals("Gr")){
			DAO=new GroupDAO();
		}
		if(mode.equals("Sd")){
			DAO=new StudyDAO();
		}
		if(mode.equals("Pr")){
			DAO=new ProfessorDAO();
		}
		if(mode.equals("Mr")){
			DAO=new MarkDAO();
		}
		String id=request.getParameter("key");
		if(id!=null){
			try {
				DAO.delete(id);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				DAO.recountAvgMark();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		doGet(request, response);
	}

}
