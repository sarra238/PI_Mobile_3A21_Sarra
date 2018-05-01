/**
* @Project: AllForKids_Mobile
* @Classe: Local_Notification
* @Date: 27 avr. 2018
* @Time: 01:16:39
*
* @author Lauris
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
    //        local_Notif.setAlertSound("");  //alert sound file name must begin with notification_sound

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
