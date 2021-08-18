package org.example.repository;

import org.example.model.Post;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    private final Map<Long, Post> posts = new ConcurrentHashMap<>();

    private AtomicLong counter = new AtomicLong(0);

    public Map<Long, Post> all() {
        return posts.entrySet().stream()
                .filter(x -> !x.getValue().isRemoved())
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    }

    public Optional<Post> getById(long id) {
        if (posts.get(id) == null) {
            return Optional.empty();
        }
        return !posts.get(id).isRemoved() ? Optional.ofNullable(posts.get(id)) : Optional.empty();//NotFoundException
    }

    public Optional<Long> save(Post post) {
        long id = post.getId();
        if (id == 0) {
            id = counter.incrementAndGet();
            posts.put(id, post);
        } else {
            if (posts.get(id) != null && !posts.get(id).isRemoved()) {
                posts.put(post.getId(), post);
            } else {
                return Optional.empty();//NotFoundException
            }
        }
        return Optional.of(id);
    }

    public Optional<Post> removeById(long id) {
        if (posts.get(id) != null) {
            if (posts.get(id).isRemoved()) {
                return Optional.empty();
            }
            posts.get(id).setRemoved(LocalDateTime.now());
        }
        return Optional.ofNullable(posts.get(id));
    }
}