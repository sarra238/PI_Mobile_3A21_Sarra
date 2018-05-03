/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiAnnonce;

import Entities.Annonce;
import Services.AnnoncesServices;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

public class Recherche {
    private Form f;
    SpanLabel lb,l,l2,l3;
    TextField nom;
    Container c,c1,c2,c3;
    private Image img;
    private Resources theme;
    public  String msg;
    public String url= "http://localhost/SoukI/web/imagesAnnonce/";
    private EncodedImage enc ;
    private ImageViewer imgv;
    public Recherche() {
        theme = UIManager.initFirstTheme("/theme");
        f=new Form("Annonces!",new FlowLayout(Component.CENTER, Component.CENTER));
        c2=new Container(BoxLayout.y());
        Container ck=new Container(BoxLayout.y());
        AnnoncesServices s =new AnnoncesServices();
        TextField txF=new TextField("","Recherche",150,0);
        c2.add(txF);
        Button Recherche=new Button("Recherche");
         c2.add(Recherche);
         f.add(c2);
         Toolbar tb2= f.getToolbar();
        Form f2=new Form("Annonces");
        Toolbar tb1= f2.getToolbar();
        Recherche.addActionListener((ActionListener) (ActionEvent evt1) -> {
        ArrayList<Annonce> le = s.getList3(txF.getText());
        for (Annonce le1 : le) {
            c=new Container(BoxLayout.x());
            try {
                enc = EncodedImage.create("/giphy.gif").scaledEncoded(150,150);
            }
            catch (IOException ex) {
                System.out.println("error encoder");
            }
            String urll=url+le1.getImage();
            img = URLImage.createToStorage(enc, urll, urll, URLImage.RESIZE_SCALE);
            imgv = new ImageViewer();
            imgv.setImage(img);
            l = new SpanLabel(le1.getNomAnnonce());
            l2 = new SpanLabel(le1.getDescription());
            l3 = new SpanLabel(le1.getType());
            c1=new Container(BoxLayout.y());
            c1.add(l);
            c1.add(l2);
            c1.add(l3);
            c.add(imgv);
            c.add(c1);
            ck.add(c);
        }
        f2.add(ck);
        f2.show();
        });  
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
    
}
