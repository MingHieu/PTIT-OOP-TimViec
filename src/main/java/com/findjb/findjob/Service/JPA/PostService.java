package com.findjb.findjob.Service.JPA;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.findjb.findjob.JWT.JWTServices.UserDetailsImplement;
import com.findjb.findjob.Model.Enterprise;
import com.findjb.findjob.Model.Post;
import com.findjb.findjob.Repositories.EnterpriseRepository;
import com.findjb.findjob.Repositories.PostRepository;
import com.findjb.findjob.Request.PostRequest;
import com.findjb.findjob.Responses.PaginateResponse;
import com.findjb.findjob.Responses.PostDetailResponse;
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
                .address(postRequest.getAddress())
                .quantity(postRequest.getQuantity())
                .description(postRequest.getDescription())
                .requirement(postRequest.getRequirement())
                .experience(postRequest.getExperience())
                .gender(postRequest.getGender())
                .position(postRequest.getPosition())
                .benefit(postRequest.getBenefit())
                .type(postRequest.getType())
                .expired(postRequest.getExpired())
                .enterprise(enterprise)
                .build();
        postRepository.save(newPost);
    }

    @Override
    public PaginateResponse getAllPostByEnterprise(String isEnterprise, Integer pageNo, Integer pageSize, String name) {
        Integer size = pageSize != null ? pageSize : 15;
        Pageable paginate = PageRequest.of(pageNo, size);
        Page<Post> listPosts;
        if (isEnterprise == "true") {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();
            Enterprise enterprise = enterpriseRepository.findById(userDetails.getId()).get();
            listPosts = postRepository.findByEnterpriseId(enterprise.getId(), paginate);
        } else if (name != null) {
            listPosts = postRepository.findByNameContaining(name, paginate);
        } else {
            listPosts = postRepository.findAll(paginate);
        }
        List<PostResponse> listResponse = new ArrayList<>();
        for (Post p : listPosts) {
            Enterprise e = p.getEnterprise();
            PostResponse pr = PostResponse.builder()
                    .id(p.getId())
                    .name(p.getName())
                    .companyName(e.getName())
                    .companyAvatar(e.getAvatar())
                    .salary(p.getSalary())
                    .type(p.getType())
                    .quantity(p.getQuantity())
                    .experience(p.getExperience())
                    .position(p.getPosition())
                    .address(p.getAddress())
                    .description(p.getDescription())
                    .requirement(p.getRequirement())
                    .gender(p.getGender())
                    .benefit(p.getBenefit())
                    .created_at(p.getCreated_at())
                    .expired(p.getExpired())
                    .build();
            listResponse.add(pr);
        }
        return new PaginateResponse(true, "Lấy danh sách thành công", listResponse, listPosts.getNumber(),
                listPosts.getSize(), listPosts.getTotalElements());
    }

    @Override
    public PostDetailResponse getPostDetail(Long id) {
        Post p = postRepository.findById(id).get();
        Enterprise e = p.getEnterprise();
        List<Post> related = postRepository.findByIdNot(id);
        return new PostDetailResponse(p, e, related);
    }

    @Override
    public void updatePost(Long id, PostRequest postRequest) {
        Post post = postRepository.findById(id).get();
        post.setName(postRequest.getName());
        post.setAddress(postRequest.getAddress());
        post.setSalary(postRequest.getSalary());
        post.setPosition(postRequest.getPosition());
        post.setQuantity(postRequest.getQuantity());
        post.setGender(postRequest.getGender());
        post.setExperience(postRequest.getExperience());
        post.setDescription(postRequest.getDescription());
        post.setRequirement(postRequest.getRequirement());
        post.setBenefit(postRequest.getBenefit());
        post.setType(postRequest.getType());
        post.setExpired(postRequest.getExpired());
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
