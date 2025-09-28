package org.example.typchecker.ast;
import org.example.typchecker.semantic.Type;

public class VarDecl implements Stmt {
    public final String name;
    public final Type declaredType;
    public final Expr init; // may be null

    public VarDecl(String name, Type declaredType, Expr init) {
        this.name = name;
        this.declaredType = declaredType;
        this.init = init;
    }

    @Override
    public void accept(org.example.typchecker.semantic.TypeChecker checker) {
        checker.visit(this);
    }
}