package com.imooc.girl.service;

import com.imooc.girl.aspect.HttpAspect;
import com.imooc.girl.domain.Girl;
import com.imooc.girl.enums.ResultEnum;
import com.imooc.girl.exception.GirlException;
import com.imooc.girl.repository.GirlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GirlService {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    public GirlRepository girlRepository;

    @Transactional
    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setAge(18);
        girlA.setCupSize("A");

        Girl girlB = new Girl();
        girlB.setAge(19);
        girlB.setCupSize("BB");

        girlRepository.save(girlA);
        girlRepository.save(girlB);
    }

    public void getAge(Integer id) throws Exception {
        Girl girl = girlRepository.findOne(id);
        Integer age = girl.getAge();
        logger.info("+++++++++++++++++" + age.toString());
        if (age < 10) {
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age < 16) {
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        } else {
            throw new GirlException(ResultEnum.SUCCESS);
        }
    }
}
