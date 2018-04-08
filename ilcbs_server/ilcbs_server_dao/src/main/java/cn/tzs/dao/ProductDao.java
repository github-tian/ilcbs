package cn.tzs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cn.tzs.domain.Product;


public interface ProductDao  extends JpaRepository<Product, String>,JpaSpecificationExecutor<Product> {

}
