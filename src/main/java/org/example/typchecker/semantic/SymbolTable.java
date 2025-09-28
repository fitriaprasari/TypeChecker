package org.example.typchecker.semantic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SymbolTable {
    private final Map<String, Type> map = new HashMap<>();

    public void declare(String name, Type type) {
        map.put(name, type);
    }

    public Optional<Type> lookup(String name) {
        return Optional.ofNullable(map.get(name));
    }

    public boolean isDeclared(String name) {
        return map.containsKey(name);
    }
}