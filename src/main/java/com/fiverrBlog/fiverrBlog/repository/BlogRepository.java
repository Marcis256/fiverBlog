package com.fiverrBlog.fiverrBlog.repository;

import com.fiverrBlog.fiverrBlog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository  extends JpaRepository<Blog, Long> {

    @Query("SELECT DISTINCT b.category FROM Blog b")
    List<String> findDistinctCategories();
}
