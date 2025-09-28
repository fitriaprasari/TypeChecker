package org.example.typchecker.ast;

import org.example.typchecker.semantic.Type;

public class BinaryExpr implements Expr {
    public enum Op { ADD, SUB, MUL, DIV, AND, OR, EQ, NEQ, LT, LE, GT, GE }

    public final Op op;
    public final Expr left;
    public final Expr right;

    public BinaryExpr(Op op, Expr left, Expr right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public Type accept(org.example.typchecker.semantic.TypeChecker checker) {
        return checker.visit(this);
    }
}