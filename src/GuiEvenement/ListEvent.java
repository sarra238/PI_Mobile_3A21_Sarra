/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiEvenement;

import Connexion.AfterCnx;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import Services.EvenementServices;
import Services.PartService;
import Entities.Evenement;
import Entities.particEv;

import java.io.IOException;
import java.util.ArrayList;
public class ListEvent {
     
    Form f;
    SpanLabel lb,l,l2;
    TextField nom;
    Container c;
    private Image img;
    private Resources theme;
    public  String msg;
    private Database db;
    private boolean created=false;
    public static int j;
    public String url= "http://localhost/SoukI/web/images2/";
     private EncodedImage enc ;
     private ImageViewer imgv;
     private Label ss ;
     public ListEvent() 
     {
        f = new Form();
        EvenementServices s =new EvenementServices();
        ArrayList<Evenement> le = s.getList2(); 
        created= Database.exists("pi");
         try{
            db= Database.openOrCreate("pi");
             if (created==false){
                 db.execute("create table participation (id INTEGER,type TEXT,ide INTEGER,idU INTEGER);");
             }
         }
        catch (IOException ex) {}
        for (int i = 0; i < le.size(); i++) 
        {
          c=new Container(new FlowLayout(CENTER));
       try 
       {
            enc = EncodedImage.create("/giphy.gif");}
            catch (IOException ex) {
            System.out.println("error encoder");
       }
       ss =new Label(le.get(i).getNomImg());       
       img = URLImage.createToStorage(enc, "imagea"+i, url+ss.getText(), URLImage.RESIZE_SCALE);
       imgv = new ImageViewer(img);
       c.add(imgv);
       l = new SpanLabel(le.get(i).getNomEvenement());
       c.add(l);
       Button b = new Button("detail");
       c.add(b);
       f.add(c);
            b.setUIID(le.get(i).getNomEvenement());
            final Evenement event = le.get(i);
            b.addActionListener((ActionListener) (ActionEvent evt) -> {
                System.out.println("hello"+event.toString());
              if (b.getUIID().equals(event.getNomEvenement())) {
                  Form  f2 = new Form();
                  PartService p = new PartService();
                  ArrayList<particEv> les=p.affichpar(event.getId());
                  int len = les.size();
                  System.out.println(len);
                  try {
                      EncodedImage enc2 ;
                      enc2 = EncodedImage.create("/giphy.gif");
                      Label ss2 =new Label(event.getNomImg());
                      Image img2 = URLImage.createToStorage(enc2, "imagea"+event.getNomImg(), url+ss2.getText());
                      ImageViewer imgv2;
                      imgv2 = new ImageViewer(img2);
                      l2 = new SpanLabel(" "+event.getDateDeb()+"  "+event.getDateFin() +"\n " +event.getDescription()
                      );             Font   existingFont = l2.getUnselectedStyle().getFont();
                      Font newFont = Font.createSystemFont(existingFont.getFace(), Font.STYLE_ITALIC, Font.SIZE_SMALL);
                      l2.getUnselectedStyle().setFont(newFont);
                      Container c2;
                      c2=new Container(new FlowLayout(CENTER));
                      c2.add(imgv2);
                      c2.add(l2);
                      RadioButton cB = new RadioButton("interesé(e)");
                      RadioButton cB2 = new  RadioButton("nest pas interesé(e)");
                      RadioButton cB3 = new  RadioButton("participer");
                      c2.add(cB);
                      c2.add(cB2);
                      c2.add(cB3);
                      f2.add(c2);
                      if (len==0) {
                          cB.addActionListener((ActionListener) (ActionEvent evt1) -> {
                              cB3.setSelected(false);
                              cB2.setSelected(false);
                              try {
                                  db.execute("insert into participation (type,ide,idU) values ('"+cB.getText()+"','"+event.getId()+"',20);");
                                  System.out.println("ok");
                              } catch (IOException ex) {
                              }        PartService p1 = new PartService();
                              particEv v = new particEv(event.getId(),"interesé(e)");
                              p1.ajoutPar(v);
                          });
                          cB2.addActionListener((ActionListener) (ActionEvent evt1) -> {
                              cB.setSelected(false);
                              cB3.setSelected(false);
                              try {
                                  db.execute("insert into participation (type,ide,idU) values ('"+cB2.getText()+"','"+event.getId()+"',20);");
                                  System.out.println("ok");
                              } catch (IOException ex) {
                              }        PartService p1 = new PartService();
                              particEv v = new particEv(event.getId(),"nest pas interesé(e)");
                              p1.ajoutPar(v);
                          });
                          cB3.addActionListener((ActionListener) (ActionEvent evt1) -> {
                              cB.setSelected(false);
                              cB2.setSelected(false);
                              try {
                                  db.execute("insert into participation (type,ide,idU) values ('"+cB3.getText()+"','"+event.getId()+"',20);");
                                  System.out.println("ok");
                              } catch (IOException ex) {
                              }        PartService p1 = new PartService();
                              particEv v = new particEv(event.getId(),"participer");
                              p1.ajoutPar(v);
                          });  } else {
                          PartService p2 = new PartService();
                          ArrayList<particEv> les2=p.affichpar(event.getId());
                          for (int i1 = 0; i1 < les2.size(); i1++) {
                              if (les.get(i1).getType().equals(cB.getText())) {
                                  cB.setSelected(true);
                                  cB2.setSelected(false);
                                  cB3.setSelected(false);
                                  cB.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                      cB.setSelected(true);
                                      cB2.setSelected(false);
                                      cB3.setSelected(false);
                                      PartService p3 = new PartService();
                                      particEv v1 = new particEv(event.getId(),cB.getText());
                                      p3.modifPar(v1);
                                  });
                                  cB2.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                      cB.setSelected(false);
                                      cB2.setSelected(true);
                                      cB3.setSelected(false);
                                      PartService p3 = new PartService();
                                      particEv v1 = new particEv(event.getId(),cB2.getText());
                                      p3.modifPar(v1);
                                  });
                                  cB3.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                      cB.setSelected(true);
                                      cB2.setSelected(true);
                                      cB3.setSelected(false);
                                      PartService p3 = new PartService();
                                      particEv v1 = new particEv(event.getId(),cB3.getText());
                                      p3.modifPar(v1);
                                  });    }
                              if (les.get(i1).getType().equals(cB2.getText())) {
                                  cB.setSelected(false);
                                  cB2.setSelected(true);
                                  cB3.setSelected(false);
                                  cB.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                      cB.setSelected(true);
                                      cB2.setSelected(false);
                                      cB3.setSelected(false);
                                      PartService p3 = new PartService();
                                      particEv v1 = new particEv(event.getId(),cB.getText());
                                      p3.modifPar(v1);
                                  });
                                  cB2.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                      cB.setSelected(false);
                                      cB2.setSelected(true);
                                      cB3.setSelected(false);
                                      PartService p3 = new PartService();
                                      particEv v1 = new particEv(event.getId(),cB2.getText());
                                      p3.modifPar(v1);
                                  });
                                  cB3.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                      cB.setSelected(true);
                                      cB2.setSelected(true);
                                      cB3.setSelected(false);
                                      PartService p3 = new PartService();
                                      particEv v1 = new particEv(event.getId(),cB3.getText());
                                      p3.modifPar(v1);
                                  });    }
                              if (les.get(i1).getType().equals(cB3.getText())) {
                                  cB.setSelected(false);
                                  cB2.setSelected(false);
                                  cB3.setSelected(true);
                                  cB.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                      cB.setSelected(true);
                                      cB2.setSelected(false);
                                      cB3.setSelected(false);
                                      PartService p3 = new PartService();
                                      particEv v1 = new particEv(event.getId(),cB.getText());
                                      p3.modifPar(v1);
                                  });
                                  cB2.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                      cB.setSelected(false);
                                      cB2.setSelected(true);
                                      cB3.setSelected(false);
                                      PartService p3 = new PartService();
                                      particEv v1 = new particEv(event.getId(),cB2.getText());
                                      p3.modifPar(v1);
                                  });
                                  cB3.addActionListener((ActionListener) (ActionEvent evt1) -> {
                                      cB.setSelected(false);
                                      cB2.setSelected(false);
                                      cB3.setSelected(true);
                                      PartService p3 = new PartService();
                                      particEv v1 = new particEv(event.getId(),cB3.getText());
                                      p3.modifPar(v1);
                                  });    }
                              System.out.println(les.get(i1).getType());
                          }
                      }
                      Button sup = new Button();
                      sup.addActionListener((ActionListener) (ActionEvent evt1) -> {
                          try {
                              db.execute( "drop table participation ;");
                          } catch (IOException ex) {
                          }
                      });
                      f2.add(sup);
                      TextField com = new TextField();
                      Button commenter = new Button();
                      f2. add(com);
                      f2.add(commenter);
                      commenter.addActionListener((ActionListener) (ActionEvent evt1) -> {
                      });
                      f2.show();
                      f2.getToolbar().addCommandToLeftBar("back", null, (ev)->{
         
          f.show();
          });     }catch (IOException ex) {
                               }
              }
          } 
          );
     
          f.getToolbar().addCommandToLeftBar("back", null, (ev)->{
              AfterCnx AfCnx = new AfterCnx();
                AfCnx.getF().show();
          });
     }}
      public Form getF() 
      {
        return f;
      }

    public void setF(Form f)
    {
        this.f = f;
    }
        }
