package org.rembx.sample.jee;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * User: bantos
 * Date: 08/01/14
 * Time: 19:52
 */

@MessageDriven(name = "TestMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = Resources.TEST_QUEUE),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class TestMDB implements MessageListener {
    
    @Override
    public void onMessage(Message message) {
        try{
            System.out.println("Received message: " + message.getBody(String.class));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
