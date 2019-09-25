package az.pashabank.ht.clients.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataModel {
    private static Map<Integer, ClientDTO> map = new HashMap<>();
    private static int lastId;

    public DataModel() {
    }

    public Map<Integer, ClientDTO> getMap() {
        return map;
    }

    public void setMap(Map<Integer, ClientDTO> map) {
        this.map = map;
    }

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }
}
