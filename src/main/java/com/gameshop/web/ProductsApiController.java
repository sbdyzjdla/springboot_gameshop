package com.gameshop.web;

import com.gameshop.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductsApiController {

    private final ProductsService productsService;

    @DeleteMapping("/admin/products/del/{id}")
    public Long products_del(@PathVariable Long id) {
        return productsService.products_del(id);
    }
}
