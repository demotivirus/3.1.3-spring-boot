package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
