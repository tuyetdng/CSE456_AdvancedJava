package org.tuyetdang.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.tuyetdang.Entity.Product;
import org.tuyetdang.Repository.ProductRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;

    public void addProduct(Product product) {
        productRepository.saveProduct(product);
    }
}
