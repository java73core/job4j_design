package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenRolenameIsPetrarh() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petrarh"));
        Role result = store.findById("1");
        assertThat(result.getUsername(), is("Petrarh"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petrarh"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRolenameIsPetrarh() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petrarh"));
        store.add(new Role("1", "Maximarh"));
        Role result = store.findById("1");
        assertThat(result.getUsername(), is("Petrarh"));
    }

    @Test
    public void whenReplaceThenRolenameIsMaximarh() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petrarh"));
        store.replace("1", new Role("1", "Maximarh"));
        Role result = store.findById("1");
        assertThat(result.getUsername(), is("Maximarh"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petrarh"));
        store.replace("10", new Role("10", "Maximarh"));
        Role result = store.findById("1");
        assertThat(result.getUsername(), is("Petrarh"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petrarh"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRolenameIsPetrarh() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petrarh"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getUsername(), is("Petrarh"));
    }
}