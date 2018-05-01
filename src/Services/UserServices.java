/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Win10
 */
public class UserServices {

    public UserServices() {
    }
   
     ArrayList<User> listUser2;
     public ArrayList<User> getListUserC(String json) {
        ArrayList<User> listUser = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
            for (Map<String, Object> obj : list) {
                User e = new User();

                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setUsername(obj.get("username").toString());
                listUser.add(e);
            }
            return listUser;
        } catch (IOException ex) {}
        return null;
    }
    public ArrayList<User> getList2(String a){       
        listUser2= new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/AffCM/"+a);
        con.addResponseListener((NetworkEvent evt) -> {
            UserServices ser = new UserServices();
            listUser2 = ser.getListUserC(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUser2;
    }
    
}
