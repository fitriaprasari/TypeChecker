package org.example.typchecker.ast;
import org.example.typchecker.semantic.Type;

public class BoolLiteral implements Expr {
    public final boolean value;
    public BoolLiteral(boolean value) { this.value = value; }

    @Override
    public Type accept(org.example.typchecker.semantic.TypeChecker checker) {
        return checker.visit(this);
    }
}