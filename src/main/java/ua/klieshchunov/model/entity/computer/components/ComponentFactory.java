package ua.klieshchunov.model.entity.computer.components;

public class ComponentFactory {
    public static Component getComponent(int id, String specs, Class<? extends Component> componentClass) {
        Component component;
        try {
            component = componentClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        component.setId(id);
        component.setQuantityInStock(50);
        component.setPriceUsd(700);
        component.setBrand("Some brand");
        component.setSpecs(specs);

        return component;
    }
}
