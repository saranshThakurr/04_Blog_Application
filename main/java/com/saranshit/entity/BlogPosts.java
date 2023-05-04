package com.saranshit.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="blog_tbl")
public class BlogPosts {
     
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer blogId;
	 private String title;
	 private String description;
	 @Lob
	 private String content;
	 @CreationTimestamp
	 private LocalDate createdDate;
	 @UpdateTimestamp
	 private LocalDate updatedDate;
	 
	 @ManyToOne
	 @JoinColumn(name="user_id")
	 private Users user;
	 
	 @OneToMany(mappedBy = "blogPost",cascade = CascadeType.REMOVE)
	 private List<Comments> comments;

}
