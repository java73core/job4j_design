package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    public boolean isBinary() {
        return findByPredicate(node -> node.children.size() > 2).isEmpty();
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = findBy(parent).isPresent() && findBy(child).isEmpty();
        if (rsl) {
                findBy(parent).get().children.add(new Node<>(child));
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(node -> node.value.equals(value));
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