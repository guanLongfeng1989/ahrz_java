package com.cn.riwise.general.common;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.common.utils.ServiceSettings;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
import com.cn.riwise.general.pojo.RiwiseMessageObj;

@SuppressWarnings("unused")
public class SendMessageSystem {
	//private  static Logger logger = Logger.getLogger(SendMessageSystem.class);
	private static Logger log4j=Logger.getLogger(SendMessageSystem.class);
	
	public static TopicMessage SendMessageOP(RiwiseMessageObj sms) {
		/**
		 * Step 1. 获取主题引用
		 */
		log4j.info("短信发送开始");
		// String transQueueName = "transQueueName";
		String accessKeyId = ServiceSettings.getMNSAccessKeyId();
		String accessKeySecret = ServiceSettings.getMNSAccessKeySecret();
		String endpoint = ServiceSettings.getMNSAccountEndpoint();

		CloudAccount account = new CloudAccount(accessKeyId, accessKeySecret,
				endpoint);
		MNSClient client = account.getMNSClient(); // this client need only
													// initialize once
		// 创建主题
		CloudTopic topic = client.getTopicRef(RiwiseMessage.topic);

		/**
		 * Step 2. 设置SMS消息体（必须）
		 * 
		 * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
		 */
		RawTopicMessage msg = new RawTopicMessage();
		msg.setMessageBody(RiwiseMessage.MessageBody);

		/**
		 * Step 3. 生成SMS消息属性
		 */
		MessageAttributes messageAttributes = new MessageAttributes();
		BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
		// 3.1 设置发送短信的签名（SMSSignName）
		batchSmsAttributes.setFreeSignName(RiwiseMessage.SignName);
		// 3.2 设置发送短信使用的模板（SMSTempateCode）
		batchSmsAttributes.setTemplateCode(sms.getAlicode());
		// 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
		BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
		     
		   @SuppressWarnings("rawtypes")
	     	Map map = sms.getParamemap();
           @SuppressWarnings("rawtypes")
	    	Iterator iter = map.entrySet().iterator();		//获取key和value的set
           while (iter.hasNext()) {
           @SuppressWarnings("rawtypes")
		   Map.Entry entry = (Map.Entry) iter.next();		//把hashmap转成Iterator再迭代到entry
           String key = (String) entry.getKey();		//从entry获取key
           String val = (String) entry.getValue();	//从entry获取value
           smsReceiverParams.setParam(key,val);
           }
		// 3.4 增加接收短信的号码
        String[] strlist=sms.getTel().split(",");
        for(int i=0;i<strlist.length;i++){
        	batchSmsAttributes.addSmsReceiver(strlist[i], smsReceiverParams);
        }
		messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
		try {
			/**
			 * Step 4. 发布SMS消息
			 */
			TopicMessage ret = topic.publishMessage(msg, messageAttributes);
			log4j.info("MessageId: " + ret.getMessageId());
			log4j.info("MessageMD5: " + ret.getMessageBodyMD5());
			log4j.info("短信发送结束");
			return ret;
		} catch (ServiceException se) {
			log4j.info(se.getErrorCode() + se.getRequestId());
			log4j.info(se.getMessage());
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		client.close();
		log4j.info("短信发送结束");
		return null;
	}
}
