/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connexion;

import Entities.User;
import Services.UserServices;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

public class cnx {
    private Form f;
    private final Container c;
    private TextField t1;
    private final TextField t2;
    private final Button b;
    private final Resources theme;
    
    public cnx() {
        theme = UIManager.initFirstTheme("/theme");
        f  = new Form("Souk El Medina!",new FlowLayout(Component.CENTER,Component.CENTER));
        c  = new Container(BoxLayout.y());
        t1 = new TextField("","Login",15,0);
        t2 = new TextField("","Password",15,TextField.PASSWORD);
        b  = new Button("Connect");
        b.addActionListener((ActionListener) (ActionEvent evt) -> {
          UserServices s =new UserServices();
         ArrayList<User> le = s.getList2(t1.getText());
            if(!le.isEmpty()){
                AfterCnx After=new AfterCnx();
                After.getF().show();
            }
            else
            {
                Dialog.show("Erreur", "Les informations saisies ne sont pas correctes", "Ok", "Cancel");
            }
        });
        c.add(t1);
        c.add(t2);
        c.add(b);
        f.add(c);
        f.show();
        Toolbar tb1= f.getToolbar();
        tb1.addCommandToLeftBar("back",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
               BeforeCnx Bcnx = new BeforeCnx();
               Bcnx.getF().show(); 
        });
        }
    
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
