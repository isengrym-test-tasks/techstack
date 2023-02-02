package ua.klieshchunov.repository;

import ua.klieshchunov.model.entity.Entity;

import java.util.List;

public class StorageUtils {
    public static int getAutoId(List<? extends Entity> storage) {
        int lastIndex = storage.get(getDbLastIndex(storage)).getId();
        return ++lastIndex;
    }

    public static int getStorageIndex(List<? extends Entity> storage, Entity entity) {
        return storage.indexOf(entity);
    }


    private static int getDbLastIndex(List<? extends Entity> storage) {
        return storage.size()-1;
    }
}
