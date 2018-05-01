/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiProduit;

import Entities.Produit;
import Services.ProduitsServices;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;


public class AffichProdClt {
    private Form f;
    private Container c2,c1,c,ck;
    private EncodedImage enc;
    private String url="http://localhost/SoukI/web/images3/";
    private ImageViewer imgv;
    private Image img;
    private SpanLabel l,l2,l3,l4,l5,l6;

    public AffichProdClt() {
        f=new Form("Produits!");
        c2=new Container(BoxLayout.y());
        ProduitsServices s =new ProduitsServices();
        ArrayList<Produit> le = s.getList2();
           for (Produit le1 : le) {
               c=new Container(BoxLayout.y());
               ck=new Container(BoxLayout.y());
               c1=new Container(BoxLayout.x());
               try {
                enc = EncodedImage.create("/giphy.gif").scaledEncoded(150,250);
            }
            catch (IOException ex) {
                System.out.println("error encoder");
            }
             String urll=url+le1.getNomImage();
            img = URLImage.createToStorage(enc, urll, urll, URLImage.RESIZE_SCALE);
            imgv = new ImageViewer();
            imgv.setImage(img);
            
            l = new SpanLabel(le1.getNomProduit());
            l2 = new SpanLabel(le1.getDescription());
            l3 = new SpanLabel(le1.getCategorie());
            l4 = new SpanLabel(le1.getRegion());
            l5 = new SpanLabel(Float.toString((float) le1.getPrix()));
            l6= new SpanLabel("****************************************");
            c.add(l);
            c.add(l3);
            c.add(l4);
            c.add(l5);
            c.add(l2);
            c1.add(imgv);
            c1.add(c);
            ck.add(c1);
            ck.add(l6);
            c2.add(ck);
           }
           f.add(c2);
    }
    

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
