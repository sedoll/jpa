package com.datajpa.controller;

import com.datajpa.dto.ProductDto;
import com.datajpa.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public String getProductList(Long number, Model model){
        List<ProductDto> dtoList = productService.productList();
        model.addAttribute("dtoList", dtoList);
        return "product/list";
    }

    @GetMapping("/getPro")
    public String getProduct(Long number, Model model){
        ProductDto productDto = productService.getProduct(number);
        model.addAttribute("dto", productDto);
        return "product/read";
    }

    @GetMapping("/create")
    public String createProduct(Model model) {
        model.addAttribute("productDto", new ProductDto());
        return "product/new";
    }

    @PostMapping("/create")
    public String createProductPro(ProductDto productDto) {
        productService.saveProduct(productDto);
        return "redirect:/product/list";
    }

    @GetMapping("/modify")
    public String changeProduct(Long number, Model model) {
        ProductDto productDto = productService.getProduct(number);
        model.addAttribute("dto", productDto);
        return "product/modify";
    }

    @PostMapping("/modify")
    public String changeProductPro(ProductDto productDto) throws Exception {
        productService.changeProduct(productDto);
        return "redirect:/product/list";
    }

    @GetMapping("/remove")
    public String deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);
        return "redirect:/product/list";
    }
}
