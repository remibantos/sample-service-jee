/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rembx.sample.jee.service;

import org.rembx.sample.jee.Resources;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;

/**
 * Sample of stateless EJB WebService which performs async operations
 * @author remibantos
 */
@Stateless
@WebService
public class SampleAsyncService {
    
    @Inject
    JMSContext jmsContext;
    
    @Resource(mappedName = Resources.TEST_QUEUE)
    Queue queue;

    public SampleAsyncService(){

    }
    
    @WebMethod
    public void produceJMSMessage(){
        jmsContext.createProducer().send(queue,"Hello, i am a JMS message sent at " +
                new Date());
    }

    @WebMethod
    public String consumeJMSMessage(){
        return jmsContext.createConsumer(queue).receiveBody(String.class,5000);
    }


    @WebMethod
    public void asyncOperation(){
        // TODO use asynchronous annotation
    }

}
