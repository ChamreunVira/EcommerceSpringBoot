package com.kh.vira_dev.ecommerce.repository;

import com.kh.vira_dev.ecommerce.entity.Category;
import com.kh.vira_dev.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
