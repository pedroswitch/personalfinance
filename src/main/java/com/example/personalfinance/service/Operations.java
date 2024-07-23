package com.example.personalfinance.service;

import java.util.List;
import java.util.Optional;

public interface Operations<T>
{
    Optional<T> add(T item);
    boolean delete(String id);
    List<T> findAll();
}
