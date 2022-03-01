package com.flexibilitysrl.models;

import com.flexibilitysrl.entity.SalesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestsSells {
    private SalesEntity salesEntity;
    private Long idSeller;
    private Long idClient;
    private List<String> idProductsList;

}
