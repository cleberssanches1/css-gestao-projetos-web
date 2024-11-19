package domain.project.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer projectCode;

	private String projectName;

	private Date projectStartDate;

	private Integer responsibleManager;

	private Date forecastEndProject;

	private Date realEndProject;

	private BigDecimal totalProjectBudgetValue;

	private String projectDescription;

	private String risk;

	private Integer projectStatus;

}
