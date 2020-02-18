package ua.com.owu.basic_spring_sec_lesson1.services.product;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.com.owu.basic_spring_sec_lesson1.dao.ProductDAO;
import ua.com.owu.basic_spring_sec_lesson1.models.Product;

import java.util.List;

@Service("psi2")
@AllArgsConstructor
public class ProductServiceImpl2 implements ProductService{

    private ProductDAO productDAO;


    @Override
    public void save(Product product) throws Exception {
        if (product != null) {
            productDAO.save(product);
        }else {
            throw new Exception();
        }
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findOneById(int id) {
        if (id>0) {
            return productDAO.findById(id).get();
        }
        return null;
    }
}
