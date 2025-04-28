package com.fiverrBlog.fiverrBlog.repository;

import com.fiverrBlog.fiverrBlog.model.Research;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResearchRepository  extends JpaRepository<Research, Long> {

    @Query("SELECT DISTINCT r.category FROM Research r")
    List<String> findDistinctCategories();
}
