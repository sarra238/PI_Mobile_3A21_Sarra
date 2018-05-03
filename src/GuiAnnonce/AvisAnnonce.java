/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiAnnonce;

import Entities.AvisAnnonces;
import Services.AvisAnnonceServices;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

public class AvisAnnonce {
    private Form f;
    private final  Resources theme;
    public AvisAnnonce() {
        theme = UIManager.initFirstTheme("/theme");
        f=new Form("Avis Service!");
        AvisAnnonceServices n=new AvisAnnonceServices();
        String k,lo,m,o,p;
        k=n.getList2("Mauvais");
        lo=n.getList2("Passable");
        m=n.getList2("Bien");
        o=n.getList2("Assez Bien");
        p=n.getList2("TrésBien");
        Container clo= new Container(BoxLayout.y());
        if(k!=null){
            Container cla= new Container(BoxLayout.x());
            SpanLabel la=new SpanLabel("Mauvais : ");
            SpanLabel la1=new SpanLabel(k);
            cla.add(la);
            cla.add(la1);
            clo.add(cla);
        }
        if(lo!=null){
            Container clb= new Container(BoxLayout.x());
            SpanLabel lb=new SpanLabel("Passable : ");
            SpanLabel lb1=new SpanLabel(lo);
            clb.add(lb);
            clb.add(lb1);
            clo.add(clb);
        }
        if(lo!=null){
            Container clc= new Container(BoxLayout.x());
            SpanLabel lc=new SpanLabel("Bien : ");
            SpanLabel lc1=new SpanLabel(m);
            clc.add(lc);
            clc.add(lc1);
            clo.add(clc);
        }
        if(lo!=null){
            Container cld= new Container(BoxLayout.x());
            SpanLabel ld=new SpanLabel("Assez Bien : ");
            SpanLabel ld1=new SpanLabel(o);
            cld.add(ld);
            cld.add(ld1);
            clo.add(cld);
        }
        if(lo!=null){
            Container cle= new Container(BoxLayout.x());
            SpanLabel le=new SpanLabel("TrésBien : ");
            SpanLabel le1=new SpanLabel(p);
            cle.add(le);
            cle.add(le1);
            clo.add(cle);
            
        }
        SpanLabel ssp= new SpanLabel("********************************");
        clo.add(ssp);
        f.add(clo);
        Container c= new Container(BoxLayout.y());
        SpanLabel l = new SpanLabel("Veuillez choisir un avis");
        ComboBox cb = new ComboBox("Mauvais","Passable", "Bien","Assez Bien","TrésBien");
        Button bAvis = new Button("Donner Avis");
        bAvis.addActionListener((ActionListener) (ActionEvent evt1) -> {
            AvisAnnonceServices ser = new AvisAnnonceServices();
            AvisAnnonces t = new AvisAnnonces((String) cb.getSelectedItem());
            ser.AjoutAvis(t);  
            Message mk = new Message("Vous avez choisit de noter notre service avec "+cb.getSelectedItem()+"! MERCI ");
            Display.getInstance().sendMessage(new String[] {"sarra.abdeljaouad@esprit.tn"}, "Avis sur le service annonce dans l'application Souk El Medina", mk);
            AffichAnnAllClt AffichAnn=new AffichAnnAllClt();
            AffichAnn.getF().show();
        });
        c.add(l);
        c.add(cb);
        c.add(bAvis);
        f.add(c);
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
