package skillforge;

import java.util.List;

public class UserDatabaseManager extends JsonDatabaseManager<User> {

    public UserDatabaseManager() {
        super("users.json", User.class);
    }

    @Override
    public String getId(User item) {
        return item.getUserId();
    }

    @Override
    public String getEmail(User item) {
        return item.getEmail();
    }

    @Override
    protected boolean isDuplicate(User item, List<User> existingItems) {
        return existingItems.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(item.getEmail()));
    }
}

