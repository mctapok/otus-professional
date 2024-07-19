package Builder;

public final class Product {
    private final int id;
    private final String title;
    private final String description;
    private final double cost;
    private final double weight;
    private final double height;
    private final double width;
    private final double length;

    public interface productBuilder{
        productBuilder id(int id);
        productBuilder title(String title);
        productBuilder description(String description);
        productBuilder cost(double cost);
        productBuilder weight(double weight);
        productBuilder height(double height);
        productBuilder width(double width);
        productBuilder length(double length);
    }

    private Product(int id, String title, String description, double cost, double weight, double height, double width, double length) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public static final class PepsiProductBuilder implements productBuilder{
        private int id;
        private String title;
        private String description;
        private double cost;
        private double weight;
        private double height;
        private double width;
        private double length;

        public Product build() {
            return new Product(id, title, description, cost, weight, height, width, length);
        }

        public PepsiProductBuilder id(int id) {this.id = id; return this;}
        public PepsiProductBuilder title(String title) {this.title = title; return this;}
        public PepsiProductBuilder description(String description) {this.description = description; return this;}
        public PepsiProductBuilder cost(double cost) {this.cost = cost; return this;}
        public PepsiProductBuilder weight(double weight) {this.weight = weight; return this;}
        public PepsiProductBuilder height(double height) {this.height = height; return this;}
        public PepsiProductBuilder width(double width) {this.width = width; return this;}
        public PepsiProductBuilder length(double length) {this.length = length; return this;}
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", weight=" + weight +
                ", height=" + height +
                ", width=" + width +
                ", length=" + length +
                '}';
    }
}
