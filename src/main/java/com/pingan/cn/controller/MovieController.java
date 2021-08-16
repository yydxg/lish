package com.pingan.cn.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Movie;
import com.pingan.cn.service.MovieService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "电影")
@RestController
@RequestMapping(value = "/api/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("save")
    public ResponseUtil save(@RequestBody Movie movie){
        return movieService.save(movie);
    }

    @GetMapping("findAll")
    public ResponseUtil getAll(){
        return movieService.getAll();
    }

    @GetMapping("find/{name}/{type}")
    public ResponseUtil findByCondition(@PathVariable  String name,@PathVariable String type){
        return movieService.findByCondition(name,type);
    }
    @GetMapping("find/{idx}")
    public ResponseUtil findByIndex(@PathVariable Integer idx){
        return movieService.findByIndex(idx);
    }

    @GetMapping("delete/{idx}")
    public ResponseUtil delete(@PathVariable Integer idx){
        return movieService.delete(idx);
    }

    @PostMapping("update/{idx}")
    public ResponseUtil update(@PathVariable Integer idx,@RequestBody Movie movie){
        return movieService.update(idx,movie);
    }

}
