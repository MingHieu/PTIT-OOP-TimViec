package com.findjb.findjob.Service.JPA;

import java.util.ArrayList;
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
import com.findjb.findjob.Responses.PostResponse;
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
                .experience(postRequest.getExperience())
                .position(postRequest.getPosition())
                .benefit(postRequest.getBenefit())
                .type(postRequest.getType())
                .expired_at(postRequest.getExpired())
                .enterprise(enterprise)
                .build();
        postRepository.save(newPost);
    }

    @Override
    public List<PostResponse> getAllPostByEnterprise(Boolean isEnterprise) {
        List<Post> listPosts = new ArrayList<>();
        if (isEnterprise == true) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();
            Enterprise enterprise = enterpriseRepository.findById(userDetails.getId()).get();
            listPosts = postRepository.findByEnterpriseId(enterprise.getId());
        } else {
            listPosts = postRepository.findAll();
        }
        List<PostResponse> listResponse = new ArrayList<>();
        for (Post p : listPosts) {
            Enterprise e = p.getEnterprise();
            PostResponse pr = PostResponse.builder()
                    .name(p.getName())
                    .companyName(e.getName())
                    .comapyAvatar(e.getAvatar())
                    .salary(p.getSalary())
                    .type(p.getType())
                    .quantity(p.getQuantity())
                    .experience(p.getExperience())
                    .position(p.getPosition())
                    .address(e.getAddress())
                    .description(p.getDescription())
                    .requirement(p.getRequirement())
                    .benefit(p.getBenefit())
                    .createAt(p.getCreated_at())
                    .expired(p.getExpired_at())
                    .address(e.getAddress())
                    .build();
            listResponse.add(pr);
        }
        return listResponse;
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
