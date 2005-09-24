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
package org.jaudiotagger.tag.id3;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class ID3v1Iterator implements Iterator
{
    /**
     * DOCUMENT ME!
     */
    private static final int TITLE = 1;

    /**
     * DOCUMENT ME!
     */
    private static final int ARTIST = 2;

    /**
     * DOCUMENT ME!
     */
    private static final int ALBUM = 3;

    /**
     * DOCUMENT ME!
     */
    private static final int COMMENT = 4;

    /**
     * DOCUMENT ME!
     */
    private static final int YEAR = 5;

    /**
     * DOCUMENT ME!
     */
    private static final int GENRE = 6;

    /**
     * DOCUMENT ME!
     */
    private static final int TRACK = 7;

    /**
     * DOCUMENT ME!
     */
    private ID3v1Tag id3v1tag;

    /**
     * DOCUMENT ME!
     */
    private int lastIndex = 0;

    /**
     * Creates a new ID3v1Iterator datatype.
     *
     * @param id3v1tag DOCUMENT ME!
     */
    public ID3v1Iterator(ID3v1Tag id3v1tag)
    {
        this.id3v1tag = id3v1tag;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean hasNext()
    {
        return hasNext(lastIndex);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Object next()
    {
        return next(lastIndex);
    }

    /**
     * DOCUMENT ME!
     */
    public void remove()
    {
        switch (lastIndex)
        {
            case TITLE:
                id3v1tag.title = "";

            case ARTIST:
                id3v1tag.artist = "";

            case ALBUM:
                id3v1tag.album = "";

            case COMMENT:
                id3v1tag.comment = "";

            case YEAR:
                id3v1tag.year = "";

            case GENRE:
                id3v1tag.genre = -1;

            case TRACK:

                if (id3v1tag instanceof ID3v11Tag)
                {
                    ((ID3v11Tag) id3v1tag).track = -1;
                }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     * @return DOCUMENT ME!
     */
    private boolean hasNext(int index)
    {
        switch (index)
        {
            case TITLE:
                return (id3v1tag.title.length() > 0) || hasNext(index + 1);

            case ARTIST:
                return (id3v1tag.artist.length() > 0) || hasNext(index + 1);

            case ALBUM:
                return (id3v1tag.album.length() > 0) || hasNext(index + 1);

            case COMMENT:
                return (id3v1tag.comment.length() > 0) || hasNext(index + 1);

            case YEAR:
                return (id3v1tag.year.length() > 0) || hasNext(index + 1);

            case GENRE:
                return (id3v1tag.genre >= 0) || hasNext(index + 1);

            case TRACK:

                if (id3v1tag instanceof ID3v11Tag)
                {
                    return (((ID3v11Tag) id3v1tag).track >= 0) || hasNext(index + 1);
                }

            default:
                return false;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     * @return DOCUMENT ME!
     * @throws NoSuchElementException DOCUMENT ME!
     */
    private Object next(int index)
    {
        switch (lastIndex)
        {
            case 0:
                return (id3v1tag.title.length() > 0) ? id3v1tag.title : next(index + 1);

            case TITLE:
                return (id3v1tag.artist.length() > 0) ? id3v1tag.artist : next(index + 1);

            case ARTIST:
                return (id3v1tag.album.length() > 0) ? id3v1tag.album : next(index + 1);

            case ALBUM:
                return (id3v1tag.comment.length() > 0) ? id3v1tag.comment : next(index + 1);

            case COMMENT:
                return (id3v1tag.year.length() > 0) ? id3v1tag.year : next(index + 1);

            case YEAR:
                return (id3v1tag.genre >= 0) ? new Byte(id3v1tag.genre) : next(index + 1);

            case GENRE:
                return (id3v1tag instanceof ID3v11Tag && (((ID3v11Tag) id3v1tag).track >= 0)) ? new Byte(((ID3v11Tag) id3v1tag).track) : null;

            default:
                throw new NoSuchElementException("Iteration has no more elements.");
        }
    }
}