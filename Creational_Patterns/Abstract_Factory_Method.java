import java.util.ArrayList;

public class Abstract_Factory_Method {
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

// client 1
class NYPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        // change ingredient
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
        if(type.equals("cheese")){
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("New York Style Cheese Pizza");
        } 
        return pizza;
    }
 } 

// client 2
class ChicagoPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();
        if(type.equals("cheese")){
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("Chicago Style Cheese Pizza");
        } 
        return pizza;
    }
}

abstract class Pizza {
    String name;
    Dough dough;
    Sauce sauce;
    ArrayList<String> toppings = new ArrayList<>();

    abstract public void prepare(); // delegate to sub class <-> factory method

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

    public void setName(String name) {
        this.name = name;
    }
}

class CheesePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;
    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }
    @Override
    public void prepare() {
        this.dough = ingredientFactory.createDough();
        this.sauce = ingredientFactory.createSauce();
    }
    public PizzaIngredientFactory getIngredientFactory() {
        return ingredientFactory;
    }
    public void setIngredientFactory(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }
}

// AbstractFactory class
interface PizzaIngredientFactory {
   public Dough createDough();
   public Sauce createSauce();
}
 
// ConcreteFactory class 1
class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinCrustDough(); // use ConcreteProduct class
    }
    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }
}

// ConcreteFactory class 2
class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }
    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }
}

// AbstractProduct Class 1
interface Dough {
    public String toString();
}

// ConcreteProduct Class 1-1
class ThinCrustDough implements Dough {
	public String toString() {
		return "Thin Crust Dough";
	}
}

// ConcreteProduct Class 1-2
class ThickCrustDough implements Dough {
	public String toString() {
		return "ThickCrust style extra thick crust dough";
	}
}

// AbstractProduct Class 2
interface Sauce {
	public String toString();
}

// ConcreteProduct Class 2-1
class MarinaraSauce implements Sauce {
	public String toString() {
		return "Marinara Sauce";
	}
}

// ConcreteProduct Class 2-2
class PlumTomatoSauce implements Sauce {
	public String toString() {
		return "Tomato sauce with plum tomatoes";
	}
}
 

 
