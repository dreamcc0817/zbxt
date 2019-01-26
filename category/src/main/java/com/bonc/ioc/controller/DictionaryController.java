package com.bonc.ioc.controller;

import com.bonc.ioc.service.DictionaryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class DictionaryController {
    @Resource
    private DictionaryService dictionaryService;
    @RequestMapping("/getDictByType")
    public String getDict(String typeCode){
        try {
            List<Map<String,Object>> list=this.dictionaryService.getDicByType(typeCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

}
