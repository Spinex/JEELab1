package pl.gda.pg.eti.kask.javaee.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import pl.gda.pg.eti.kask.javaee.jsf.entities.MagicElement;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Tower;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Wizard;

/**
 *
 * @author psysiu
 */

@ManagedBean
@ApplicationScoped
public class WizardService implements Serializable {

    private final SortedMap<Integer, Wizard> wizards;
    
    private final SortedMap<Integer, Tower> towers;

    public WizardService() {
        Tower a1 = new Tower(1, 57);
        Tower a2 = new Tower(2, 38);
        Wizard b1 = new Wizard(1, "Xardas", 80, MagicElement.WATER, a1);
        Wizard b2 = new Wizard(2, "Corristo", 120, MagicElement.FIRE, a2);
        wizards = new TreeMap<>();
        wizards.put(b1.getId(), b1);
        wizards.put(b2.getId(), b2);
        towers = new TreeMap<>();
        towers.put(a1.getId(), a1);
        towers.put(a2.getId(), a2);
    }
    
    private List<Tower> asList(Tower... towers) {
        List<Tower> list = new ArrayList<>();
        list.addAll(Arrays.asList(towers));
        return list;
    }

    public List<Wizard> findAllWizards() {
        return new ArrayList<>(wizards.values());
    }

    public Wizard findWizard(int id) {
        return wizards.get(id);
    }

    public void removeWizard(Wizard wizard) {
        wizards.remove(wizard.getId());
    }

    public void removeTower(Tower tower) {
        List<Integer> idsToDelete = wizards.keySet().stream().filter( d -> d == tower.getId() ).collect(Collectors.toList());
        wizards.keySet().removeAll(idsToDelete);
        towers.remove(tower.getId());
    }

    public void saveWizard(Wizard wizard) {
        if (wizard.getId() == 0) {
            wizard.setId(wizards.lastKey() + 1);
        }
        wizards.put(wizard.getId(), wizard);
    }

    public void saveTower(Tower tower) {
        if (tower.getId() == 0) {
            tower.setId(towers.lastKey() + 1);
        }
        towers.put(tower.getId(), tower);
    }
    
    public List<Tower> findAllTowers() {
        return new ArrayList<>(towers.values());
    }
    
    public Tower findTower(int id) {
        return towers.get(id);
    }

    public MagicElement[] findAllMagicElements() {
        return new MagicElement[]{MagicElement.FIRE, MagicElement.WATER, MagicElement.WIND, MagicElement.EARTH};
    }
}
