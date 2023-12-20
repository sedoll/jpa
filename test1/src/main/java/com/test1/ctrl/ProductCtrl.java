package com.test1.ctrl;

import com.test1.dto.ProductDto;
import com.test1.service.ProductService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductCtrl {

    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public String list(Model model) {
        List<ProductDto> dtoList = productService.productList();
        model.addAttribute("dtoList", dtoList);
        return "list";
    }

    @GetMapping("detail")
    public String detail(@RequestParam("no") Integer no, Model model){
        ProductDto dto = productService.getProduct(no);
        model.addAttribute("dto", dto);
        return "detail";
    }


    @GetMapping("insert")
    public String insert() {
        return "insert";
    }

    @PostMapping("insert")
    public String insertPro(ProductDto dto) {
        productService.proInsert(dto);
        return "redirect:/list";
    }

    @GetMapping("update")
    public String update(ProductDto dto, Model model) {
        ProductDto resDto = productService.getProduct(dto.getNo());
        model.addAttribute("dto", resDto);
        return "update";
    }

    @PostMapping("update")
    public String updatePro(ProductDto dto) {
        productService.proUpdate(dto);
        return "redirect:/list";
    }

    @GetMapping("delete")
    public String delete(@RequestParam("no") Integer no) {
        productService.proDelete(no);
        return "redirect:/list";
    }
}
