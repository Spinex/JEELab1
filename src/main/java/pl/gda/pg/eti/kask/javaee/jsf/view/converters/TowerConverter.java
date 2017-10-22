package pl.gda.pg.eti.kask.javaee.jsf.view.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Tower;
import pl.gda.pg.eti.kask.javaee.jsf.WizardService;

/**
 *
 * @author psysiu
 */
@ManagedBean
@RequestScoped
public class TowerConverter implements Converter {

    @ManagedProperty("#{wizardService}")
    private WizardService wizardService;

    public void setWizardService(WizardService wizardService) {
        this.wizardService = wizardService;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if ("---".equals(value)) {
            return null;
        }
        return wizardService.findTower(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "---";
        }
        return ((Tower) value).getId() + "";
    }
}
