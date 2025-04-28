package com.fiverrBlog.fiverrBlog.controlle;

import com.fiverrBlog.fiverrBlog.model.Blog;
import com.fiverrBlog.fiverrBlog.model.Research;
import com.fiverrBlog.fiverrBlog.security.jwt.JwtUtils;
import com.fiverrBlog.fiverrBlog.service.Impl.BlogServiceImpl;
import com.fiverrBlog.fiverrBlog.service.Impl.ResearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/admin")
//@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React
public class AdminController {

    @Autowired
    private BlogServiceImpl blogService;
    @Autowired
    private ResearchServiceImpl researchService;

    @GetMapping("/blog")
    public ResponseEntity<List<Blog>> getAllBlog() {
        List<Blog> products = blogService.getAllBlogs();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/blog/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<Blog> blog = blogService.getBlogById(id);

        if (blog.isPresent()) {
            if (blog.get().getImageDate() != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.valueOf(blog.get().getImageType()));
                return new ResponseEntity<>(blog.get().getImageDate(), headers, HttpStatus.OK);
            } else {
                System.out.println("Image data is null for blog ID: " + id);
            }
        } else {
            System.out.println("No blog found with ID: " + id);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/blog")
    public ResponseEntity<?> addBlog(@RequestPart Blog blog,
                                        @RequestPart MultipartFile imageFile) {
        try {
            Blog createdBlog = blogService.addBlog(blog, imageFile);
            return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/blog/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id,
                                            @RequestPart("blog") Blog blog,
                                            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
    Optional<Blog> updatedBlog = blogService.updateBlog(id, blog, imageFile);
        return updatedBlog.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/research/{id}")
    public ResponseEntity<Research> updateResearch(@PathVariable Long id,
                                                   @RequestPart("research") Research research,
                                                   @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        Optional<Research> updatedResearch = researchService.updateResearch(id, research, imageFile);
        return updatedResearch.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/blog/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        boolean isDeleted = blogService.deleteBlog(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/research")
    public ResponseEntity<List<Research>> getAllResearchs() {
        List<Research> products = researchService.getAllResearchs();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/research")
    public ResponseEntity<?> addResearch(@RequestPart Research research,
                                                @RequestPart MultipartFile imageFile) {
        try {
            Research createdResearch = researchService.addResearch(research, imageFile);
            return new ResponseEntity<>(createdResearch, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/research/image/{id}")
    public ResponseEntity<byte[]> getImageResearch(@PathVariable Long id) {
        Optional<Research> research = researchService.getResearchById(id);

        if (research.isPresent()) {
            if (research.get().getImageDate() != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.valueOf(research.get().getImageType()));
                return new ResponseEntity<>(research.get().getImageDate(), headers, HttpStatus.OK);
            } else {
                System.out.println("Image data is null for research ID: " + id);
            }
        } else {
            System.out.println("No research found with ID: " + id);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/research/{id}")
    public ResponseEntity<Void> deleteResearch(@PathVariable Long id) {
        boolean isDeleted = researchService.deleteResearch(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/blog/categories")
    public ResponseEntity<List<String>> getCategories() {
        List<String> categories = blogService.getDistinctCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/research/categories")
    public ResponseEntity<List<String>> getResearchCategories() {
        List<String> categories = researchService.getDistinctCategories();
        return ResponseEntity.ok(categories);
    }
}
