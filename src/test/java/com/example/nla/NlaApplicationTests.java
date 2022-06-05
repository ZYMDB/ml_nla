package com.example.nla;
import com.example.nla.mapper.*;
import com.example.nla.service.ColourService;
import com.example.nla.service.RPService;
import com.example.nla.util.C;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
class NlaApplicationTests {
    @Autowired
    ActionMapper actionMapper;
    @Autowired
    GiverMapper giverMapper;
    @Autowired
    RecMapper recMapper;
    @Autowired
    ResMapper resMapper;
    @Autowired
    TempMapper tempMapper;
    @Autowired
    ToolMapper toolMapper;
    @Autowired
    RPService rpService;
    @Autowired
    RpMapper rpMapper;
    @Autowired
    RpRegexMapper rpRegexMapper;
    @Autowired
    ColourService colourService;
    @Value("${test.value}")
    private String testV;
    static Map<String,Boolean> map = new HashMap<>();
    @Test
    void contextLoads() throws IOException {
        // 连接词
          // 谓语系列 ： 产生的 ，后产生的
          String c1 = colourService.getRS("后产生的");
          String c2 = colourService.getRS("产生的");
        // 施为对象
        String giv1 = C.rpInfo(giverMapper.queryById(1).getGivName());
        // 活动
        String ac1 = actionMapper.queryById(1).getActionName();
        String ac2 = actionMapper.queryById(2).getActionName();
        String ac3 = actionMapper.queryById(3).getActionName();
        // 受为对象
        String rec1 = C.rpInfo(recMapper.queryById(1).getRecName());
        // 中间对象
        String te1_v = C.rpInfo(tempMapper.queryById(1).getTempName());
        String te2_v = C.rpInfo(tempMapper.queryById(2).getTempName());
        String te3_v = C.rpInfo(tempMapper.queryById(3).getTempName());
        // 资源对象
        String res1 = resMapper.queryById(1).getResName();
        // 工具对象
        String tool1 = toolMapper.queryById(1).getToolName();
        String tool2 = toolMapper.queryById(2).getToolName();
        System.out.println("----------giv1:"+giv1);
        System.out.println("----------ac1:"+ac1);
        System.out.println("----------ac2:"+ac2);
        System.out.println("----------ac3:"+ac3);
        System.out.println("----------rec1:"+rec1);
        System.out.println("----------te1v:"+te1_v);
        System.out.println("----------te2v:"+te2_v);
        System.out.println("----------te3v:"+te3_v);
        System.out.println(
        colourService.getRS(giv1)+
        colourService.getRS(ac1)+colourService.getRS(rec1)+
        (rec1.equals(te1_v)?c1+colourService.getRS(rec1):c2+colourService.getRS(te1_v))
        );
        System.out.println(
                colourService.getRS(giv1)+
                colourService.getRS(ac2)+
                colourService.getRS(te1_v)+
                (rec1.equals(te2_v)?c1+colourService.getRS(rec1):c2+colourService.getRS(te2_v))
        );
        System.out.println(
                colourService.getRS(giv1)+
                colourService.getRS(ac3)+
                colourService.getRS(te2_v)+
                (rec1.equals(te3_v)?c1+colourService.getRS(rec1):c2+colourService.getRS(te3_v))
        );
    }
    @Test
    void configurationTest(){
        System.out.println("配置文件里读取的参数："+testV);
    }
    @Test
    void regexRPTest(){
        String content = "SpringFramework已有十余年的历史了，是Java应用程序开发框架的事实标准。";
        System.out.println(rpService.rpByRegex(content));
    }

}
