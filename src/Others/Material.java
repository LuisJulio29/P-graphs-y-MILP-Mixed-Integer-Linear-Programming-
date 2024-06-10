/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Others;

/**
 *
 * @author Duvan Bustos
 */


public class Material {
    private String name;
    private String type;
    private int flowRateLowerBound;

    public Material(String name, String type) {
        this.name = name;
        this.type = type;
        this.flowRateLowerBound = 0;
    }

    public Material(String name, String type, int flowRateLowerBound) {
        this.name = name;
        this.type = type;
        this.flowRateLowerBound = flowRateLowerBound;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getFlowRateLowerBound() {
        return flowRateLowerBound;
    }

    @Override
    public String toString() {
        return "Material{name='" + name + "', type='" + type + "', flowRateLowerBound=" + flowRateLowerBound + '}';
    }
}
