/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import Entities.particEv;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lv
 */
public class PartService {
    public void ajoutPar (particEv t){
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/event/newP/20/"+t.getIdEv()+"/" + t.getType();
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public void modifPar (particEv t){
         ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/event/serP/20/"+t.getIdEv()+"/" + t.getType();
        con.setUrl(Url);

        //System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public ArrayList<particEv> getListP(String json) {

        ArrayList<particEv> listEvent = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(event);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
            System.out.println(list);

            for (Map<String, Object> obj : list) {
                particEv e = new particEv();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                //System.out.println(id);
                e.setId((int) id);
          ///e.setUserId(O(obj.get("id").toString().trim()));
                e.setType(obj.get("type").toString());
              
          
                        
      
                listEvent.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEvent);
        return listEvent;

    }
     
    ArrayList<particEv> listEvent = new ArrayList<>();
    
    public ArrayList<particEv> affichpar(int t){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/event/serPm/20/"+t);  
        con.addResponseListener((NetworkEvent evt) -> {
            PartService ser = new PartService();
            listEvent = ser.getListP(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvent;
    }
    
}
