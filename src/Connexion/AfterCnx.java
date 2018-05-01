/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import GuiAnnonce.AffichAnnAllClt;
import GuiEvenement.ListEvent;
import GuiProduit.AffichProdClt;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Component;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author Win10
 */
public class AfterCnx {
    private Form f0;
    private Resources theme;
    EncodedImage enc;

    public AfterCnx() {
        theme = UIManager.initFirstTheme("/theme");
        f0=new Form("Acceuil",new FlowLayout(Component.CENTER,Component.CENTER));
        try {
            enc = EncodedImage.create("/giphy.gif").scaledEncoded(300,500);
        }
        catch (IOException ex) {
            System.out.println("error encoder");
        }
        String urll="http://localhost/SoukI/web/Home/images/4.jpg";
        Image img = URLImage.createToStorage(enc, urll, urll, URLImage.RESIZE_SCALE);
        ImageViewer imgv=new ImageViewer();
        imgv.setImage(img);
        f0.add(imgv);
        Form f4=new Form(new FlowLayout(Component.CENTER,Component.CENTER));
        Label l4=new Label("Restaurants");
        f4.add(l4);
        Form f5=new Form(new FlowLayout(Component.CENTER,Component.CENTER));
        Label l5=new Label("Service Aprés Vente");
        f5.add(l5);
        Toolbar tb=f0.getToolbar();
        tb.addMaterialCommandToSideMenu("Acceuil",FontImage.MATERIAL_WEB, (ActionListener) (ActionEvent evt) -> {
            f0.show(); 
        });
        tb.addMaterialCommandToSideMenu("Produits",FontImage.MATERIAL_WEB, (ActionListener) (ActionEvent evt) -> {
            AffichProdClt pr=new AffichProdClt();
            pr.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Annonces",FontImage.MATERIAL_WEB, (ActionListener) (ActionEvent evt) -> {
            AffichAnnAllClt affAnn = new AffichAnnAllClt();
            affAnn.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Evénements",FontImage.MATERIAL_WEB, (ActionListener) (ActionEvent evt) -> {
            ListEvent ev=new ListEvent();
            ev.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Restaurants",FontImage.MATERIAL_WEB, (ActionListener) (ActionEvent evt) -> {
            Toolbar tb1= f4.getToolbar();
            tb1.addCommandToLeftBar("Retour",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                f0.show();
            });
            f4.show(); 
        });
        tb.addMaterialCommandToSideMenu("Service Aprés Vente",FontImage.MATERIAL_WEB, (ActionListener) (ActionEvent evt) -> {
            Toolbar tb1= f5.getToolbar();
            tb1.addCommandToLeftBar("Retour",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                f0.show(); 
            });
            f5.show(); 
        });
        tb.addCommandToOverflowMenu("Déconnexion",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                cnx c2 =new cnx();
                c2.getF().show();
        });
        
    }

    public Form getF() {
        return f0;
    }

    public void setF(Form f0) {
        this.f0 = f0;
    }
    
    
}
