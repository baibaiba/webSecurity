package com.example.demo.service.impl;

import com.example.demo.service.IService;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements IService {
    @Override
    public String index() {
        return "index";
    }
}
