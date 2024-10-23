package builder.productbuilder;

public final class Product {
    private final int id;
    private final String title;
    private final String description;
    private final double cost;
    private final double weight;
    private final double height;
    private final double width;
    private final double length;

    public static ProductBuilderImpl builder(){
        return new ProductBuilderImpl();
    }

    public interface ProductBuilder {
        ProductBuilder id(int id);
        ProductBuilder title(String title);
        ProductBuilder description(String description);
        ProductBuilder cost(double cost);
        ProductBuilder weight(double weight);
        ProductBuilder height(double height);
        ProductBuilder width(double width);
        ProductBuilder length(double length);
    }

    private Product(ProductBuilderImpl builder){
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.cost = builder.cost;
        this.weight = builder.weight;
        this.height = builder.height;
        this.width = builder.width;
        this.length = builder.length;
    }

    public static final class ProductBuilderImpl implements ProductBuilder {
        private int id;
        private String title;
        private String description;
        private double cost;
        private double weight;
        private double height;
        private double width;
        private double length;

        private ProductBuilderImpl(){}

        public Product build() {
            return new Product(this);
        }

        public ProductBuilderImpl id(int id) {this.id = id; return this;}
        public ProductBuilderImpl title(String title) {this.title = title; return this;}
        public ProductBuilderImpl description(String description) {this.description = description; return this;}
        public ProductBuilderImpl cost(double cost) {this.cost = cost; return this;}
        public ProductBuilderImpl weight(double weight) {this.weight = weight; return this;}
        public ProductBuilderImpl height(double height) {this.height = height; return this;}
        public ProductBuilderImpl width(double width) {this.width = width; return this;}
        public ProductBuilderImpl length(double length) {this.length = length; return this;}
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "builder.productbuilder.Product{" +
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
