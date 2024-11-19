package domain.position.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer positionCode;

	private String positionName;

	private String positionDescription;

	private String positionStatus;

}
