package com.zy.wx.controller;

import com.zy.wx.dto.InfoDTO;
import com.zy.wx.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: ZYi
 * @Date: 2021/3/22 8:54
 * @Version 1.0
 **/
@Controller
public class GenerateController {

    private final GenerateService generateService;

    @Autowired
    public GenerateController(GenerateService generateService) {
        this.generateService = generateService;
    }

    @GetMapping("/")
    public String index(ModelMap map){
        map.put("InfoDTO", new InfoDTO());
        return "generate";
    }
    @GetMapping(value="/upload")
    public String upload(){
        return "upload";
    }

    @PostMapping(value="/add")
    public void add(HttpServletResponse resp, @ModelAttribute InfoDTO info) throws IOException {
        generateService.add(info,resp);
    }

    @RequestMapping(value="/show")
    public String show(@ModelAttribute InfoDTO info, Model model){
        model.addAttribute("info", info);
        return "show";
    }

    @PostMapping(value="/gen",consumes = "multipart/form-data")
    public void gen(@RequestParam("img") MultipartFile img,HttpServletResponse resp) throws IOException {
        generateService.gen(img,resp);
    }
}
