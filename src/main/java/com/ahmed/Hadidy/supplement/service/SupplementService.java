package com.ahmed.Hadidy.supplement.service;


import com.ahmed.Hadidy.supplement.dto.SupplementResponse;
import com.ahmed.Hadidy.supplement.dto.CreateSupplementRequest;
import com.ahmed.Hadidy.supplement.dto.SupplementRequest;

import java.util.List;

public interface SupplementService {

    SupplementResponse createSupplement(CreateSupplementRequest request , String username);
    List<SupplementResponse> listSupplement(String username);
    SupplementResponse getSupplement(Long id , String username);

    void deleteSupplement(Long id , String username);

    SupplementResponse editSupplement(Long id , SupplementRequest request , String username);
}
