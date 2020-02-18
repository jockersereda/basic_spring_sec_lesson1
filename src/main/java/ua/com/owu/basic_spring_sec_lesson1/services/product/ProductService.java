package ua.com.owu.basic_spring_sec_lesson1.services.product;

import org.springframework.stereotype.Service;
import ua.com.owu.basic_spring_sec_lesson1.models.Product;

import java.util.List;

@Service
public interface ProductService {
    void save(Product product) throws Exception;

    List<Product> findAll();

    Product findOneById(int id);

}
