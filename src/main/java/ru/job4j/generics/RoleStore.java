package ru.job4j.generics;

public class RoleStore implements Store<Role> {

    private final Store<Role> store = new MemStore<>();

    @Override
    public void add(Role model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        if (store.findById(id) != null) {
            store.replace(id, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (store.findById(id) != null) {
            store.delete(id);
            return true;
        }
        return false;
    }

    @Override
    public Role findById(String id) {
        return store.findById(id);
    }
}