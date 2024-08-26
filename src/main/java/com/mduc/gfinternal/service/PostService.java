package com.mduc.gfinternal.service;

import com.mduc.gfinternal.model.Post;
import com.mduc.gfinternal.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    public Post createPost(Post post) {
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        newPost.setTitleImage(post.getTitleImage());
        return postRepository.save(newPost);
    }
    public Optional<Post> getPostById(Integer id) {
        return postRepository.findById(id);
    }
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

}
