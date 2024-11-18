package domain.projectstatus.dto;

 
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStatusRequestDTO implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private Integer statusCode;
 
	private String statusDescription;
		 
}