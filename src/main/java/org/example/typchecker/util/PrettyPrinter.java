package org.example.typchecker.util;

import org.example.typchecker.ast.*;
import org.example.typchecker.semantic.Type;

public class PrettyPrinter {
    public static String type(Type t) { return t.name().toLowerCase(); }
}