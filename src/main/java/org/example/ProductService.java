package org.example;

public class ProductService implements ProductServiceInterface {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String findProductById(String id) {
        return productRepository.findProductById(id);
    }
}
