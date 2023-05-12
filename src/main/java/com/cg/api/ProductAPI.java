package com.cg.api;


import com.cg.model.dto.ProductCreateReqDTO;
import com.cg.model.dto.ProductCreateResDTO;
import com.cg.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> create(ProductCreateReqDTO productCreateReqDTO) {

        ProductCreateResDTO productCreateResDTO = productService.create(productCreateReqDTO);

        return new ResponseEntity<>(productCreateResDTO, HttpStatus.OK);
    }
}
