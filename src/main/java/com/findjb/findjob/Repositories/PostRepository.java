package com.findjb.findjob.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findjb.findjob.Model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
