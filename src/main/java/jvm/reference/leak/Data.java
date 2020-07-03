package jvm.reference.leak;

public class Data {
    private int id =0;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "data："+id;
    }
}
