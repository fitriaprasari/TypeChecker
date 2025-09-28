package org.example.typchecker.ast;
import org.example.typchecker.semantic.Type;

public class IntLiteral implements Expr {
    public final int value;
    public IntLiteral(int value) { this.value = value; }

    @Override
    public Type accept(org.example.typchecker.semantic.TypeChecker checker) {
        return checker.visit(this);
    }
}