package com.micro.pmo.tools.moudle.common;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.micro.pmo.AppPmo;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(AppPmo.class)//属性:用于指定引导类
public abstract class BaseJunit {  
      
}  
 