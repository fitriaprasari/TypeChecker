package org.example.typchecker;
import java.util.Arrays;
import org.example.typchecker.ast.*;
import org.example.typchecker.ast.BinaryExpr.Op;
import org.example.typchecker.semantic.*;
//import org.junit.Test;
//import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TypeCheckerTest {
    @Test
    public void testCorrectProgram() {
        Program p = new Program(Arrays.asList(
                new VarDecl("x", Type.INT, new IntLiteral(5)),
                new VarDecl("b", Type.BOOL, new BoolLiteral(false)),
                new Assign("x", new BinaryExpr(Op.ADD, new VarRef("x"), new IntLiteral(1)))
        ));
        TypeChecker tc = new TypeChecker();
    }

    @Test
    public void testTypeErrorAssign() {
        Program p = new Program(Arrays.asList(
                new VarDecl("x", Type.INT, new IntLiteral(5)),
                new Assign("x", new BoolLiteral(true))
        ));
        TypeChecker tc = new TypeChecker();
    }
}