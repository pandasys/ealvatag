/**
 *  Amended @author : Paul Taylor
 *  Initial @author : Eric Farng
 *
 *  Version @version:$Id$
 *
 *  MusicTag Copyright (C)2003,2004
 *
 *  This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser
 *  General Public  License as published by the Free Software Foundation; either version 2.1 of the License,
 *  or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 *  the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License along with this library; if not,
 *  you can get a copy from http://www.opensource.org/licenses/lgpl-license.php or write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * Description:
 */
package org.jaudiotagger.tag.lyrics3;

import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.StringSizeTerminated;
import org.jaudiotagger.tag.InvalidTagException;

import java.io.RandomAccessFile;

public class FieldFrameBodyINF extends AbstractLyrics3v2FieldFrameBody
{
    /**
     * Creates a new FieldBodyINF datatype.
     */
    public FieldFrameBodyINF()
    {
        //        this.setObject("Additional Information", "");
    }

    public FieldFrameBodyINF(FieldFrameBodyINF body)
    {
        super(body);
    }

    /**
     * Creates a new FieldBodyINF datatype.
     *
     * @param additionalInformation DOCUMENT ME!
     */
    public FieldFrameBodyINF(String additionalInformation)
    {
        this.setObjectValue("Additional Information", additionalInformation);
    }

    /**
     * Creates a new FieldBodyINF datatype.
     *
     * @param file DOCUMENT ME!
     * @throws InvalidTagException DOCUMENT ME!
     * @throws java.io.IOException DOCUMENT ME!
     */
    public FieldFrameBodyINF(RandomAccessFile file)
        throws InvalidTagException, java.io.IOException
    {
        this.read(file);
    }

    /**
     * DOCUMENT ME!
     *
     * @param additionalInformation DOCUMENT ME!
     */
    public void setAdditionalInformation(String additionalInformation)
    {
        setObjectValue("Additional Information", additionalInformation);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getAdditionalInformation()
    {
        return (String) getObjectValue("Additional Information");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getIdentifier()
    {
        return "INF";
    }

    /**
     * DOCUMENT ME!
     */
    protected void setupObjectList()
    {
        objectList.add(new StringSizeTerminated("Additional Information", this));
    }
}