package hospital;

public interface Manageable {
    boolean remove(Object o);
    void add(Object o);
    //Object find(String searchKey, String searchQuery);
    void updateData();
}
