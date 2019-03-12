package com.limengting.serviceimpl;

import com.limengting.mapper.ImageMapper;
import com.limengting.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements IImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public List<String> listImage() {
        return imageMapper.listImage();
    }
}
