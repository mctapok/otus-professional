public class Task {
    private int id;
    private String name;
    private String status;


    public Task(String name, String status, int id){
        this.name = name;
        this.id = id;
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
