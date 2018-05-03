/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Display;

public class Local_Notification {
    public final LocalNotification local_Notif = new LocalNotification();
    public Local_Notification(String title, String body) {
        if(Display.getInstance().getCurrent() != null) {
            local_Notif.setId("intern");
            local_Notif.setAlertTitle(title);
            local_Notif.setAlertBody(body);
            Display.getInstance().scheduleLocalNotification(local_Notif, System.currentTimeMillis() + 1000, LocalNotification.REPEAT_NONE); 
        }
    }
    public LocalNotification getLocal_Notif() {
        return local_Notif;
    }


}



/**
*@Lau82 © 2018
*/
