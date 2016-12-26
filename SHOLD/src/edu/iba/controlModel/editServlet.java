package edu.iba.controlModel;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.iba.dataModel.Group;
import edu.iba.dataModel.Mark;
import edu.iba.dataModel.Person;
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
 * Servlet implementation class editServlet
 */
@WebServlet("/content/edit")
public class editServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateDAO DAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		String key = request.getParameter("key");
		Map<String, Object> list = new HashMap<String, Object>();
		request.setAttribute("mode", mode);
		request.setAttribute("key", key);
		TemplateDAO subDAO = null;
		if (mode == null || mode.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/content/list");
		} else {
			if (key != null) {
				if ("St".equals(mode)) {
					DAO = new StudentDAO();
					Student student;
					try {
						student = (Student) DAO.getBean(key);
						list.put("firstname", student.getFirst_name());
						list.put("secondname", student.getSecond_name());
						list.put("avgmark", student.getAvg_mark().toString());
						subDAO = new GroupDAO();
						Map<String, String> grList = subDAO.getList();
						list.put("group", student.getGroup_number());
						request.setAttribute("listOfGroups", grList);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if ("Us".equals(mode)) {
					try {
						DAO = new UserDAO();
						User user = (User) DAO.getBean(key);
						list.put("username", user.getUser());
						list.put("role", user.getRole());
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if ("Gr".equals(mode)) {
					try {
						DAO = new GroupDAO();
						Group group = (Group) DAO.getBean(key);
						list.put("groupnumber", group.getGroup_number());
						list.put("avgmark", group.getAvg_mark().toString());
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if ("Sd".equals(mode)) {
					try {
						DAO = new StudyDAO();
						subDAO = new ProfessorDAO();
						Study study = (Study) DAO.getBean(key);
						list.put("name", study.getName());
						list.put("hours", study.getHours().toString());
						list.put("avgmark", study.getAvg_mark().toString());
						list.put("professor", study.getProfessor_id());
						Map<String, String> prList = subDAO.getList();
						request.setAttribute("listOfProfessors", prList);
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if ("Pr".equals(mode)) {
					try {
						DAO = new ProfessorDAO();
						Professor professor = (Professor) DAO.getBean(key);
						list.put("firstname", professor.getFirst_name());
						list.put("secondname", professor.getSecond_name());
						list.put("fathername", professor.getFather_name());
						list.put("avgmark", professor.getAvg_mark().toString());
						list.put("birthdate", professor.getBirth_date()
								.toString());
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if ("Mr".equals(mode)) {
					try {
						DAO = new MarkDAO();
						subDAO = new StudyDAO();
						Mark mark = (Mark) DAO.getBean(key);
						list.put("mark", mark.getMark());
						list.put("professor", mark.getStudy_id());
						Map<String, String> sdList = subDAO.getList();
						request.setAttribute("listOfStudies", sdList);
						subDAO = new StudentDAO();
						list.put("student", mark.getStudent_id());
						Map<String, String> stList = subDAO.getList();
						request.setAttribute("listOfStudents", stList);
						subDAO = new ProfessorDAO();
						list.put("date", mark.getDate().toString());
						list.put("professor", mark.getProfessor_id());
						Map<String, String> prList = subDAO.getList();
						request.setAttribute("listOfProfessors", prList);
						list.put("comments", mark.getComments());
					} catch (DAOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		request.setAttribute("listOfData", list);
		request.getRequestDispatcher(
				"Pages/Edit/" + request.getParameter("mode") + "Edit.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		String key = request.getParameter("key");
		request.setAttribute("mode", mode);
		request.setAttribute("key", key);
		try {
		Object bean=null;
		if (mode == null || mode.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/content/list");
		} else {
			if ("St".equals(mode)) {
				DAO = new StudentDAO();
				bean = new Student();
				((Student) bean).setFirst_name(request.getParameter("first_name"));
				((Student) bean).setSecond_name(request.getParameter("second_name"));
				((Student) bean).setGroup_number(request.getParameter("group"));
				((Student) bean).setId(key);
			}
			if ("Us".equals(mode)) {
				DAO = new UserDAO();
				bean = new User();
				((User) bean).setUser(request.getParameter("id"));
				((User) bean).setRole(request.getParameter("role"));
			}
			if ("Gr".equals(mode)) {
				DAO = new GroupDAO();
				bean= new Group();
				((Group) bean).setGroup_number(request.getParameter("group_number"));
			}
			if ("Sd".equals(mode)) {
				DAO = new StudyDAO();
				bean = new Study();
				((Study) bean).setName(request.getParameter("name"));
				((Study) bean).setId(key);
				((Study) bean).setHours(request.getParameter("hours"));
				((Study) bean).setProfessor_id(request.getParameter("professor"));
			}
			if ("Pr".equals(mode))
				try {
					{
						DAO = new ProfessorDAO();
						bean = new Professor();
						((Professor) bean).setId(key);
						((Professor) bean).setFirst_name(request.getParameter("first_name"));
						((Professor) bean).setSecond_name(request.getParameter("second_name"));
						((Professor) bean).setFather_name(request.getParameter("father_name"));
						try {
							((Professor) bean).setBirth_date(request.getParameter("birth_date"));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if ("Mr".equals(mode)) {
				DAO = new MarkDAO();
				bean = new Mark();
				((Mark) bean).setId(key);
				((Mark) bean).setStudy_id(request.getParameter("study"));
				((Mark) bean).setStudent_id(request.getParameter("student"));
				try {
					((Mark) bean).setDate(request.getParameter("date"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				((Mark) bean).setProfessor_id(request.getParameter("professor"));
				((Mark) bean).setMark(request.getParameter("mark"));
				((Mark) bean).setComments(request.getParameter("comments"));
			}
			
		
		
			if (key != null) {
					DAO.edit(bean);
			}
			else{
				DAO.create(bean);
			}
			
		}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/content/list");
	}

}
