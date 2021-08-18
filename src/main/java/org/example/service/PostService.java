package org.example.service;


import org.example.exception.NotFoundException;
import org.example.model.Post;
import org.example.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Map<Long, Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public long save(Post post) {
        if (post.getId() == 0) {
            post.setId(repository.save(post).get());
        } else {
            return repository.save(post).orElseThrow(NotFoundException::new);
        }
        return post.getId();
    }

    public Post removeById(long id) {
        return repository.removeById(id).orElseThrow(NotFoundException::new);
    }
}
