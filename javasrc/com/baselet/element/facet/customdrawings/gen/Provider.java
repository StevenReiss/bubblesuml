/* Generated By:JavaCC: Do not edit this line. Provider.java Version 6.1 */
/* JavaCCOptions:KEEP_LINE_COLUMN=true */
package com.baselet.element.facet.customdrawings.gen;


import java.io.IOException;

public interface Provider {
    /**
     * Reads characters into an array
     * @param buffer  Destination buffer
     * @param offset   Offset at which to start storing characters
     * @param length   The maximum possible number of characters to read
     * @return The number of characters read, or -1 if all read
     * @exception  IOException
     */
    public int read(char buffer[], int offset, int len) throws IOException;
    
    /**
     * Closes the stream and releases any system resources associated with
     * it.
     * @exception IOException
     */
     public void close() throws IOException;
    
}
/* JavaCC - OriginalChecksum=c4a9d24541a43381b262e1341aa87ecc (do not edit this line) */
