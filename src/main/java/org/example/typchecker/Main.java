package org.example.typchecker;

import org.example.typchecker.ast.*;
import org.example.typchecker.ast.BinaryExpr.Op;
import org.example.typchecker.semantic.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TypeChecker checker = new TypeChecker();

        // ✅ Success program
        // int x = 1 + 2;
        // bool b = true;
        // x = x + 3;
        // b = (x == 6);
        Program successProgram = new Program(Arrays.asList(
                new VarDecl("x", Type.INT, new BinaryExpr(Op.ADD, new IntLiteral(1), new IntLiteral(2))),
                new VarDecl("b", Type.BOOL, new BoolLiteral(true)),
                new Assign("x", new BinaryExpr(Op.ADD, new VarRef("x"), new IntLiteral(3))),
                new Assign("b", new BinaryExpr(Op.EQ, new VarRef("x"), new IntLiteral(6)))
        ));

        // ❌ Error program
        // int x = 5;
        // x = true;   // type error: assigning bool to int
        Program errorProgram = new Program(Arrays.asList(
                new VarDecl("x", Type.INT, new IntLiteral(5)),
                new Assign("x", new BoolLiteral(true))  // ❌ type mismatch
        ));

        // Run both programs
        runProgram("Success case", checker, successProgram);
        runProgram("Error case", checker, errorProgram);
    }

    private static void runProgram(String label, TypeChecker checker, Program program) {
        System.out.println("\n=== " + label + " ===");
        try {
            checker.check(program);
            System.out.println("Type check succeeded.");
        } catch (TypeError e) {
            System.err.println("Type error: " + e.getMessage());
        }
    }
}
