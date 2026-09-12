package com.sky.controller.admin;


import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/dish")
@Api(tags="菜品管理")
@Slf4j
@RestController
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping()
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增加菜品信息{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> list(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品分页查询接口{}",dishPageQueryDTO);
        PageResult pageResult = dishService.list(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping()
    @ApiOperation("批量删除菜品")
    public Result delete(@RequestParam List<Long> ids){
        log.info("批量删除菜品：{}", ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }
}
