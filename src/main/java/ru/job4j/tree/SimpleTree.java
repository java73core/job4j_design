package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    public boolean isBinary() {
        Predicate<Node<E>> predicate = node -> !node.children.isEmpty()
                && node.children.size() > 2;
        Optional<Node<E>> rsl = findByPredicate(predicate);
        return rsl.isEmpty();
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> findParent = findBy(parent);
        Optional<Node<E>> findChildren = findBy(child);
        if (findParent.isPresent()) {
            if (findChildren.isEmpty()) {
                Node<E> newChild = new Node<>(child);
                findParent.get().children.add(newChild);
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl;
        Predicate<Node<E>> predicate = node -> node.value.equals(value);
        rsl = findByPredicate(predicate);
        return rsl;
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition)  {
        Optional<Node<E>> rsl = Optional.empty();
        List<Node<E>> list = new LinkedList<>();
        list.add(root);
        Node<E> node;
        while (!list.isEmpty()) {
            node = list.remove(0);
            if (condition.test(node)) {
                rsl = Optional.of(node);
                break;
            }
            list.addAll(node.children);
        }
        return rsl;
    }
}