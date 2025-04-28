package com.fiverrBlog.fiverrBlog.service.Impl;

import com.fiverrBlog.fiverrBlog.model.Blog;
import com.fiverrBlog.fiverrBlog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public Blog addBlog(Blog product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageDate(imageFile.getBytes());
        return blogRepository.save(product);
    }

    public Optional<Blog> updateBlog(Long id, Blog blog, MultipartFile imageFile) {
        return blogRepository.findById(id).map(existingBlog -> {
            existingBlog.setName(blog.getName());
            existingBlog.setDescription(blog.getDescription());
            existingBlog.setHyperlink(blog.getHyperlink());
            existingBlog.setCategory(blog.getCategory());
            existingBlog.setContent(blog.getContent() != null ? blog.getContent() : ""); // Default to empty

            if (imageFile != null) {
                try {
                    existingBlog.setImageName(imageFile.getOriginalFilename());
                    existingBlog.setImageType(imageFile.getContentType());
                    existingBlog.setImageDate(imageFile.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to update blog image", e);
                }
            }

            return blogRepository.save(existingBlog);
        });
    }
    public boolean deleteBlog(Long id) {
        if (blogRepository.existsById(id)) {
            blogRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<String> getDistinctCategories() {
        return blogRepository.findDistinctCategories();
    }
}
