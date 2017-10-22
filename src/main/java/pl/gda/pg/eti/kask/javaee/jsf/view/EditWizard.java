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
public class EditWizard implements Serializable {

    @ManagedProperty("#{wizardService}")
    private WizardService wizardService;

    @Getter
    @Setter
    private int wizardId;

    @Getter
    @Setter
    private Wizard wizard;
    
    private List<SelectItem> towersAsSelectItems;

    private List<SelectItem> magicElementsAsSelectItems;

    public void setWizardService(WizardService wizardService) {
        this.wizardService = wizardService;
    }
    
    public List<SelectItem> getTowersAsSelectItems() {
        if (towersAsSelectItems == null) {
            towersAsSelectItems = new ArrayList<>();
            for (Tower tower : wizardService.findAllTowers()) {
                towersAsSelectItems.add(new SelectItem(tower, "Wieża " + tower.getHeight() + " m"));
            }
        }
        return towersAsSelectItems;
    }

    public List<SelectItem> getMagicElementsAsSelectItems() {
        if (magicElementsAsSelectItems == null) {
            magicElementsAsSelectItems = new ArrayList<>();
            for (MagicElement magicElement : wizardService.findAllMagicElements()) {
                magicElementsAsSelectItems.add(new SelectItem(magicElement, magicElement.name));
            }
        }
        return magicElementsAsSelectItems;
    }

    public void init() {
        if (wizard == null && wizardId != 0) {
            wizard = wizardService.findWizard(wizardId);
        } else if (wizard == null && wizardId == 0) {
            wizard = new Wizard();
        }/**/
        if (wizard == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }

    public String saveWizard() {
        wizardService.saveWizard(wizard);
        return "list_wizards?faces-redirect=true";
    }
}
