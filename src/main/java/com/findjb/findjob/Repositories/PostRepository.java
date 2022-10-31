package com.findjb.findjob.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findjb.findjob.Model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByEnterpriseId(Long id);
    List<Post> findByIdNot(Long id);
}
