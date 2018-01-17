package com.kingthy.service.test;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kingthy.KingthyProviderRabbitmqApplication;
import com.kingthy.constant.RabbitmqConstants;
import com.kingthy.dto.MaterialAccessoriesDTO;
import com.kingthy.dto.OrderCommentImgDTO;
import com.kingthy.dubbo.review.service.BuyersShowDubboService;
import com.kingthy.entity.BuyersShow;
import com.kingthy.entity.Goods;
import com.kingthy.entity.SensitiveWord;
import com.kingthy.proto.PersonPTO;
import com.kingthy.service.BuyersShowImgService;
import com.kingthy.service.EsGoodsService;
import com.kingthy.service.MaterialAccessoriesService;
import com.netflix.discovery.converters.Auto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 11:33 on 2017/5/17.
 * @Modified by:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KingthyProviderRabbitmqApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"eureka.client.enabled=false"})
//@Rollback
//@Transactional
public class BuyersShowImgServiceTest {


    @Autowired
    private BuyersShowImgService buyersShowImgService;

    @Autowired
    private EsGoodsService esGoodsService;

    @Autowired
    private MaterialAccessoriesService materialAccessoriesService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendProtobufTest(){

        try{

            PersonPTO.Person.Builder builder = PersonPTO.Person.newBuilder();
            builder.setId(1001);
            builder.setName("潘勇");
            builder.setEmail("xx@xx.com");
            PersonPTO.Person object = builder.build();

//        object.toByteArray();

//            rabbitTemplate.send("protobuf", "protobuf", new Message(object.toByteArray(), new MessageProperties()));

            rabbitTemplate.execute((channel) ->{
                channel.basicPublish("protobuf", "protobuf", null, object.toByteArray());
                channel.txCommit();
                return null;
            });

            Thread.sleep(3000l);

//            Message message = rabbitTemplate.receive("protobuf.queue");
//            PersonPTO.Person personPTO = PersonPTO.Person.parseFrom(message.getBody());
//            System.out.println(personPTO.getEmail() + "-------------------------" + personPTO.getName());
//            rabbitTemplate.getConnectionFactory().createConnection().createChannel(false).basicPublish("protobuf", "protobuf", null, object.toByteArray());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void saveSensitiveWord(){

        try{
            /*
            Set<String> set = new HashSet<String>();
            File file = new File("D:\\SensitiveWord.txt");    //读取文件
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");

            if(file.isFile() && file.exists()){      //文件流是否存在

                BufferedReader bufferedReader = new BufferedReader(read);
                String txt = null;
                while((txt = bufferedReader.readLine()) != null){    //读取文件，将文件内容放入到set中
                    set.add(txt);
                }
            }

            else{         //不存在抛出异常信息
                throw new Exception("敏感词库文件不存在");
            }
            */

/*
            for (String word : set){
                SensitiveWord sensitiveWord = new SensitiveWord();

                sensitiveWord.setCreateDate(new Date());
                sensitiveWord.setWord(word);
                sensitiveWord.setDelFlag(false);
                sensitiveWord.setVersion(0);

//                sensitiveWordMapper.insert(sensitiveWord);
            }
            */

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void syncEsGoods(){

        try {
            esGoodsService.syncEsGoods("97071061572518338");
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void sensitiveDataFilter(){

        OrderCommentImgDTO imgDTO = new OrderCommentImgDTO();
        imgDTO.setMemberUuid("97137901347464904");
        imgDTO.setBuyersUuid("97293067846105909");


        try {
            buyersShowImgService.sensitiveDataFilter(imgDTO);
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void syncipptest(){
        MaterialAccessoriesDTO message = new MaterialAccessoriesDTO();
        message.setTableType(1);
        message.setOperSt(4);
        message.setCode("97203244123554332");
        try {
           int result = materialAccessoriesService.syncCIPPInfo(message);
           Assert.assertTrue("success", result > 0);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Test
    public void insertR(){
        try {

            BuyersShow buyersShow = new BuyersShow();
            buyersShow.setOrderSn("xumin123");
            String g = buyersShowImgService.testinsertReturnUuid(buyersShow);
            Assert.assertTrue("success", !StringUtils.isEmpty(g));
        }catch (Exception e){
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }

    }

    @Test
    public void pushBuyShowTest(){
        BuyersShow buyersShow = new BuyersShow();
        buyersShow.setDelFlag(false);

        buyersShowImgService.queryPage(buyersShow).forEach(a -> {

            String memberUuid = a.getMemberUuid();

            OrderCommentImgDTO dto = new OrderCommentImgDTO();
            dto.setBuyersUuid(a.getUuid());
            dto.setMemberUuid(memberUuid);
            rabbitTemplate.convertAndSend(RabbitmqConstants.BuyersShowEnum.EXCHANGE.getValue(), RabbitmqConstants.BuyersShowEnum.ROUTING.getValue(), dto);
        });

    }

}
