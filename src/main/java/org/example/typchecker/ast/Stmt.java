package org.example.typchecker.ast;

import org.example.typchecker.ast.ASTNode;

public interface Stmt extends ASTNode {
    void accept(org.example.typchecker.semantic.TypeChecker checker);
}