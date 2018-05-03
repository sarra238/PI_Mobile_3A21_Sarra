/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiAnnonce;

import Connexion.AfterCnx;
import Entities.Annonce;
import Services.AnnoncesServices;
import Services.FbServices;
import Services.Local_Notification;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
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

public class AffichAnnAllClt {
    private Form f;
    TextField nom;
    SpanLabel l,l2,l3,lr,lb;
    Container c,c1,c2,c3;
    private Image img;
    private Resources theme;
    public  String msg;
    public String url= "http://localhost/SoukI/web/imagesAnnonce/";
    private EncodedImage enc ;
    private ImageViewer imgv;
    private Database db;
    private Boolean created = false;
    public AffichAnnAllClt() {
         created = Database.exists("Annonce");
        try {
           db= Database.openOrCreate("Annonce");
        } catch (IOException ex) {
            System.out.println("Problème connection");
        }
        if (created == false) {
            try {
                db.execute("create table Annonce (id INTEGER,Nom TEXT,desc TEXT,Type TEXT); ");
                System.out.println(" creation ok");
            } catch (IOException ex) {
                System.out.println("Problème creation");
            }
        }
        theme = UIManager.initFirstTheme("/theme");
        f=new Form("Annonces!");
        c2=new Container(BoxLayout.y());
        AnnoncesServices s =new AnnoncesServices();
        ArrayList<Annonce> le = s.getList2();
        Form f2=new Form("Détails!",new FlowLayout(Component.CENTER, Component.CENTER));
        for (Annonce le1 : le) {
            Toolbar tb1= f2.getToolbar();
            c=new Container(BoxLayout.x());
            try {
                enc = EncodedImage.create("/giphy.gif").scaledEncoded(175,200);
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
            lr=new SpanLabel(Double.toString(le1.getPrixReducton())+"%");
            String g=Double.toString(le1.getPrixReducton());
            c1=new Container(BoxLayout.y());
            c1.add(l);
            c1.add(l2);
            c1.add(l3);
            if(g!= null || !"0".equals(g)){
                c1.add(lr);
            }
            c.add(imgv);
            c.add(c1);
            c2.add(c);
            Button b = new Button();
            b.addActionListener((ActionListener) (ActionEvent evt) -> {
                 f2.removeAll();
                 Container info = new Container(BoxLayout.y());
                 Image imgk = URLImage.createToStorage(enc, urll, urll, URLImage.RESIZE_SCALE);
                 ImageViewer imgv2 = new ImageViewer();
                 imgv2.setImage(imgk);
                 info.add(imgv2);
                 SpanLabel l6 = new SpanLabel(le1.getNomAnnonce());
                 SpanLabel l4 = new SpanLabel(le1.getDescription());
                 SpanLabel l5 = new SpanLabel(le1.getType());
                 SpanLabel l44=new SpanLabel(Double.toString(le1.getPrixReducton())+"%");
                 int ijk=le1.getId();
                 String gg=Double.toString(le1.getPrixReducton());
                  
                 info.add(l6);
                 info.add(l4);
                 info.add(l5);
                 if(gg!= null || !"0".equals(gg)){
                 info.add(l44);
                 }
                 f2.add(info);
                 /********************************************Ajout***************************************************/
                    tb1.addCommandToOverflowMenu("Ajout Favori", theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt2) -> {
                    String ss= le1.getNomAnnonce();
                    String sss=le1.getDescription();
                    String ssss=le1.getType();
                    int ij=le1.getId();
                    try {
                                     db.execute("insert into Annonce (id,nom,desc,Type ) values ('" + ij + "','" + ss + "','" + sss + "','" + ssss + "');");
                                     Local_Notification lo=new Local_Notification("Ajout","Ajout Réussi !");
                                     lo.getLocal_Notif();
                                     System.out.println("insertion");
                        }
                    catch (IOException ex) {
                            System.out.println("pas insertion");
                    }
                    });
                    /******************************************************************************************************/
                    tb1.addCommandToOverflowMenu("Favoris", theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt3) -> {
                    Form f5 = new Form(BoxLayout.y());
                    try {
                            Cursor curs = db.executeQuery("select * from Annonce;");
                            while (curs.next()) {
                                Row r = curs.getRow();
                                String k=Integer.toString(r.getInteger(0));
                                String kk = r.getString(1);
                                String kkk = r.getString(2);
                                String kkkk = r.getString(3);
                                SpanLabel ssp=new SpanLabel("***************");
                                SpanLabel Sp= new SpanLabel(kk);
                                SpanLabel Sp2=new SpanLabel(kkk);
                                SpanLabel Sp3=new SpanLabel(kkkk);
                                Container cccc=new Container(BoxLayout.y());
                                cccc.add(ssp);
                                cccc.add(Sp);
                                cccc.add(Sp2);
                                cccc.add(Sp3);
                                f5.add(cccc);
                            }
                        }
                    catch (IOException ex) {
                            System.out.println("Affichage pas bon ");
                    }
                        f5.show();
                        Toolbar tb5= f5.getToolbar();
                        tb5.addCommandToLeftBar("back",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                        f2.show();
                        });
                    });
                     /******************************************************************************************************/
                    tb1.addCommandToOverflowMenu("Supprimer des favo", theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt3) -> {
                     try {
                         db.execute("Delete from Annonce where id='"+ijk+"';");
                         System.out.println("sup");
                     } catch (IOException ex) {
                        System.out.println("Error sup");
                     }
                    });
                    tb1.addCommandToLeftBar("back",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                    f.show();
                    });
                 f2.show(); 
                
            });
            c.setLeadComponent(b);
        }
        
        f.add(c2);
           
        Toolbar tb2= f.getToolbar();
           tb2.addCommandToLeftBar("back",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                AfterCnx AfCnx = new AfterCnx();
                AfCnx.getF().show();
        });
           tb2.addCommandToOverflowMenu("Commenter",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                CommentaireAnn Avis=new CommentaireAnn();
                Avis.getF().show();
        });
           tb2.addCommandToOverflowMenu("Avis",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                AvisAnnonce Avis=new AvisAnnonce();
                Avis.getF().show();
        });
           tb2.addCommandToOverflowMenu("Recherche",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                Recherche Avis=new Recherche();
                Avis.getF().show();
        });
        tb2.addCommandToOverflowMenu("Partager Facebook",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                     FbServices fb=new FbServices();
                     fb.getF().show();
        });
         tb2.addCommandToOverflowMenu("Map",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                Map m=new Map();
                m.getHi().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
