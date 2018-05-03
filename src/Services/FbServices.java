/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Annonce;
import GuiAnnonce.AffichAnnAllClt;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import com.restfb.Parameter;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.FacebookType;

public class FbServices  {
    Form f;
    private final Resources theme;
    public FbServices (){
      theme = UIManager.initFirstTheme("/theme");
      AnnoncesServices serviceeva=new AnnoncesServices();
      ArrayList<Annonce> liste=serviceeva.getList2();
      f =new Form("Evenementt", BoxLayout.y());
      for(Annonce t:liste){
             Container c = new Container(BoxLayout.y());
             Label Titre = new Label(t.getNomAnnonce());
            SpanLabel Desc = new SpanLabel(t.getDescription());
            SpanLabel Desc2 = new SpanLabel(t.getType());
            SpanLabel Desc3 = new SpanLabel(Double.toString(t.getPrixReducton())+"%");
            Button par=new Button("Partager sur FB");
            par.addActionListener(e -> {
            String accessToken = "EAACEdEose0cBACu3EGIsdTbGk74eCoZAZCcAwBuApW5ahF4yj3WubPOZAAfM7HfWdZAUPltq99aBNPMKoJenavVH568hpx2sOg0sjusgV52vARrMN6YoT80snXQ68fSgd526qXDZCpL8bT5KM01ZBZCv6hUMXveIE0EWiDJM0FzWUTbxyZCIcAmTqdGtrqHcYFlvsfGx1SRYFAZDZD";
            FacebookClient fbClient = new DefaultFacebookClient(accessToken);
            FacebookType response = fbClient.publish("me/feed", FacebookType.class,Parameter.with("message","daliyoooo patron"));
            Dialog.show("Information","Votre Annonce à été publiée sur facebook","ok",null);
            });
            c.addAll(Titre, Desc,Desc2);
            if(t.getPrixReducton()!=0)
            {
                c.add(Desc3);
            }
            c.add(par);
            c.getStyle().setBgColor(0x99CCCC);
            c.getStyle().setBgTransparency(255);
            f.add(ComponentGroup.enclose(c)); 
        }
      Toolbar tb2= f.getToolbar();
      tb2.addCommandToLeftBar("back",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
            AffichAnnAllClt AffichAnn=new AffichAnnAllClt();
            AffichAnn.getF().show();
      });
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
}