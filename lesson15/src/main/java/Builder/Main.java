package Builder;

public class Main {
    public static void main(String[] args) {
        Product product = new Product.PepsiProductBuilder()
                .id(23)
                .cost(90.5)
                .description("Pepsi water")
                .title("Pepsi")
                .weight(0.5)
                .height(25.4)
                .width(4.5)
                .length(4.5)
                .build();
        System.out.println(product);
    }
}
