
## CustomMqttClient

## MQTT特点：    
1、基于发布/订阅模型的协议    
2、他是二进制协议，二进制的特点就是紧凑、占用空间小。他的协议头只有2个字节     
3、提供了三种消息可能性保障：最多一次 0、最少一次 1、只有一次 2    

## MQTT Client       
1、MQTT Java客户端Eclipse paho实现数据的发送和接收       

## 第三方框架       
1、[Android Mqtt 开源框架EasyMqttAndroidClient](https://blog.csdn.net/u013347784/article/details/78924835)           
2、[Android APP必备高级功能，消息推送之MQTT](https://blog.csdn.net/qq_17250009/article/details/52774472)          
3、[Android客户端通过PahoMQTT和Broker建立SSL/TLS的单向连接](https://www.jianshu.com/p/20b54423e3e5)      
4、[mqtt paho ssl java端代码](https://blog.csdn.net/lingshi210/article/details/52439050)     

## 参考文献    
1、[快速集成MQTT协议到Android客户端,只需要简单的几个步骤,无需关注搭建细节](https://github.com/zhangjianying/androidMQTTlib)        
2、[这个MQTTService做的比较完善，后续值得参考](https://github.com/AshuJoshi/androidMQTT/tree/master/androidMQTT)      
3、[MqttClientAndroid结合EventBus处理消息](https://github.com/LichFaker/MqttClientAndroid)                
4、[AndroidMqttWithSSL暂时没发现有啥用](https://github.com/widercode/AndroidMqttWithSSLSample)          


## 爬坑

        //FileInputStream fin = new FileInputStream(path);
        InputStream fin = BasicApplication.getAppContext().getAssets().open(path);
        
1、[支付宝集成时的InvalidKeySpecException](https://www.cnblogs.com/littlepanpc/p/3989232.html)      
2、
