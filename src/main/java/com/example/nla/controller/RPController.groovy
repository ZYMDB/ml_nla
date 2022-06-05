package com.example.nla.controller

import com.example.nla.service.ColourService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RPController {
    @Autowired
    ColourService colourService;
    @RequestMapping("/rpc/{content}")
    def rpc(@PathVariable("content") String content){
        return colourService.getRS(content).replace("。","。<br/>");
    }
}
