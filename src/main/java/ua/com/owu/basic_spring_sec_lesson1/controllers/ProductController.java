package ua.com.owu.basic_spring_sec_lesson1.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.basic_spring_sec_lesson1.dao.ProductDAO;
import ua.com.owu.basic_spring_sec_lesson1.models.Product;
import ua.com.owu.basic_spring_sec_lesson1.services.product.ProductService;
import ua.com.owu.basic_spring_sec_lesson1.services.product.ProductServiceImp1;

import java.util.List;

@RestController
@RequestMapping("/product") // - відхватує по посиланню
public class ProductController {

    // @Qualifier("psi1") - не працює з анотацією @AllArgsConstructor, і його можна встановити тільки в конструктор

    private ProductService productService;

    public ProductController(@Qualifier("psi1") ProductService productService) {   // @Qualifier("psi1") - в конструкторі
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public Product findOne(@PathVariable int id){
        return productService.findOneById(id);
    }

    @PostMapping("/save")
    public List<Product> save(@RequestBody Product product){
       return productService.findAll();
    }

    @GetMapping("/product")
    public List<Product> findAll(){
       return productService.findAll();
    }
}
