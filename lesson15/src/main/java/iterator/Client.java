package iterator;

public class Client {
    public static void main(String[] args) {
        Box box = new Box();
        print(box.createIterator());
    }
    public static void print(MyIterator<String> it){
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
