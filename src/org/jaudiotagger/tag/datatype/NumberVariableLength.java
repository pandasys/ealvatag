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
 *
 */
package org.jaudiotagger.tag.datatype;

import org.jaudiotagger.tag.id3.ID3Tags;

import org.jaudiotagger.audio.mp3.*;
import org.jaudiotagger.tag.id3.ID3Tags;
import org.jaudiotagger.tag.AbstractTagFrameBody;

public class NumberVariableLength extends AbstractDataType
{
    /**
     * DOCUMENT ME!
     */
    int minLength = 1;

    /**
     * Creates a new ObjectNumberVariableLength datatype.
     *
     * @param identifier  DOCUMENT ME!
     * @param minimumSize DOCUMENT ME!
     */
    public NumberVariableLength(String identifier, AbstractTagFrameBody frameBody, int minimumSize)
    {
        super(identifier, frameBody);

        if (minimumSize > 0)
        {
            this.minLength = minimumSize;
        }
    }

    public NumberVariableLength(NumberVariableLength copy)
    {
        super(copy);
        this.minLength = copy.minLength;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getMaximumLenth()
    {
        return 8;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getMinimumLength()
    {
        return minLength;
    }

    /**
     * DOCUMENT ME!
     *
     * @param minimumSize DOCUMENT ME!
     */
    public void setMinimumSize(int minimumSize)
    {
        if (minimumSize > 0)
        {
            this.minLength = minimumSize;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getSize()
    {
        if (value == null)
        {
            return 0;
        }
        else
        {
            int current;
            long temp = ID3Tags.getWholeNumber(value);
            int size = 0;

            for (int i = 1; i <= 8; i++)
            {
                current = (byte) temp & 0xFF;

                if (current != 0)
                {
                    size = i;
                }

                temp >>= 8;
            }

            return (minLength > size) ? minLength : size;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param obj DOCUMENT ME!
     * @return DOCUMENT ME!
     */
    public boolean equals(Object obj)
    {
        if ((obj instanceof NumberVariableLength) == false)
        {
            return false;
        }

        NumberVariableLength object = (NumberVariableLength) obj;

        if (this.minLength != object.minLength)
        {
            return false;
        }

        return super.equals(obj);
    }

    /**
     * Read from Byte Array
     *
     * @param arr    DOCUMENT ME!
     * @param offset DOCUMENT ME!
     * @throws NullPointerException      DOCUMENT ME!
     * @throws IndexOutOfBoundsException DOCUMENT ME!
     */
    public void readByteArray(byte[] arr, int offset)
    {
        if (arr == null)
        {
            throw new NullPointerException("Byte array is null");
        }

        if ((offset < 0) || (offset >= arr.length))
        {
            throw new IndexOutOfBoundsException("Offset to byte array is out of bounds: offset = " + offset + ", array.length = " + arr.length);
        }

        long lvalue = 0;

        for (int i = offset; i < arr.length; i++)
        {
            lvalue <<= 8;
            lvalue += arr[i];
        }

        value = new Long(lvalue);
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String toString()
    {
        if (value == null)
        {
            return "";
        }
        else
        {
            return value.toString();
        }
    }

    /**
     * Write to Byte Array
     *
     * @return DOCUMENT ME!
     */
    public byte[] writeByteArray()
    {
        int size = getSize();
        byte[] arr;

        if (size == 0)
        {
            arr = new byte[0];
        }
        else
        {
            long temp = ID3Tags.getWholeNumber(value);
            arr = new byte[size];

            for (int i = size - 1; i >= 0; i--)
            {
                arr[i] = (byte) (temp & 0xFF);
                temp >>= 8;
            }
        }
        return arr;
    }
}