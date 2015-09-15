package wci.frontend.java;

import java.util.Hashtable;
import java.util.HashSet;

import wci.frontend.TokenType;

/**
 * <h1>JavaTokenType</h1>
 *
 * <p>
 * Java token types.</p>
 *
 * <p>
 * Code has been copied and modified.
 * Copyright (c) 2009 by Ronald Mak</p>
 * <p>
 * For instructional purposes only. No warranties.</p>
 */
public enum JavaTokenType implements TokenType
{
    // Reserved words.
    ABSTRACT, DOUBLE, INT, LONG, BREAK, ELSE, SWITCH, CASE, ENUM,
    NATIVE, SUPER, CHAR, EXTENDS, RETURN, THIS, CLASS, FLOAT, SHORT, THROW,
    CONST, FOR, PACKAGE, VOID, CONTINUE, GOTO, PROTECTED, VOLATILE, DO, IF,
    STATIC, WHILE,
    
    // Special symbols.
    PLUS("+"), MINUS("-"), STAR("*"), SLASH("/"), TILDE("~"),VERTICAL_BAR("|"),
    DOT("."), COMMA(","), SEMICOLON(";"), COLON(":"), APOSTROPHE("'"), DOUBLE_QUOTES("\""),
    EQUALS("="), BOOLEAN_EQUALS("=="), NOT_EQUALS("!="), LESS_THAN("<"), LESS_EQUALS("<="),
    GREATER_EQUALS(">="), GREATER_THAN(">"), LEFT_PAREN("("), RIGHT_PAREN(")"),MULTIPLY_ASSIGN("*="),DIVIDE_ASSIGN("/="),
    LEFT_BRACKET("["), RIGHT_BRACKET("]"), LEFT_BRACE("{"), RIGHT_BRACE("}"),PLUS_EQUAL("+="),MINUS_EQUAL("-="),
    ADD_ONE("++"),SUBTRACT_ONE("--"), COMMENT("//"),NOT("!"),BITWISE_ASSIGNMENT("|="),AND("&&"),
    SHIFT_RIGHT(">>"), SHIFT_LEFT("<<"), RSHIFT_ASSIGN(">>="), LSHIFT_ASSIGN("<<="),DOUBLE_VERTICAL("||"),
    START_COMMENT("/*"), END_COMMENT("*/"),AT_SIGN("@"),MODULUS("%"),MOD_ASSIGN("%="),
    BIT_ASSIGN("&="),BITWISE_AND("&"),CIRCUMFLEX_ACCENT("^"),QUESTION("?"),BITOR_ASSIGN("^="),
     
    IDENTIFIER, INTEGER, REAL, STRING,
    ERROR, END_OF_FILE;

    private static final int FIRST_RESERVED_INDEX = ABSTRACT.ordinal();
    private static final int LAST_RESERVED_INDEX = WHILE.ordinal();

    private static final int FIRST_SPECIAL_INDEX = PLUS.ordinal();
    private static final int LAST_SPECIAL_INDEX = BITOR_ASSIGN.ordinal();

    private String text;  // token text

    /**
     * Constructor.
     */
    JavaTokenType()
    {
        this.text = this.toString().toLowerCase();
    }

    /**
     * Constructor.
     *
     * @param text the token text.
     */
    JavaTokenType(String text)
    {
        this.text = text;
    }

    /**
     * Getter.
     *
     * @return the token text.
     */
    public String getText()
    {
        return text;
    }

    // Set of lower-cased Java reserved word text strings.
    public static HashSet<String> RESERVED_WORDS = new HashSet<String>();

    static
    {
        JavaTokenType values[] = JavaTokenType.values();
        for (int i = FIRST_RESERVED_INDEX; i <= LAST_RESERVED_INDEX; ++i)
        {
            RESERVED_WORDS.add(values[i].getText().toLowerCase());
        }
    }

    // Hash table of Java special symbols.  Each special symbol's text
    // is the key to its Java token type.
    public static Hashtable<String, JavaTokenType> SPECIAL_SYMBOLS
            = new Hashtable<String, JavaTokenType>();

    static
    {
        JavaTokenType values[] = JavaTokenType.values();
        for (int i = FIRST_SPECIAL_INDEX; i <= LAST_SPECIAL_INDEX; ++i)
        {
            SPECIAL_SYMBOLS.put(values[i].getText(), values[i]);
        }
    }
}
