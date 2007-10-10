/*
 * Entagged Audio Tag library
 * Copyright (c) 2003-2005 Rapha�l Slinckx <raphael@slinckx.net>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *  
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.jaudiotagger.tag.mp4;

import org.jaudiotagger.tag.mp4.Mp4TagBinaryField;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Represents Cover Art
 *
 * Note:Within this library we have a seperate TagCoverField for every image stored, however this does not map
 * very directly to how they are physically stored within a file, because all are stored under a single covr atom, so
 * a more complex conversion has to be done then for other fields when writing multiple images back to file.
 */
public class Mp4TagCoverField extends Mp4TagBinaryField
{
    private int type;

    /**
     * Empty CoverArt Field
     */
    public Mp4TagCoverField()
    {
        super(Mp4FieldKey.ARTWORK.getFieldName());
    }

    /**
     * Construct CoverField by reading data from audio file
     *
     * @param raw
     * @param type
     * @throws UnsupportedEncodingException
     */
    public Mp4TagCoverField(ByteBuffer raw,int type) throws UnsupportedEncodingException
    {
        super(Mp4FieldKey.ARTWORK.getFieldName(), raw);
        this.type=type;
    }



    /**
     * Construct new binary field with binarydata provided
     *
     * @param data
     * @throws UnsupportedEncodingException
     */
    public Mp4TagCoverField(byte[] data) throws UnsupportedEncodingException
    {
        super(Mp4FieldKey.ARTWORK.getFieldName(),data);
        //TODO hardcoded
        this.type=Mp4FieldType.COVERART_JPEG.getFileClassId();

    }

    protected Mp4FieldType getFieldType()
    {
        //TODO this is wrong needs to match type field
        return Mp4FieldType.COVERART_JPEG;
    }

    public boolean isBinary()
    {
        return true;
    }

    /**
     * Identifies the image type, only jpg and png are supported.
     *
     * @return
     */
    public int getType()
    {
        return type;
    }
}
