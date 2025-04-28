package com.fiverrBlog.fiverrBlog.controlle;

import com.fiverrBlog.fiverrBlog.model.Blog;
import com.fiverrBlog.fiverrBlog.model.Research;
import com.fiverrBlog.fiverrBlog.service.Impl.BlogServiceImpl;
import com.fiverrBlog.fiverrBlog.service.Impl.ResearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/home")
//@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React
public class HomeController {

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private ResearchServiceImpl researchService;

    @GetMapping("/blog")
    public ResponseEntity<List<Blog>> getAllBlog() {
        List<Blog> products = blogService.getAllBlogs();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/research")
    public ResponseEntity<List<Research>> getAllResearch() {
        List<Research> researches = researchService.getAllResearchs();
        return ResponseEntity.ok(researches);
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

}
