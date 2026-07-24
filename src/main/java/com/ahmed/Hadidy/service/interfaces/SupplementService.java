package com.ahmed.Hadidy.service.interfaces;


import com.ahmed.Hadidy.dto.response.SupplementResponse;
import com.ahmed.Hadidy.dto.request.CreateSupplementRequest;
import com.ahmed.Hadidy.dto.request.SupplementRequest;

import java.util.List;

public interface SupplementService {

    SupplementResponse createSupplement(CreateSupplementRequest request , String username);
    List<SupplementResponse> listSupplement(String username);
    SupplementResponse getSupplement(Long id , String username);

    void deleteSupplement(Long id , String username);

    SupplementResponse editSupplement(Long id , SupplementRequest request , String username);
}
