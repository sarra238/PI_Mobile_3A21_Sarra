/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiAnnonce;

import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;


/**
 *
 * @author Win10
 */
public class Map {
    Form hi;
    public Map(){
//        hi= new Form("Map", new BorderLayout());
//        Toolbar tb = new Toolbar(true);
//        hi.setToolbar(tb);
//        hi.add(BorderLayout.CENTER, new MapContainer("***IMPORTANT: SET JS KEY HERE****"));
//        
//        Label title = new Label("Map", "Title");
//        ButtonGroup bg = new ButtonGroup();
//        
//        RadioButton modeA = RadioButton.createToggle("Mode A", bg);
//        RadioButton modeB = RadioButton.createToggle("Mode B", bg);
//        RadioButton modeC = RadioButton.createToggle("Mode C", bg);
//        modeA.setUIID("SmallTitle");
//        modeB.setUIID("SmallTitle");
//        modeC.setUIID("SmallTitle");
//        modeA.setSelected(true);
//        Container radioGrid = GridLayout.encloseIn(3, modeA, modeB, modeC);
//
//        Label whiteLine = new Label();
//        whiteLine.setShowEvenIfBlank(true);
//        whiteLine.getUnselectedStyle().setBgColor(0xffffff);
//        whiteLine.getUnselectedStyle().setBgTransparency(255);
//        whiteLine.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_DIPS);
//        whiteLine.getUnselectedStyle().setPadding(1, 0, 1, 1);
//        Container lineGrid = GridLayout.encloseIn(3, whiteLine, new Label(), new Label());
//        bg.addActionListener(e -> {
//            int offset = radioGrid.getComponentIndex(e.getComponent());
//            whiteLine.remove();
//            lineGrid.addComponent(offset, whiteLine);
//            lineGrid.animateLayout(150);
//        });
//        
//        
//        Container titleArea = BoxLayout.encloseY(title, radioGrid, lineGrid);
//        tb.setTitleComponent(titleArea);
}

    public Form getHi() {
        return hi;
    }

    public void setHi(Form hi) {
        this.hi = hi;
    }
    
    
}
