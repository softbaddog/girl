package com.imooc.girl.controller;

import com.imooc.girl.aspect.HttpAspect;
import com.imooc.girl.domain.Girl;
import com.imooc.girl.domain.Result;
import com.imooc.girl.repository.GirlRepository;
import com.imooc.girl.service.GirlService;
import com.imooc.girl.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    /**
     * Get all Girls
     * @return
     */
    @GetMapping(value = "/girls")
    public Result<List<Girl>> girlList() {
        logger.info("girlList");
        return ResultUtil.success(girlRepository.findAll());
    }

    /**
     * Add a new Girl
     * @return
     */
    @PostMapping(value = "/girls")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        girl.setCupSize(girl.getCupSize());
        girl.setAge(girl.getAge());

        return ResultUtil.success(girlRepository.save(girl));
    }

    /**
     * Get a Girl from Id
     * @param id
     * @return
     */
    @GetMapping(value = "/girls/{id}")
    public Result<Girl> girlFindOne(@PathVariable("id") Integer id) {
        return ResultUtil.success(girlRepository.findOne(id));
    }

    /**
     * Update a Girl
     * @param id
     * @param cupSize
     * @param age
     * @return
     */
    @PutMapping(value = "girls/{id}")
    public Result<Girl> girlUpdateOne(@PathVariable("id") Integer id,
                               @RequestParam("cupSize") String cupSize,
                               @RequestParam("age") Integer age) {
        Girl girl = girlRepository.findOne(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return ResultUtil.success(girlRepository.save(girl));
    }

    /**
     * Delete a Grils
     * @param id
     */
    @DeleteMapping(value = "girls/{id}")
    public Result girlDeleteOne(@PathVariable("id") Integer id) {
        girlRepository.delete(id);
        return ResultUtil.success();
    }

    @GetMapping(value = "girls/age/{age}")
    public List<Girl> getGirlsByAge(@PathVariable("age") Integer age) {
        return girlRepository.findByAge(age);
    }

    @PostMapping(value = "girls/two")
    public void girlTwo() {
        girlService.insertTwo();
    }


    @GetMapping(value = "girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
        girlService.getAge(id);
    }
}
