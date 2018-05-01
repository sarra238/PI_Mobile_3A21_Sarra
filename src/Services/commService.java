/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Annonce;
import Entities.CommentAnn;
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
 * @author lv
 */
public class commService {
    ArrayList<CommentAnn> listComAnn2;
    public ArrayList<CommentAnn> getListAnnC(String json) {
        ArrayList<CommentAnn> listComAnn = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
            for (Map<String, Object> obj : list) {
                CommentAnn e = new CommentAnn();

                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setCommentAnn(obj.get("Commentaire").toString());
                e.setDate(obj.get("dateC").toString());
                listComAnn.add(e);
            }
            return listComAnn;
        } catch (IOException ex) {}
        return null;
    }
    public ArrayList<CommentAnn> getList2(){       
        listComAnn2= new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/annonce/AffichComMob");
        con.addResponseListener((NetworkEvent evt) -> {
            commService ser = new commService();
            listComAnn2 = ser.getListAnnC(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listComAnn2;
    }
    
}
