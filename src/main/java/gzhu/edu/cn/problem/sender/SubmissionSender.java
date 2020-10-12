package gzhu.edu.cn.problem.sender;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * @program: exam
 * @description: 发送测评消息
 * @author: 丁国柱
 * @create: 2020-10-07 21:34
 */
@Component
public class SubmissionSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);            //指定 ConfirmCallback
        rabbitTemplate.setReturnCallback(this);             //指定 ReturnCallback

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息发送成功" + correlationData);
        } else {
            System.out.println("消息发送失败:" + cause);
        }
    }

    public void sendSubmission(Long submissionId){
        this.rabbitTemplate.convertAndSend("JUDGE-EXCHANGE-1","judge_queue", submissionId);
    }

    /**
     * 发送完成测评作业的消息
     * @param info
     */
    public void sendSubmission(String info){
        this.rabbitTemplate.convertAndSend("JUDGE-EXCHANGE-1","judge_queue", info);
    }

   /* public void sendSubmission(Map<String,Object> message){
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder().contentEncoding("UTF-8").contentType("text/plain").headers(new HashMap<String, Object>()).priority(0);
        this.rabbitTemplate.convertAndSend("JUDGE-EXCHANGE-1","judge_queue", message);
    }
*/

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        // 反序列化对象输出
        System.out.println("消息主体: " + message.getBody());
        System.out.println("应答码: " + replyCode);
        System.out.println("描述：" + replyText);
        System.out.println("消息使用的交换器 exchange : " + exchange);
        System.out.println("消息使用的路由键 routing : " + routingKey);
    }


}