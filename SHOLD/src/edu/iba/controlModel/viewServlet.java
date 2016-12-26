package edu.iba.controlModel;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.iba.dataModel.Group;
import edu.iba.dataModel.Mark;
import edu.iba.dataModel.Professor;
import edu.iba.dataModel.Student;
import edu.iba.dataModel.Study;
import edu.iba.dataModel.User;
import edu.iba.db2ConnectionModel.DAOException;
import edu.iba.db2ConnectionModel.GroupDAO;
import edu.iba.db2ConnectionModel.MarkDAO;
import edu.iba.db2ConnectionModel.ProfessorDAO;
import edu.iba.db2ConnectionModel.StudentDAO;
import edu.iba.db2ConnectionModel.StudyDAO;
import edu.iba.db2ConnectionModel.TemplateDAO;
import edu.iba.db2ConnectionModel.UserDAO;

/**
 * Servlet implementation class viewServlet
 */
@WebServlet("/content/view")
public class viewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateDAO DAO;      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mode =request.getParameter("mode");
		String key = request.getParameter("key");
		List<String> list = new ArrayList<String>();
		request.setAttribute("mode", mode);
		request.setAttribute("key", key);
		TemplateDAO subDAO= null;
		if(mode==null||mode.isEmpty()||key==null){
			list.add("Некорректные данные");
			request.setAttribute("listOfData", list);
			request.getRequestDispatcher("Pages/View/StView.jsp").forward(request, response);
		}
		else{
			if("St".equals(mode)){
				try {
					DAO=new StudentDAO();
					Student student = (Student) DAO.getBean(key);
					list.add(student.getFirst_name());
					list.add(student.getSecond_name());
					list.add(student.getAvg_mark().toString());
					subDAO=new GroupDAO();
					list.add(subDAO.getMainValue(student.getGroup_number()));
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if("Us".equals(mode)){
				try {
					DAO=new UserDAO();
					User user = (User) DAO.getBean(key);
					list.add(user.getUser());
					list.add(user.getRole());
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if("Gr".equals(mode)){
				try {
					DAO=new GroupDAO();
					Group group = (Group) DAO.getBean(key);
					list.add(group.getGroup_number());
					list.add(group.getAvg_mark().toString());
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if("Sd".equals(mode)){
				try {
					DAO=new StudyDAO();
					subDAO=new ProfessorDAO();
					Study study = (Study) DAO.getBean(key);
					list.add(study.getName());
					list.add(study.getHours().toString());
					list.add(study.getAvg_mark().toString());
					list.add(subDAO.getMainValue(study.getProfessor_id()));
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if("Pr".equals(mode)){
				try {
					DAO=new ProfessorDAO();
					Professor professor = (Professor) DAO.getBean(key);
					list.add(professor.getFirst_name());
					list.add(professor.getSecond_name());
					list.add(professor.getFather_name());
					list.add(professor.getAvg_mark().toString());
					list.add(professor.getBirth_date().toString());
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if("Mr".equals(mode)){
				try {
					DAO=new MarkDAO();
					subDAO=new StudyDAO();
					Mark mark = (Mark) DAO.getBean(key);
					list.add(mark.getMark());
					list.add(subDAO.getMainValue(mark.getStudy_id()));
					subDAO=new StudentDAO();
					list.add(subDAO.getMainValue(mark.getStudent_id()));
					subDAO=new ProfessorDAO();
					list.add(mark.getDate().toString());
					list.add(subDAO.getMainValue(mark.getProfessor_id()));
					list.add(mark.getComments());
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			request.setAttribute("listOfData", list);
			request.getRequestDispatcher("Pages/View/"+mode+"View.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
