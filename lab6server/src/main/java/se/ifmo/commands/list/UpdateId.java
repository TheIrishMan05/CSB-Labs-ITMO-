package se.ifmo.commands.list;


import se.ifmo.collection.Resource;
import se.ifmo.collection.format.CollectionManager;
import se.ifmo.collection.models.StudyGroup;
import se.ifmo.commands.Command;
import se.ifmo.transfer.Request;
import se.ifmo.transfer.Response;

public class UpdateId extends Command {

    public UpdateId() {
        super("update", "<ID> {element} - update value of the element by its ID", 1);
    }

    @Override
    public Response execute(Request request) {
        if (request.getText() == null || request.getText().isEmpty() || request.getText().isBlank()) {
            return new Response("Error! Command is typed without ID of element for update");
        }

        if (!request.getText().matches("\\d+")) {
            return new Response("Error! Command arg is not a number");
        }

        if (request.getResource().isEmpty()) {
            return new Response("Collection is empty!");
        }

        long idForUpdate = Long.parseLong(request.getText());
        StudyGroup input =  CollectionManager.getInstance().getResource().iterator().next();
        Resource collection = CollectionManager.getInstance().getResource();
        StudyGroup formerStudyGroup = getById(collection, idForUpdate);

        if (formerStudyGroup == null) {
            return new Response(String.format("Group with ID %d does not exist", idForUpdate));
        } else {
            collection.remove(formerStudyGroup);
            collection.add(input);
            return new Response(String.format("Collection with ID %d has been updated", idForUpdate));
        }
    }

    public StudyGroup getById(Resource collection, Long id) {
        for (StudyGroup studyGroup : collection) {
            if (studyGroup.getId() == id) return studyGroup;
        }
        return null;
    }
}
