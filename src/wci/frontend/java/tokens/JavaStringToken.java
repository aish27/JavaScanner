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
public class JavaStringToken extends JavaToken
{
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public JavaStringToken(Source source)
        throws Exception
    {
        super(source);
    }

    /**
     * Extract a Java string token from the source.
     * @throws Exception if an error occurred.
     */
    protected void extract()
        throws Exception
    {
        StringBuilder textBuffer = new StringBuilder();
        StringBuilder valueBuffer = new StringBuilder();
        char currentChar = currentChar();
        if(currentChar == '\"' && peekChar() == '\"')
        { 
            currentChar = nextChar(); 
        }
        else if(currentChar == '\"' && peekChar() == '\\')
        {
            textBuffer.append('\'');
            textBuffer.append("\"");
            valueBuffer.append("\"");
            currentChar = nextChar();
            currentChar = nextChar();
            currentChar = nextChar();
        }
        else
        {
        //char currentChar = nextChar();  // consume initial quote
        currentChar = nextChar();
        textBuffer.append('\'');

        // Get string characters.
            do 
            {
                // Replace any whitespace character with a blank.
                if (Character.isWhitespace(currentChar)) {
                    currentChar = ' ';
                }

                //if the character is not a quote then keep going
                if ((currentChar != '\"') && (currentChar != EOF)) 
                {
                    textBuffer.append(currentChar);
                    valueBuffer.append(currentChar);
                    currentChar = nextChar();  // consume character
                }
                if(currentChar == '\\' && peekChar()== 't')
                {
                    textBuffer.append(currentChar);
                    currentChar = nextChar();
                    textBuffer.append(currentChar);
                    currentChar = nextChar();
                    valueBuffer.append("\t");
                }
                if(currentChar == '\\' && peekChar()== 'n')
                {
                    textBuffer.append(currentChar);
                    currentChar = nextChar();
                    textBuffer.append(currentChar);
                    currentChar = nextChar();
                    valueBuffer.append("\n");
                }
                if(currentChar == '\\' && peekChar() == '\"')//escape character followed by quote
                {
                    textBuffer.append(currentChar);
                    currentChar = nextChar();
                    textBuffer.append(currentChar);
                    currentChar = nextChar();
                    valueBuffer.append("\"");
                }

            } while ((currentChar != '\"') && (currentChar != EOF));
        }
        
        
        if (currentChar == '\"') { //end of the line here
            nextChar();  // consume final quote
            if(textBuffer.toString().equals(""))
            {
                
            }
            else
            {
                textBuffer.append('\'');
            }
            

            type = STRING;
            value = valueBuffer.toString();
        }
        else {
            type = ERROR;
            value = UNEXPECTED_EOF;
        }

        text = textBuffer.toString();
    }
}