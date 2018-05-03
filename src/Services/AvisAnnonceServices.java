/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.AvisAnnonces;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AvisAnnonceServices {

    public AvisAnnonceServices() {
    }
    
    public void AjoutAvis(AvisAnnonces ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/annonce/AjoutCM/" + ta.getAvis();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public String getListAnnC(String json) {
        String k = null;
         try {
            JSONParser j = new JSONParser();
            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
            for (Map<String, Object> obj : list) {
                k=obj.toString();
            }
            return k;
        } catch (IOException ex) {}
        return null;
    } 
     String listComAnn2;
    public String getList2(String ta){
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/annonce/CountAvisMob/" + ta);
        con.addResponseListener((NetworkEvent evt) -> {
            listComAnn2 = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listComAnn2;
    }
    
}
