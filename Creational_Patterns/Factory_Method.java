import java.util.ArrayList;

public class Factory_Method {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        Pizza nyStylePizza = nyStore.orderPizza("cheese");
        System.out.println(nyStylePizza.getName());
        System.out.println("-------------------------------------------------");

        PizzaStore chicagoStore = new ChicagoPizzaStore();
        Pizza chicagoStylePizza = chicagoStore.orderPizza("cheese");
        System.out.println(chicagoStylePizza.getName());
    }
}

// Creator Class
abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
    abstract Pizza createPizza(String type);
}

// ConcreteCreate Class 1 - use ConcreteProduct Class 
class NYPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")) {
            pizza = new NYStyleCheesePizza();
        }
//        if (type.equals("pepper")) {
//            pizza = new NYStylePepperoniPizza();
//        }
//        if (type.equals("clam")) {
//            pizza = new NYStyleClamPizza();
//        }
//        if (type.equals("veggie")) {
//            pizza = new NYStyleVeggiePizza();
//        }
        return pizza;
    }
}

// ConcreteCreate Class 2
class ChicagoPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")) {
            pizza = new ChicagoStyleCheesePizza();
        }
//        if (type.equals("pepper")) {
//            pizza = new ChicagoStylePepperoniPizza();
//        }
//        if (type.equals("clam")) {
//            pizza = new ChicagoStyleClamPizza();
//        }
//        if (type.equals("veggie")) {
//            pizza = new ChicagoStyleVeggiePizza();
//        }
        return pizza;
    }
}

// Product Class
abstract class Pizza {
    String name;
    String dough;
    String sauce;
    ArrayList<String> toppings = new ArrayList<>();

    void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough... ");
        System.out.println("Adding sauce... ");
        System.out.println("Adding toppings: ");
        for (int i = 0; i < toppings.size(); i++) {
            System.out.println("     " + toppings.get(i));
        }
    }

    void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    void cut() {
        System.out.println("Cutting the pizza into diagonal slice");
    }

    void box() {
        System.out.println("Place pizza in official PizzaStroe box");
    }

    public String getName() {
        return this.name;
    }
}

// ConcreteProduct Class 1
class NYStyleCheesePizza extends Pizza {
    public NYStyleCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";
        toppings.add("Grated Reggiano Cheese");
    }
}

// ConcreteProduct Class 2
class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Deep Dish Cheese Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";
        toppings.add("Shredded Mozzarella Cheese");
    }

    @Override
    void cut() {
        System.out.println("Cutting the pizza into sqaare slices");
    }
}
