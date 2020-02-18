package ua.com.owu.basic_spring_sec_lesson1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.basic_spring_sec_lesson1.models.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{
}
