package domain.project.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import domain.employee.dto.EmployeeResponseDTO;
import domain.projectstatus.dto.ProjectStatusResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer projectCode;

	private String projectName;

	private LocalDate projectStartDate;

	private EmployeeResponseDTO responsibleManager;

	private LocalDate forecastEndProject;

	private LocalDate realEndProject;

	private BigDecimal totalProjectBudgetValue;

	private String projectDescription;

	private String risk;

	private ProjectStatusResponseDTO projectStatus;

}