package miniprojectswingit2;

public class CSBemployee {
    private String name;
    private boolean isAvailable;

    public CSBemployee(String name, boolean isAvailable) {
        this.name = name;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

