package com.tekkenhub.backend.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<BlogPost, Long> {

}
