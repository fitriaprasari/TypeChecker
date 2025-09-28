package org.example.typchecker.ast;

import java.util.List;

public class Program implements ASTNode {
    public final List<Stmt> statements;
    public Program(List<Stmt> statements) {
        this.statements = statements;
    }
}