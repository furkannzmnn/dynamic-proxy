package org.example;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepositoryImpl();

        final ProductServiceInterface o = (ProductServiceInterface) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{ProductServiceInterface.class},
                new ProxyHandler(new ProductService(productRepository))
        );

        System.out.println(o.findProductById("1"));
    }
}