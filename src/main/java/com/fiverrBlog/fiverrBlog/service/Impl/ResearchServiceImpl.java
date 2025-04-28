package com.fiverrBlog.fiverrBlog.service.Impl;

import com.fiverrBlog.fiverrBlog.model.Blog;
import com.fiverrBlog.fiverrBlog.model.Research;
import com.fiverrBlog.fiverrBlog.repository.BlogRepository;
import com.fiverrBlog.fiverrBlog.repository.ResearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ResearchServiceImpl {

    @Autowired
    private ResearchRepository researchRepository;

    public List<Research> getAllResearchs() {
        return researchRepository.findAll();
    }

    public Optional<Research> getResearchById(Long id) {
        return researchRepository.findById(id);
    }

    public Research addResearch(Research product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageDate(imageFile.getBytes());
        return researchRepository.save(product);
    }

    public Optional<Research> updateResearch(Long id, Research research, MultipartFile imageFile) {
        return researchRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(research.getName());
            existingProduct.setDescription(research.getDescription());
            existingProduct.setHyperlink(research.getHyperlink());
            existingProduct.setCategory(research.getCategory());
            existingProduct.setContent(research.getContent());

            if (imageFile != null) {
                try {
                    existingProduct.setImageName(imageFile.getOriginalFilename());
                    existingProduct.setImageType(imageFile.getContentType());
                    existingProduct.setImageDate(imageFile.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to update blog image", e);
                }
            }

            return researchRepository.save(existingProduct);
        });
    }
    public boolean deleteResearch(Long id) {
        if (researchRepository.existsById(id)) {
            researchRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<String> getDistinctCategories() {
        return researchRepository.findDistinctCategories();
    }
}
