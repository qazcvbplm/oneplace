package com.ops.servicedaoimple;

import com.ops.dao.TestMapper;
import com.ops.entity.Test;
import com.ops.servicedao.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Scanner;

@Component
public class TestServiceImple implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Transactional
    @Override
    public void test() {
        Test test = testMapper.selectByPrimaryKey(1);
        System.out.println(test.getMoney());
        test2();
        test.setMoney(0);
        testMapper.updateByPrimaryKeySelective(test);
    }

    private void test2() {
        Scanner input = new Scanner(System.in);
        input.nextInt();
    }

}
