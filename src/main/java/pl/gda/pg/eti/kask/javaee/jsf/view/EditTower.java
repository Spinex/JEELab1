package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.jsf.WizardService;
import pl.gda.pg.eti.kask.javaee.jsf.entities.MagicElement;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Tower;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Wizard;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author psysiu
 */
@ViewScoped
@ManagedBean
@Log
public class EditTower implements Serializable {

    @ManagedProperty("#{wizardService}")
    private WizardService wizardService;

    @Getter
    @Setter
    private int towerId;

    @Getter
    @Setter
    private Tower tower;
    
    public void setWizardService(WizardService wizardService) {
        this.wizardService = wizardService;
    }
    
    public void init() {
        if (tower == null && towerId != 0) {
            tower = wizardService.findTower(towerId);
        } else if (tower == null && towerId == 0) {
            tower = new Tower();
        }/**/
        if (tower == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }

    public String saveTower() {
        wizardService.saveTower(tower);
        return "list_wizards?faces-redirect=true";
    }
}
