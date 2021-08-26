package com.deletet.deletet3.HomePage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NewsRepository extends JpaRepository<News,Long> {
}
