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

public class EvenementServices {
     public ArrayList<Evenement> getListEvent(String json) {
        ArrayList<Evenement> listEvent2 = new ArrayList<>();
        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(event);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
            System.out.println(list);

            for (Map<String, Object> obj : list) {
                Evenement e = new Evenement();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setNomEvenement(obj.get("nomEvenement").toString());
                e.setDescription(obj.get("description").toString());
                e.setNomImg(obj.get("nomImg").toString());
                e.setDateDeb(obj.get("dateDeb2").toString());
                e.setDateFin(obj.get("dateFin2").toString());
                e.setType(obj.get("type").toString());
                Object n = obj.get("idUser");
                System.out.println("jj"+n.toString());          
                e.setLocalisation(obj.get("localisation").toString());
                listEvent2.add(e);
            }
        } 
        catch (IOException ex) {}
        return listEvent2;
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
