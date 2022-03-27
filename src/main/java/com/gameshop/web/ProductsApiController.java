package com.gameshop.web;

import com.gameshop.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductsApiController - 상품관리 컨트롤러
 */

@RequiredArgsConstructor
@RestController
public class ProductsApiController {

    private final ProductsService productsService;

    /**
     * 상품관리 - 상품삭제 (Products객체를 상속받은 Consoles와 Titles ApiController에는 삭제기능 구현X)
     * @param id
     * @return
     */
    @DeleteMapping("/admin/products/del/{id}")
    public Long products_del(@PathVariable Long id) {
        return productsService.products_del(id);
    }
}
