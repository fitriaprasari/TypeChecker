package org.example.typchecker.ast;
import org.example.typchecker.semantic.Type;

public class VarRef implements Expr {
    public final String name;
    public VarRef(String name) { this.name = name; }

    @Override
    public Type accept(org.example.typchecker.semantic.TypeChecker checker) {
        return checker.visit(this);
    }
}