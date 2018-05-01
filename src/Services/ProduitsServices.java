/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Produit;
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
public class ProduitsServices {
    ArrayList<Produit> listProd2;
     public ArrayList<Produit> getListProdC(String json) {
        ArrayList<Produit> listProd= new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) event.get("root");
            System.out.println(list);
            for (Map<String, Object> obj : list) {
                Produit e = new Produit();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setNomProduit(obj.get("nomProduit").toString());
                e.setDescription(obj.get("description").toString());
                e.setRegion(obj.get("region").toString());
                Double Prix = Double.parseDouble(obj.get("prix").toString());
                e.setPrix(Prix);
                e.setCategorie(obj.get("categorie").toString());
                e.setNomImage(obj.get("nomImage").toString());
                listProd.add(e);
            }
            return listProd;
        } catch (IOException ex) {}
        return null;
    }
     public ArrayList<Produit> getList2(){       
        listProd2= new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukI/web/app_dev.php/Produit/AffichMob");
        con.addResponseListener((NetworkEvent evt) -> {
            ProduitsServices ser = new ProduitsServices();
            listProd2 = ser.getListProdC(new String(con.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProd2;
    }
}
