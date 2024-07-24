package com.example.personalfinance.service;

public interface Operations<T>
{
    boolean delete(long id);
    Iterable<T> findAll();
}
