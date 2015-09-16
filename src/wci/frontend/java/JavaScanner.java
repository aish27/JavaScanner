package wci.frontend.java;

import wci.frontend.*;
import wci.frontend.java.tokens.*;

import static wci.frontend.Source.EOF;
import static wci.frontend.Source.EOL;
import static wci.frontend.java.JavaTokenType.*;
import static wci.frontend.java.JavaErrorCode.*;

/**
 * <h1>PascalScanner</h1>
 *
 * <p>The Pascal scanner.</p>
 *
 * <p>Copyright (c) 2009 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public class JavaScanner extends Scanner
{
    /**
     * Constructor
     * @param source the source to be used with this scanner.
     */
    public JavaScanner(Source source)
    {
        super(source);
    }

    /**
     * Extract and return the next Java token from the source.
     * @return the next token.
     * @throws Exception if an error occurred.
     */
    protected Token extractToken()
        throws Exception
    {
        skipWhiteSpace();

        Token token;
        char currentChar = currentChar();

        // Construct the next token.  The current character determines the
        // token type.
        if (currentChar == EOF) {
            token = new EofToken(source);
        }
        else if (Character.isLetter(currentChar) || currentChar=='_') {
            token = new JavaWordToken(source);
        }
        else if (Character.isDigit(currentChar)) {
            token = new JavaNumberToken(source);
        }
        else if (currentChar == '\"') 
        {
            //System.out.println("Activate String Token");
            token = new JavaStringToken(source);
        }
        else if(currentChar == '\'')
        {
            //System.out.println("Current char is " + currentChar);

            //System.out.println("Activate Char Token");
            token = new JavaCharToken(source);        
        }
        
        else if (JavaTokenType.SPECIAL_SYMBOLS
                 .containsKey(Character.toString(currentChar))) {
            token = new JavaSpecialSymbolToken(source);
        }
        else {
            token = new JavaErrorToken(source, INVALID_CHARACTER,
                                         Character.toString(currentChar));
            nextChar();  // consume character
        }
        if (token.getText().equals("/*") ||token.getText().equals("//")){
            skipComments(token.getText());
            return extractToken();
        }

        return token;
    }
    /**
     * Skip Comments.  A comment starts with // or /*.
     * @throws Exception if an error occurred.
     */
    private void skipComments(String commentType) throws Exception
    {
       // Start of a comment? need to fix
       char currentChar = currentChar();
       if (commentType.equals("/*")) {
            do {
                    currentChar = nextChar();  // consume comment characters
            } while ((currentChar != '*') && (currentChar != EOF));

            // Found closing '}'?
            if (nextChar() == '/') {
                currentChar = nextChar();  // consume the '}'
            }else{
                skipComments("/*");// no nested comments
            }
        }
        else if (commentType.equals("//")) {
            do {
                    currentChar = nextChar();  // consume comment characters
            } while ((currentChar != EOL) && (currentChar != EOF));

            // Found closing '}'?
            if (currentChar == EOL) {
                currentChar = nextChar();  // consume the 'end of line'
            }
        }
    }

    /**
     * Skip whitespace characters by consuming them.  A comment is whitespace.
     * @throws Exception if an error occurred.
     */
    private void skipWhiteSpace()
        throws Exception
    {
        char currentChar = currentChar();

        while (Character.isWhitespace(currentChar)) {
    
           currentChar = nextChar();  // consume whitespace character
            
        }
    }
}
