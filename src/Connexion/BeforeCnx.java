/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import GuiAnnonce.AnalogClock;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

public class BeforeCnx {
    private Form f0;
    private final Resources theme;
    private final Container c;
    EncodedImage enc;

    public BeforeCnx() {
        theme = UIManager.initFirstTheme("/theme");
        f0=new Form("Acceuil",new FlowLayout(Component.CENTER,Component.CENTER));
        try {
            enc = EncodedImage.create("/giphy.gif").scaledEncoded(300,500);
        }
        catch (IOException ex) {
            System.out.println("error encoder");
        }
//         Container hi = new Container();
//        AnalogClock clock = new AnalogClock();
//        hi.add(clock);
//        clock.start();
        String urll="http://localhost/SoukI/web/Home/images/4.jpg";
        Image img = URLImage.createToStorage(enc, urll, urll, URLImage.RESIZE_SCALE);
        ImageViewer imgv=new ImageViewer();
        imgv.setImage(img);
        c=new Container(BoxLayout.y());
        c.add(imgv);
//        f0.add(hi);
        f0.add(c);
       
        
        f0.show();
         Toolbar tb1= f0.getToolbar();
           tb1.addCommandToOverflowMenu("Connexion",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
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
