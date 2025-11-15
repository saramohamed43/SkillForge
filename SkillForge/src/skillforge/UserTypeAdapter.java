package skillforge;

import com.google.gson.*;
import java.lang.reflect.Type;

public class UserTypeAdapter implements JsonDeserializer<User>, JsonSerializer<User> {

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String role = jsonObject.get("role").getAsString();

        if ("student".equalsIgnoreCase(role)) {
            return context.deserialize(json, Student.class);
        } else if ("instructor".equalsIgnoreCase(role)) {
            return context.deserialize(json, Instructor.class);
        }

        throw new JsonParseException("Unknown user role: " + role);
    }

    @Override
    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src);
    }
}
