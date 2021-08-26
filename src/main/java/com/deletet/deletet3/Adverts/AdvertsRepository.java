package com.deletet.deletet3.Adverts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdvertsRepository extends JpaRepository<Adverts,Long> {
}
