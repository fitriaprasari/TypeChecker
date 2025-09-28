package org.example.typchecker.ast;
import org.example.typchecker.semantic.Type;

public interface Expr extends ASTNode {
    Type accept(org.example.typchecker.semantic.TypeChecker checker);
}