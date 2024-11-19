package domain.project.dto;

import utils.Utils;

public class ProjectAdapter {

	private ProjectAdapter() {
	}

	public static ProjectRequestDTO dotoToRequestDto(ProjectDTO dto) {

		return ProjectRequestDTO.builder()
				.forecastEndProject(Utils.convertDateToLocalDate(dto.getForecastEndProject()))
				.projectCode(dto.getProjectCode())
				.projectDescription(dto.getProjectDescription())
				.projectName(dto.getProjectName())
				.projectStartDate(Utils.convertDateToLocalDate(dto.getProjectStartDate()))
				.projectStatus(dto.getProjectStatus())
				.realEndProject(Utils.convertDateToLocalDate(dto.getRealEndProject()))
				.responsibleManager(dto.getResponsibleManager())
				.risk(dto.getRisk())
				.totalProjectBudgetValue(dto.getTotalProjectBudgetValue())
				.build();
	}
	 
	public static ProjectRequestDTO responseDTOToRequestDto(ProjectResponseDTO dto) {

		return ProjectRequestDTO.builder()
				.forecastEndProject(dto.getForecastEndProject())
				.projectCode(dto.getProjectCode())
				.projectDescription(dto.getProjectDescription())
				.projectName(dto.getProjectName())
				.projectStartDate(dto.getProjectStartDate())
				.projectStatus(dto.getProjectStatus().getStatusCode())
				.realEndProject(dto.getRealEndProject())
				.responsibleManager(dto.getResponsibleManager().getEmployeeCode())
				.risk(dto.getRisk())
				.totalProjectBudgetValue(dto.getTotalProjectBudgetValue())
				.build();
	}

	public static ProjectDTO requestDTOToProjectDTO(ProjectRequestDTO dto) {

		return ProjectDTO.builder()
				.forecastEndProject(Utils.convertLocalDateToDate(dto.getForecastEndProject()))
				.projectCode(dto.getProjectCode())
				.projectDescription(dto.getProjectDescription())
				.projectName(dto.getProjectName())
				.projectStartDate(Utils.convertLocalDateToDate(dto.getProjectStartDate()))
				.projectStatus(dto.getProjectStatus())
				.realEndProject(Utils.convertLocalDateToDate(dto.getRealEndProject()))
				.responsibleManager(dto.getResponsibleManager())
				.risk(dto.getRisk())
				.totalProjectBudgetValue(dto.getTotalProjectBudgetValue())
				.build();
	}
	
}