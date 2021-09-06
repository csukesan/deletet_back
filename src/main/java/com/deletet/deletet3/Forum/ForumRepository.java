package com.deletet.deletet3.Forum;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumRepository extends JpaRepository<ForumHome,Long> {

    List<ForumHome> findAllByOrderByIdDesc();
}
