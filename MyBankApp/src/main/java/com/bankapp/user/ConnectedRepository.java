package com.bankapp.user;

import org.springframework.data.repository.CrudRepository;
public interface ConnectedRepository extends CrudRepository<Connected, Integer> {
    public Long countById(Integer id);
}