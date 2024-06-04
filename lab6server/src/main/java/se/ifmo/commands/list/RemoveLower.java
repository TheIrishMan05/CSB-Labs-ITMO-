package se.ifmo.commands.list;


import se.ifmo.collection.format.CollectionManager;
import se.ifmo.collection.models.StudyGroup;
import se.ifmo.commands.Command;
import se.ifmo.transfer.Request;
import se.ifmo.transfer.Response;

public class RemoveLower extends Command {

    public RemoveLower() {
        super("remove_lower", "{element} - remove all elements from the collection" +
                " where name length is less than written element has.");
    }

    @Override
    public Response execute(Request request) {
        if (request.getResource().isEmpty()){
            return new Response("Collection is empty");
        }

        StudyGroup selected = request.getResource().iterator().next();

        CollectionManager.getInstance().getResource().stream()
                .filter(element -> element.compareTo(selected) < 0)
                .forEach(element -> CollectionManager.getInstance().getResource().remove(element));

        return new Response(String.format("All elements lower than %s have been removed", selected.getName()));
    }
}
