package com.findjb.findjob.Service.JPA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.findjb.findjob.JWT.JWTServices.UserDetailsImplement;
import com.findjb.findjob.Model.Enterprise;
import com.findjb.findjob.Model.Post;
import com.findjb.findjob.Repositories.EnterpriseRepository;
import com.findjb.findjob.Repositories.PostRepository;
import com.findjb.findjob.Request.PostRequest;
import com.findjb.findjob.Service.PostServiceInterface;

@Service
public class PostService implements PostServiceInterface {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Override
    public void createNewPost(PostRequest postRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();
        Enterprise enterprise = enterpriseRepository.findById(userDetails.getId()).get();
        Post newPost = Post.builder().name(postRequest.getName())
                .salary(postRequest.getSalary())
                .quantity(postRequest.getQuantity())
                .description(postRequest.getDescription())
                .requirement(postRequest.getRequirement())
                .benefit(postRequest.getBenefit())
                .enterprise(enterprise)
                .build();
        postRepository.save(newPost);
    }

    @Override
    public List<Post> getAllPost() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();
        Enterprise enterprise = enterpriseRepository.findById(userDetails.getId()).get();
        return postRepository.findByEnterpriseId(enterprise.getId());
    }

    @Override
    public Post getPostDetail(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public void updatePost(Long id, PostRequest postRequest) {
        Post post = postRepository.findById(id).get();
        post.setName(postRequest.getName());
        post.setSalary(postRequest.getSalary());
        post.setQuantity(postRequest.getQuantity());
        post.setDescription(postRequest.getDescription());
        post.setRequirement(postRequest.getRequirement());
        post.setBenefit(postRequest.getBenefit());
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
