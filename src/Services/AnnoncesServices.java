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
 * @author Win10
 */
public class AnnoncesServices {
     ArrayList<Annonce> listAnn2;
     public ArrayList<Annonce> getListAnnC(String json) {
        ArrayList<Annonce> listAnn = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
            for (Map<String, Object> obj : list) {
                Annonce e = new Annonce();

                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setNomAnnonce(obj.get("nomAnnonce").toString());
                e.setDescription(obj.get("description").toString());
                e.setType(obj.get("type").toString());
                Double k=Double.parseDouble(obj.get("prixReducton").toString());
                e.setPrixReducton(k);
                e.setImage(obj.get("nomImage").toString());
                listAnn.add(e);
            }
            return listAnn;
        } catch (IOException ex) {}
        return null;
    }
    public ArrayList<Annonce> getList2(){       
        listAnn2= new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/annonce/AffichCM");
        con.addResponseListener((NetworkEvent evt) -> {
            AnnoncesServices ser = new AnnoncesServices();
            listAnn2 = ser.getListAnnC(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnn2;
    }
    public ArrayList<Annonce> getList3(String k){       
        listAnn2= new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/annonce/RechM/"+k);
        con.addResponseListener((NetworkEvent evt) -> {
            AnnoncesServices ser = new AnnoncesServices();
            listAnn2 = ser.getListAnnC(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnn2;
    }
    public void AjoutCom(CommentAnn ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukI/web/app_dev.php/annonce/AjoutComM/" + ta.getCommentAnn();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

}
