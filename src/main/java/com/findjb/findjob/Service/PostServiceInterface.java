package com.findjb.findjob.Service;

import java.util.List;
import com.findjb.findjob.Request.PostRequest;
import com.findjb.findjob.Responses.PostResponse;

public interface PostServiceInterface {
    void createNewPost(PostRequest postRequest);

    List<PostResponse> getAllPostByEnterprise(String enterprise);

    PostResponse getPostDetail(Long id);

    void updatePost(Long id, PostRequest postRequest);

    void deletePost(Long id);
}
