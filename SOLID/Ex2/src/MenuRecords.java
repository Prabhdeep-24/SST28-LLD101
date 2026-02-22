import java.util.LinkedHashMap;
import java.util.Map;

public class MenuRecords implements MenuRepository {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    
    public void add(MenuItem item){
        menu.put(item.id, item);
    }

    public MenuItem getItem(String itemId){
        return menu.get(itemId);
    }
}