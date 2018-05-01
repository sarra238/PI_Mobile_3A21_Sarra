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
import Entities.Evenement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lv
 */
public class EvenementServices {
     public ArrayList<Evenement> getListEvent(String json) {

        ArrayList<Evenement> listEvent = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(event);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
            System.out.println(list);

            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                //System.out.println(id);
                e.setId((int) id);
          ///e.setUserId(O(obj.get("id").toString().trim()));
                e.setNomEvenement(obj.get("nomEvenement").toString());
               e.setDescription(obj.get("description").toString());
               e.setNomImg(obj.get("nomImg").toString());
           e.setDateDeb(obj.get("dateDeb2").toString());
                //System.out.println(obj.get("dateDeb2").toString());
           e.setDateFin(obj.get("dateFin2").toString());
          
             //   Date deb (obj.get("dateDeb").toString());
             // e.setDateDeb(obj.get("dateDeb").toString());
                 // e.setDateFin(obj.get("dateFin").toString());
                         e.setType(obj.get("type").toString());
                       Object n = obj.get("idUser");
                       System.out.println("jj"+n.toString());
              // int idr= n.getId();
              
            //  e.setUserId(idr);
              
            
             //   System.out.println(obj.get("idUser").toString());
                          e.setLocalisation(obj.get("localisation").toString());
               // System.out.println(e);
                listEvent.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEvent);
        return listEvent;

    }
     
    ArrayList<Evenement> listEvent = new ArrayList<>();
    
    public ArrayList<Evenement> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/event/a");  
        con.addResponseListener((NetworkEvent evt) -> {
            EvenementServices ser = new EvenementServices();
            listEvent = ser.getListEvent(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvent;
    }

}
