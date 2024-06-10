/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Others;

/**
 *
 * @author Duvan Bustos
 */
import java.util.HashMap;
import java.util.Map;


public class OperatingUnit {
    private String name;
    private int capacityUpperBound;
    private int fixCost;
    private int proportionalCost;
    private Map<Material, Material> materialFlowRates;

    public OperatingUnit(String name, int capacityUpperBound, int fixCost, int proportionalCost) {
        this.name = name;
        this.capacityUpperBound = capacityUpperBound;
        this.fixCost = fixCost;
        this.proportionalCost = proportionalCost;
        this.materialFlowRates = new HashMap<>();
    }

    public void addMaterialFlowRate(Material input, Material output) {
        materialFlowRates.put(input, output);
    }

    public String getName() {
        return name;
    }

    public int getCapacityUpperBound() {
        return capacityUpperBound;
    }

    public int getFixCost() {
        return fixCost;
    }

    public int getProportionalCost() {
        return proportionalCost;
    }

    public Map<Material, Material> getMaterialFlowRates() {
        return materialFlowRates;
    }

    @Override
    public String toString() {
        return "OperatingUnit{name='" + name + "', capacityUpperBound=" + capacityUpperBound + 
               ", fixCost=" + fixCost + ", proportionalCost=" + proportionalCost + 
               ", materialFlowRates=" + materialFlowRates + '}';
    }
}
   

