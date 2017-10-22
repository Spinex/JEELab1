package pl.gda.pg.eti.kask.javaee.jsf.view;

import pl.gda.pg.eti.kask.javaee.jsf.WizardService;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Wizard;

/**
 *
 * @author psysiu
 */
@ViewScoped
@ManagedBean
@Log
public class ViewWizard implements Serializable {

    @ManagedProperty("#{wizardService}")
    private WizardService wizardService;

    @Getter
    @Setter
    private int wizardId;

    @Getter
    @Setter
    private Wizard wizard;

    public void setWizardService(WizardService wizardService) {
        this.wizardService = wizardService;
    }
    
    public void init() {
        if (wizard == null) {
            wizard = wizardService.findWizard(wizardId);
        }
        if (wizard == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }

}
