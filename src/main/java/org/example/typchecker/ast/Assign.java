package org.example.typchecker.ast;
public class Assign implements Stmt {
    public final String target;
    public final Expr value;

    public Assign(String target, Expr value) {
        this.target = target;
        this.value = value;
    }

    @Override
    public void accept(org.example.typchecker.semantic.TypeChecker checker) {
        checker.visit(this);
    }
}