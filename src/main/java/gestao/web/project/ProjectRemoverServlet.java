package gestao.web.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.employee.dto.EmployeeResponseDTO;
import domain.employee.service.EmployeeService;
import domain.project.dto.ProjectAdapter;
import domain.project.dto.ProjectResponseDTO;
import domain.project.service.ProjectService;
import domain.projectstatus.dto.ProjectStatusResponseDTO;
import domain.projectstatus.service.ProjectStatusService;

@WebServlet("/projeto/remover")
public class ProjectRemoverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DESTINY = "/projeto/confirmarExclusao.jsp";
	private static final String ERRO = "/publico/erro.jsp";

	public ProjectRemoverServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProjectService projectService = new ProjectService();
		ProjectStatusService projectStatusService = new ProjectStatusService();
		EmployeeService employeeService = new EmployeeService();
		
		try {
		 
			ProjectResponseDTO dto = projectService.getProjectById(request.getParameter("cod"));
			  
			ProjectStatusResponseDTO status = projectStatusService.getProjectStatusById(dto.getProjectStatus().getStatusCode().toString());
			
			EmployeeResponseDTO employee = employeeService.getEmployeeById(dto.getResponsibleManager().getEmployeeCode().toString());
			
			request.setAttribute("status", status.getStatusDescription());
			
			request.setAttribute("employee", employee.getEmployeeName());
			 
			request.setAttribute("item", ProjectAdapter.requestDTOToProjectDTO(ProjectAdapter.responseDTOToRequestDto(dto)));
			 
			request.getRequestDispatcher(DESTINY).forward(request, response);
		  
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());			
			request.getRequestDispatcher(ERRO).forward(request, response);
		}

	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}