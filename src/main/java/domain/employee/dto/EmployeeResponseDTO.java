package domain.employee.dto;

import java.io.Serializable;
import java.time.LocalDate;

import domain.position.dto.PositionResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer employeeCode;

	private String cpf;

	private PositionResponseDTO position;

	private String employeeName;

	private String employeeStatus;

	private LocalDate startDate;

}