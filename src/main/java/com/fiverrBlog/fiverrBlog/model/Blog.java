package com.fiverrBlog.fiverrBlog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.Temporal;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Blog {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String category;
    private String hyperlink;
    @Lob // Large object to store long text
    private String content;

    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageDate;

    @CreationTimestamp // Automatically sets the timestamp on creation
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMMM d, yyyy")
    private LocalDateTime createdDate;
}
