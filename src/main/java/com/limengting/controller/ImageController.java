package com.limengting.controller;

import com.alibaba.fastjson.JSON;
import com.limengting.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class ImageController {
    @Autowired
    private IImageService imageService;

    private final static Logger logger = Logger.getLogger(ImageController.class.getName());

    /**
     * 【校园】按钮
     * @param model
     * @return
     */
    @RequestMapping("/listImage.do")
    public String ListImage(Model model) {
        logger.info("model:" + JSON.toJSON(model) + "; 调用/listImage.do");
        List<String> imageList = imageService.listImage();
        model.addAttribute("imageList", imageList);
        return "image";
    }
}
