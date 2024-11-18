package gestao.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.employee.dto.EmployeeResponseDTO;
import domain.employee.service.EmployeeService;
import domain.position.dto.PositionResponseDTO;
import domain.position.service.PositionService;
import domain.project.dto.ProjectResponseDTO;
import domain.project.service.ProjectService;
import domain.projectstatus.dto.ProjectStatusResponseDTO;
import domain.projectstatus.service.ProjectStatusService;
import utils.CustomPage;

@WebServlet("/Instanciacao")
public class Instantiation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public Instantiation() {
        super(); 
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		ProjectService projectService = new ProjectService();		
		CustomPage <ProjectResponseDTO> pagina = projectService.getAllProjects(0, 10, "ASC");
		
		EmployeeService employeeService = new EmployeeService();		
		CustomPage<EmployeeResponseDTO> pagina2 = employeeService.getAllEmployees(0, 10, "ASC");
				
		PositionService positionService = new PositionService();
		CustomPage<PositionResponseDTO> pagina3 = positionService.getAllPositions(0, 10, "ASC");
				
		ProjectStatusService projectStatusService = new ProjectStatusService();
		CustomPage<ProjectStatusResponseDTO> pagina4 = projectStatusService.getAllProjectStatuss(0, 10, "ASC");
		
		response.getWriter().append("Objetos retornados :" + pagina4).append(request.getContextPath());
	}

}