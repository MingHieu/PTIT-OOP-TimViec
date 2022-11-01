package com.findjb.findjob.Service;


import com.findjb.findjob.Request.PostRequest;
import com.findjb.findjob.Responses.PaginateResponse;
import com.findjb.findjob.Responses.PostDetailResponse;

public interface PostServiceInterface {
    void createNewPost(PostRequest postRequest);

    PaginateResponse getAllPostByEnterprise(String enterprise,Integer pageNo,Integer pageSize,String name);

    PostDetailResponse getPostDetail(Long id);

    void updatePost(Long id, PostRequest postRequest);

    void deletePost(Long id);
}
