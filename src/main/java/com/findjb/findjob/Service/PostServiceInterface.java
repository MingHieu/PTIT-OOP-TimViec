package com.findjb.findjob.Service;

import java.util.List;

import com.findjb.findjob.Model.Post;
import com.findjb.findjob.Request.PostRequest;

public interface PostServiceInterface {
    void createNewPost(PostRequest postRequest);

    List<Post> getAllPost();

    Post getPostDetail(Long id);

    void updatePost(Long id, PostRequest postRequest);

    void deletePost(Long id);
}
