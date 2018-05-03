/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class SMS_Service {
    
    public static final String ACCOUNT_SID = "ACc533cc52e76b0d7e7776e47be61dc861";
    public static final String AUTH_TOKEN = "c5aa6fb2ccafdff00ca19206dd687a1f";
  
    
    public void createSMS(long number, String sujet){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(ACCOUNT_SID,new PhoneNumber("+216"+number), new PhoneNumber("(256) 286-4610"), sujet).create();
        System.out.println(message.getSid());
    }
}



/**
*@Lau82 © 2018
*/
