package org.example.typchecker.semantic;
import org.example.typchecker.ast.*;

public class TypeChecker {
    private final SymbolTable sym = new SymbolTable();

    public void check(Program p) {
        for (Stmt s : p.statements) {
            s.accept(this);
        }
    }

    /* Statements */
    public void visit(VarDecl d) {
        if (sym.isDeclared(d.name)) {
            throw new TypeError("Variable already declared: " + d.name);
        }
        if (d.init != null) {
            Type initType = d.init.accept(this);
            if (initType == Type.ERROR) {
                sym.declare(d.name, d.declaredType); // still declare to avoid cascades
                return;
            }
            if (initType != d.declaredType) {
                throw new TypeError("Type mismatch in initialization of " + d.name
                        + ": declared " + d.declaredType + " but got " + initType);
            }
        }
        sym.declare(d.name, d.declaredType);
    }

    public void visit(Assign a) {
        if (!sym.isDeclared(a.target)) {
            throw new TypeError("Assignment to undeclared variable: " + a.target);
        }
        Type leftType = sym.lookup(a.target).orElse(Type.ERROR);
        Type valType = a.value.accept(this);
        if (valType == Type.ERROR) return;
        if (leftType != valType) {
            throw new TypeError("Type mismatch in assignment to " + a.target
                    + ": expected " + leftType + " but got " + valType);
        }
    }

    /* Expressions */
    public Type visit(VarRef v) {
        return sym.lookup(v.name).orElseThrow(() ->
                new TypeError("Use of undeclared variable: " + v.name));
    }

    public Type visit(IntLiteral i) {
        return Type.INT;
    }

    public Type visit(BoolLiteral b) {
        return Type.BOOL;
    }

    public Type visit(BinaryExpr e) {
        Type L = e.left.accept(this);
        Type R = e.right.accept(this);
        if (L == Type.ERROR || R == Type.ERROR) return Type.ERROR;

        switch (e.op) {
            case ADD: case SUB: case MUL: case DIV:
                if (L==Type.INT && R==Type.INT) return Type.INT;
                throw new TypeError("Arithmetic operator requires ints");
            case AND: case OR:
                if (L==Type.BOOL && R==Type.BOOL) return Type.BOOL;
                throw new TypeError("Logical operator requires bools");
            case EQ: case NEQ: case LT: case LE: case GT: case GE:
                if (L==Type.INT && R==Type.INT) return Type.BOOL;
                throw new TypeError("Comparison requires ints");
            default:
                return Type.ERROR;
        }
    }
}