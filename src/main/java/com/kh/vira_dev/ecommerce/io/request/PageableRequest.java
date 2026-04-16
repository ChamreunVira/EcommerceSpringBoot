package com.kh.vira_dev.ecommerce.io.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageableRequest {

    private int number = 0;
    private int size = 10;
    private String sortBy = "id";
    private boolean ascending = true;

    public Pageable toPageable() {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        return PageRequest.of(number , size , sort);
    }

}
