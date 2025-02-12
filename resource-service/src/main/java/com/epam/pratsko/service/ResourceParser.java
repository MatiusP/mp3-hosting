package com.epam.pratsko.service;

import com.epam.pratsko.exception.ResourceParseException;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.epam.pratsko.exception.ErrorMessages.INVALID_MP3_ERROR;

@Service
public class ResourceParser {

    public Metadata extractMetadata(byte[] resourceContent) {
        if (!isResourceValid(resourceContent)) {
            throw new ResourceParseException(INVALID_MP3_ERROR);
        }

        Metadata metadata = new Metadata();
        try (InputStream inputstream = new ByteArrayInputStream(resourceContent)) {
            BodyContentHandler handler = new BodyContentHandler();
            new Mp3Parser().parse(inputstream, handler, metadata, new ParseContext());
        } catch (IOException | TikaException | SAXException e) {
            throw new ResourceParseException(INVALID_MP3_ERROR);
        }
        return metadata;
    }

    private boolean isResourceValid(byte[] resourceContent) {
        String mp3ContentType = "audio/mpeg";
        if (resourceContent.length == 0) {
            throw new ResourceParseException(INVALID_MP3_ERROR);
        }

        return mp3ContentType.equals(new Tika().detect(resourceContent));
    }

}
