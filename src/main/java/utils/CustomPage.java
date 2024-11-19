package utils;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomPage<T> {
	
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
 
}