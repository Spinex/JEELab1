package pl.gda.pg.eti.kask.javaee.jsf.view;

import pl.gda.pg.eti.kask.javaee.jsf.WizardService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import pl.gda.pg.eti.kask.javaee.jsf.entities.Tower;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Wizard;

/**
 *
 * @author psysiu
 */
@ViewScoped
@ManagedBean
public class ListWizards implements Serializable {

    @ManagedProperty("#{wizardService}")
    private WizardService wizardService;

    public void setWizardService(WizardService wizardService) {
        this.wizardService = wizardService;
    }
    private List<Wizard> wizards;
    private List<Tower> towers;

    public List<Tower> getTowers() {
        if (towers == null) {
            towers = wizardService.findAllTowers();
        }
        return towers;
    }

    public List<Wizard> getWizards() {
        if (wizards == null) {
            wizards = wizardService.findAllWizards();
        }
        return wizards;
    }

    public void removeWizard(Wizard wizard) {
        wizardService.removeWizard(wizard);
        wizards.remove(wizard);
    }

    public void removeTower(Tower tower) {
        wizardService.removeTower(tower);
        towers.remove(tower);
    }

    public List<Wizard> getWizardsByTower(final Tower tower) {
        getWizards();
        return this.wizards.stream().filter( d -> d.getTower().getId() == tower.getId() ).collect(Collectors.toList());
    }
}
