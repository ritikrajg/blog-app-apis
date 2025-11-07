package com.codewithritik.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codewithritik.blog.entities.*;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

    
}
