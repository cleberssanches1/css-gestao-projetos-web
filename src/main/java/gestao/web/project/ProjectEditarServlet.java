package gestao.web.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/projeto/editar")
public class ProjectEditarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DESTINY = "/projeto/formEditar.jsp";
	private static final String ERRO = "/publico/erro.jsp";

	public ProjectEditarServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProjectService projectService = new ProjectService();
		ProjectStatusService projectStatusService = new ProjectStatusService();
		
		try {
		 
			ProjectResponseDTO dto = projectService.getProjectById(request.getParameter("cod"));
			 
			request.setAttribute("item", ProjectAdapter.requestDTOToProjectDTO(ProjectAdapter.responseDTOToRequestDto(dto)));
			
            this.setRiskItens(request);
			 
			List<ProjectStatusResponseDTO> responseStatusList = projectStatusService.getAllProjectStatuss(0, 100, "DESC").getContent();
			request.setAttribute("statusList", responseStatusList);
   
			EmployeeService employeeService = new EmployeeService();
			List<EmployeeResponseDTO> employeeList = employeeService.getAllEmployees(0, 100, "DESC").getContent();
			request.setAttribute("employeeList", employeeList);
			 
			request.getRequestDispatcher(DESTINY).forward(request, response);
		  
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());			
			request.getRequestDispatcher(ERRO).forward(request, response);
		}

	}

	private void setRiskItens(HttpServletRequest request) {
		List<String> riskItens = new ArrayList<String>();
		
		riskItens.add("BAIXO RISCO");			
		riskItens.add("MÉDIO RISCO");			
		riskItens.add("ALTO RISCO");
		request.setAttribute("riskItens", riskItens);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}