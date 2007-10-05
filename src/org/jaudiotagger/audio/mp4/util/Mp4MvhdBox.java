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
package org.jaudiotagger.audio.mp4.util;

import org.jaudiotagger.audio.generic.Utils;

import java.nio.ByteBuffer;

/**
 * This MP4Box contains important audio information we need
 *
 * Can be used to calculate track length
 */
public class Mp4MvhdBox extends AbstractMp4Box
{
    private int timeScale;
    private long timeLength;
    private byte version;

    /**
     *
     * @param header header info
     * @param dataBuffer data of box (doesnt include header data)
     */
    public Mp4MvhdBox(Mp4BoxHeader header, ByteBuffer dataBuffer)
    {
        this.header  = header;
        this.version = dataBuffer.get(0);

        if (version == 1)
        {
            this.timeScale  = Utils.getNumberBigEndian(dataBuffer, 20, 23);
            this.timeLength = Utils.getLongNumberBigEndian(dataBuffer, 24, 31);
        }
        else
        {
            this.timeScale = Utils.getNumberBigEndian(dataBuffer, 12, 15);
            this.timeLength = Utils.getNumberBigEndian(dataBuffer, 16, 19);
        }
    }

    public int getLength()
    {
        return (int) (this.timeLength / this.timeScale);
    }
}