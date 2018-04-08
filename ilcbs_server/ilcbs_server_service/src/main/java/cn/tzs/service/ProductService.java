package cn.tzs.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.tzs.domain.PackingList;
import cn.tzs.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface ProductService extends BaseService<Product> {

}
