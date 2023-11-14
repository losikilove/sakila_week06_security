package com.group01.server_a.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group01.server_a.models.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

}
