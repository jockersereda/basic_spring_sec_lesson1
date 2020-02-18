package ua.com.owu.basic_spring_sec_lesson1.services.product;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.com.owu.basic_spring_sec_lesson1.dao.ProductDAO;
import ua.com.owu.basic_spring_sec_lesson1.models.Product;

import java.util.List;

@Service("psi1")
@AllArgsConstructor
public class ProductServiceImp1 implements ProductService{

    private ProductDAO productDAO;


    @Override
    public void save(Product product) {
        productDAO.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findOneById(int id) {
        return productDAO.findById(id).get();
    }
}
