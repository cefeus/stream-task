package by.clevertec.util.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface Reader {

    <T> List<T> getModelData(String fileName, TypeReference<List<T>> typeReference);
    JsonNode getFileContent(String fileName);
}
