package com.pingan.cn.service;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.entity.Movie;
import com.pingan.cn.entity.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("movieService")
public class MovieService {
    @Autowired
    private RedisTemplate redisTemplate;

    public ResponseUtil save(Movie movie){
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            final Long result = listOperations.rightPushAll("movie", movie);
            return ResponseUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil getAll(){
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            List movies = listOperations.range("movie", 0, -1);
            return ResponseUtil.success(movies);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findByCondition(String name, String type){
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            List<Movie> movies = listOperations.range("movie", 0, -1);

            List<Movie> movieList = new ArrayList<>();
            if(name.equals("ALL")&&type.equals("ALL")){
                movieList = movies;
            }else if(name.equals("ALL")&& !type.equals("ALL")){
                MovieType movieType = MovieType.valueOf(type);
                movieList = movies.stream()
                        .filter((Movie s) -> s.getType() == movieType)
                        .collect(Collectors.toList());
            }else if(!name.equals("ALL")&& type.equals("ALL")){
                movieList = movies.stream()
                        .filter((Movie s) -> name.equals(s.getName()))
                        .collect(Collectors.toList());
            }else if(!name.equals("ALL")&& !type.equals("ALL")){
                MovieType movieType = MovieType.valueOf(type);
                movieList = movies.stream()
                        .filter((Movie s) -> name.equals(s.getName())).filter((Movie s) -> s.getType() == movieType)
                        .collect(Collectors.toList());
            }
            return ResponseUtil.success(movieList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.error(e.getMessage());
        }
    }

    public ResponseUtil findByIndex(Integer idx){
        return ResponseUtil.success(this.find(idx));
    }

    private Movie find(Integer idx){
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            Movie movie = (Movie) listOperations.index("movie",idx);
            return movie;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ResponseUtil delete(Integer idx){
        ListOperations listOperations = redisTemplate.opsForList();
        if(idx == 0){
            listOperations.trim("movie",1,-1);
            return ResponseUtil.success();
        } else{
            Long res = listOperations.remove("movie", idx, find(idx));
            return ResponseUtil.success(res);
        }
    }

    public ResponseUtil update(Integer idx, Movie movie){
        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.set("movie",idx, movie);
        return ResponseUtil.success();
    }
}
