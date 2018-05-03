/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiAnnonce;

import com.codename1.googlemaps.MapContainer;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

public class Map {
    Form hi;
    private final  Resources theme;
    public Map(){
        theme = UIManager.initFirstTheme("/theme");
        hi= new Form("Map");
        hi.add(BorderLayout.CENTER, new MapContainer("***IMPORTANT: SET JS KEY HERE****"));
         Toolbar tb = hi.getToolbar();
          tb.addCommandToLeftBar("back",theme.getImage("back-command.png"), (ActionListener) (ActionEvent evt1) -> {
                AffichAnnAllClt AffichAnn=new AffichAnnAllClt();
                AffichAnn.getF().show();
        });
}

    public Form getHi() {
        return hi;
    }

    public void setHi(Form hi) {
        this.hi = hi;
    }
    
    
}
