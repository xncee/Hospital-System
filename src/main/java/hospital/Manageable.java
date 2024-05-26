package hospital;

import java.util.List;

public interface Manageable {
    boolean remove(Object o);
    void add(Object o);
    void updateData();
    //List<Object> find(String searchKey, String searchQuery);
}
