package wci.frontend.java.tokens;

import wci.frontend.*;
import wci.frontend.java.*;

import static wci.frontend.Source.EOL;
import static wci.frontend.Source.EOF;
import static wci.frontend.java.JavaTokenType.*;
import static wci.frontend.java.JavaErrorCode.*;

/**
 * <h1>JavaStringToken</h1>
 *
 * <p> Java string tokens.</p>
 *
 * <p>Copyright (c) 2009 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public class JavaCharToken extends JavaToken
{
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public JavaCharToken(Source source)
        throws Exception
    {
        super(source);
    }

    /**
     * Extract a Java char token from the source.
     * @throws Exception if an error occurred.
     */
    protected void extract()
        throws Exception
    {
        StringBuilder textBuffer = new StringBuilder();
        StringBuilder valueBuffer = new StringBuilder();

        char currentChar = nextChar();  // consume initial quote
        textBuffer.append('\''); //escape character for the first quote

        boolean singleQuoteFound = false;
        boolean escapeFound = false;
        // Get character.
        do 
        {
            //System.out.println("Current char is :" + currentChar);
            // Replace any whitespace character with a blank.
            //if (Character.isWhitespace(currentChar)) {
              //  currentChar = ' ';
            //}

            if ((currentChar != '\'') && (currentChar != EOF)) //while we are not at the end
            { //if there is a quote and not end of file
                //System.out.println("CURRENT CHARACTER IS: "+ currentChar);
               
                if(currentChar == '\\' && peekChar() == '\'')
                {
                    currentChar = nextChar();
             
                }
                else if(currentChar == '\\' && peekChar() == '\\')
                {
                    currentChar = nextChar();
             
                }
                textBuffer.append(currentChar);
                valueBuffer.append(currentChar);
                currentChar = nextChar();  // consume character
                

            }

           
            /*
            if (currentChar == '\'') // if we see escape character
            {
                
                while ((currentChar == '\'') && (peekChar() == '\'')) 
                {
                    textBuffer.append("''");
                    valueBuffer.append(currentChar); // append single-quote
                    currentChar = nextChar();        // consume pair of quotes
                    currentChar = nextChar();
                }

            }
            */
            //while current character is not a single quote and not end of file
        } while (((currentChar != '\'')&&!singleQuoteFound) && (currentChar != EOF)  ); 

        if (currentChar == '\'') //end of the character?
        {
            nextChar();  // consume final quote
            textBuffer.append('\'');
            type = CHAR;
            value = valueBuffer.toString();
        }
        else 
        {
            type = ERROR;
            value = UNEXPECTED_EOF;
        }

        text = textBuffer.toString();
    }
}
