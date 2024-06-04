package se.ifmo.commands.list;


import se.ifmo.collection.Resource;
import se.ifmo.collection.format.CollectionManager;
import se.ifmo.collection.models.StudyGroup;
import se.ifmo.commands.Command;
import se.ifmo.transfer.Request;
import se.ifmo.transfer.Response;

public class RemoveById extends Command {

    public RemoveById() {
        super("remove_by_id", "<ID> - remove element by written ID");
    }

    @Override
    public Response execute(Request request) {
        if (request.getText() == null || request.getText().isEmpty()) {
            return new Response("Error! Command is typed without args");
        }

        if (!request.getText().matches("\\d+")) {
            return new Response("Error! Command arg is not a number");
        }

        long idToFind = Long.parseLong(request.getText());

        Resource resource = CollectionManager.getInstance().getResource();

        for (StudyGroup studyGroup: resource){
            if (studyGroup.getId() == idToFind){
                resource.remove(studyGroup);
                return new Response(String.format("Element with ID %d has been removed", idToFind));
            } else {
                return new Response(String.format("Element with ID %d does not exist", idToFind));
            }
        }
        return null;
    }
}
