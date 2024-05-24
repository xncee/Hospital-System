package hospital;

//import io.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.JsonFileReader;
import io.JsonFileWriter;

public class File {
    public ObjectNode data;
    private JsonFileReader reader;
    private JsonFileWriter writer;

    public File(String fileName) {
        reader = new JsonFileReader(fileName);
        writer = new JsonFileWriter(fileName);

        data = reader.getJsonNode();
    }

    public ObjectNode read() {
        return data;
    }

    public void write() {
        writer.write(data);
    }
}
